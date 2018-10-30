package com.qworldr.mmorpg;

import com.qworldr.mmorpg.common.identify.SnowflakeIdentifyGeneratorStrategy;
import org.apache.commons.collections.set.SynchronizedSet;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author wujiazhen
 */
public class SnowflakeIdentifyGeneratorStrategyTest  {


    @Test
    public void test() throws InterruptedException {
        SnowflakeIdentifyGeneratorStrategy snowflakeIdentifyGeneratorStrategy=new SnowflakeIdentifyGeneratorStrategy();
        snowflakeIdentifyGeneratorStrategy.setServerId(1023);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Set<Long> longs = Collections.synchronizedSet(new HashSet<>());
        CountDownLatch countDownLatch =new CountDownLatch(30000);

        long start=System.currentTimeMillis();
        for(int i=0;i<30000;i++){
            executorService.submit(()->{
                Long x = snowflakeIdentifyGeneratorStrategy.generatorKey();

                if(longs.contains(x)){
                    System.out.println("相同了------------------------------------------"+x);
                }else {
                    longs.add(x);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end=System.currentTimeMillis();
        System.out.println((end-start));
    }
}
