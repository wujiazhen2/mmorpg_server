package com.qworldr.mmorpg.provider;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.qworldr.mmorpg.entity.IEntity;

import java.io.Serializable;

/**
 * hibernate加上本地cache缓存
 * @param <T>
 * @param <ID>
 */
public class CacheEntityProvider<T extends IEntity<ID>,ID extends Serializable> extends HibernateEntityProvider<T, ID> {
    private Cache<ID,T> cache =Caffeine.newBuilder().softValues().build();
    @Override
    public T get(ID id) {
        T t = cache.getIfPresent(id);
        if(t==null) {
            t = super.get(id);
            cache.put(t.getId(),t);
        }
        return super.get(id);
    }

    @Override
    public void save(T entity) {
        super.save(entity);
        cache.put(entity.getId(),entity);
    }
}
