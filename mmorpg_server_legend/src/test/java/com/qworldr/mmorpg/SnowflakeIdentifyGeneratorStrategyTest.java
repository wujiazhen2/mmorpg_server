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
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author wujiazhen
 */
public class SnowflakeIdentifyGeneratorStrategyTest  {


    @Test
    public void test() throws InterruptedException {
        SnowflakeIdentifyGeneratorStrategy snowflakeIdentifyGeneratorStrategy=new SnowflakeIdentifyGeneratorStrategy();
        snowflakeIdentifyGeneratorStrategy.setServerId(1023);
        ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executorService.prestartAllCoreThreads();
        CountDownLatch countDownLatch=new CountDownLatch(2000000-1);
        long start=System.currentTimeMillis();
        for(int i=0;i<2000000;i++){
            executorService.execute(()->{
                Long x = snowflakeIdentifyGeneratorStrategy.generatorKey();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end=System.currentTimeMillis();
        System.out.println((end-start));


    }
}
