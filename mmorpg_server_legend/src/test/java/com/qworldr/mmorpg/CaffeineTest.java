package com.qworldr.mmorpg;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.qworldr.mmorpg.logic.account.entity.AccountEntity;
import com.qworldr.mmorpg.provider.HibernateEntityProvider;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class CaffeineTest {

    @Test
    public void test01(){
        Cache<Long , String> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
        String value = cache.get(1L, k -> createValue(k));
         value = cache.getIfPresent(1L);
        cache.put(1L, "1");
        cache.invalidate(1);
    }
    @Test
    public void test02(){
        HibernateEntityProvider<AccountEntity, String> accountEntityStringHibernateEntityProvider = new HibernateEntityProvider<>();
    }
    public String  createValue(Long key){
        return (++key)+"";
    }
}
