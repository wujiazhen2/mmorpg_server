package com.qworldr.mmorpg;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class CaffeineTest {

    @Test
    public void test01(){
        Cache<Long , String> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .ref
                .build();
        String value = cache.get(1L, k -> createValue(k));
         value = cache.getIfPresent(1L);
        cache.put(1L, "1");
        cache.invalidate(1);
    }

    public String  createValue(Long key){
        return (++key)+"";
    }
}
