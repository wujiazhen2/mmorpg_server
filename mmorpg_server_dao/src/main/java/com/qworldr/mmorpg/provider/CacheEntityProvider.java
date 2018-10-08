package com.qworldr.mmorpg.provider;


import com.github.benmanes.caffeine.cache.*;
import com.qworldr.mmorpg.entity.IEntity;
import com.qworldr.mmorpg.thread.DispatcherExecutor;
import com.qworldr.mmorpg.thread.DispatcherTask;
import com.qworldr.mmorpg.thread.HashDispatcherThreadPool;
import jdk.internal.org.objectweb.asm.ClassWriter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

/**
 * hibernate加上本地cache缓存
 * @param <T>
 * @param <ID>
 */
public class CacheEntityProvider<T extends IEntity<ID>,ID extends Serializable> extends HibernateEntityProvider<T, ID> {
    private static DispatcherExecutor dispatcherExecutor=new HashDispatcherThreadPool(Runtime.getRuntime().availableProcessors()*2);
    private LoadingCache<ID,T> cache =Caffeine.newBuilder().writer(new CacheWriter<ID, T>(){
        @Override
        public void write(@Nonnull ID key, @Nonnull T value) {
            dispatcherExecutor.submit(new DispatcherTask() {
                @Override
                public int getDispatchCode() {
                    return key.hashCode();
                }

                @Override
                public void run() {
                    CacheEntityProvider.super.saveOrUpdate(value);
                }
            });
        }
        @Override
        public void delete(@Nonnull ID key, @Nullable T value, @Nonnull RemovalCause cause) {
            dispatcherExecutor.submit(new DispatcherTask() {
                @Override
                public int getDispatchCode() {
                    return key.hashCode();
                }

                @Override
                public void run() {
                    CacheEntityProvider.super.delete(key);
                }
            });
        }
    }).maximumSize(10_000).softValues().build(super::get);
    @Override
    public T get(ID id) {
        T t = cache.get(id);
        return t;
    }

    @Override
    public void save(T entity) {
        cache.put(entity.getId(),entity);
    }

    @Override
    public void update(T entity) {
        cache.put(entity.getId(),entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        cache.put(entity.getId(),entity);
    }

    @Override
    public void delete(ID id) {
        cache.invalidate(id);
    }

    @Override
    public void delete(T entity) {
        cache.invalidate(entity.getId());
    }
}
