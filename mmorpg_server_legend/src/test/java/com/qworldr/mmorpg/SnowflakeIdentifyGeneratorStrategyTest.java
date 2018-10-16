package com.qworldr.mmorpg;

import com.qworldr.mmorpg.common.identify.SnowflakeIdentifyGeneratorStrategy;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author wujiazhen
 */
public class SnowflakeIdentifyGeneratorStrategyTest extends SpringTest {

    @Autowired
    private SnowflakeIdentifyGeneratorStrategy snowflakeIdentifyGeneratorStrategy;

    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Set<Long> longs = new HashSet<>();
        CountDownLatch countDownLatch =new CountDownLatch(10000);
        for(int i=0;i<100000;i++){

            executorService.submit(()->{

                Long x = snowflakeIdentifyGeneratorStrategy.generatorKey();
                if(longs.contains(x)){
                    System.out.println("相同了------------------------------------------------------");
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
    }
}
