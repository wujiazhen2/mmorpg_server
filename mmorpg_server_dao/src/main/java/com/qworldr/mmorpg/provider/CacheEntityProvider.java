package com.qworldr.mmorpg.provider;


import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.qworldr.mmorpg.entity.IEntity;
import com.qworldr.mmorpg.exception.GeneratorException;
import com.qworldr.mmorpg.identify.IdentifyGenerator;
import com.qworldr.mmorpg.thread.DispatcherExecutor;
import com.qworldr.mmorpg.thread.DispatcherTask;
import com.qworldr.mmorpg.thread.HashDispatcherThreadPool;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

/**
 * hibernate加上本地cache缓存
 *
 * @param <T>
 * @param <ID>
 */
public class CacheEntityProvider<T extends IEntity<ID>, ID extends Serializable> extends HibernateEntityProvider<T, ID> {
    private static DispatcherExecutor dispatcherExecutor = new HashDispatcherThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    private LoadingCache<ID, T> cache = Caffeine.newBuilder().writer(new CacheWriter<ID, T>() {

        @Override
        public void write(@Nonnull ID key, @Nonnull T value) {
            //这里处理难以区分是保存还是删除，
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
        if (getKeyGenerator() != null) {
            try {
                entity.setId((ID) IdentifyGenerator.getInstance().getGeneratorStrategy(getKeyGenerator()).generatorKey());
            } catch (Exception e) {
                throw new GeneratorException(String.format("类型转化错误,id生产策略 %s 生产id和实体id类型不一致", entity.getId().getClass()));
            }
        } else {
            if (entity.getId() == null) {
                throw new RuntimeException(String.format("实体%s没有id生成策略，也没有设置id。", entity.getClass()));
            }
        }
        cache.put(entity.getId(), entity);
        //数据库保存
        dispatcherExecutor.submit(new DispatcherTask() {
            @Override
            public int getDispatchCode() {
                return Math.abs(entity.getId().hashCode());
            }

            @Override
            public void run() {
                //TODO 延时更新，将操作合并，减少对数据库的访问。--服务器挂了导致数据丢，通过日志记录操作，可以通过日志还原??
                CacheEntityProvider.super.save(entity);
            }
        });
    }

    @Override
    public void update(T entity) {
        cache.put(entity.getId(), entity);
        //数据库更新
        dispatcherExecutor.submit(new DispatcherTask() {
            @Override
            public int getDispatchCode() {
                return Math.abs(entity.getId().hashCode());
            }

            @Override
            public void run() {
                //TODO 延时更新，将操作合并，减少对数据库的访问。--服务器挂了导致数据丢，通过日志记录操作，可以通过日志还原??
                CacheEntityProvider.super.update(entity);
            }
        });
    }


    @Override
    public void delete(ID id) {
        cache.invalidate(id);
    }

    @Override
    public void delete(T entity) {
        cache.invalidate(entity.getId());
    }

    @Override
    public T loadAndCreate(ID id, ICreator<T, ID> creator) {
        T t = get(id);
        if (t != null) {
            return t;
        }
        T t1 = creator.create(id);
        save(t1);
        return t1;
    }
}
