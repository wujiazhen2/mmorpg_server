package com.qworldr.mmorpg.thread;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class HashDispatcherThreadPool extends DispatcherExecutor implements ApplicationContextAware {

    @Value("${threadPoolSize}")
    private int threadPoolSize;
    private static HashDispatcherThreadPool hashDispatcherThreadPool;
    private static ApplicationContext applicationContext;
    private static final int DEFAULT_THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    @PostConstruct
    public  void init(){
        if (threadPoolSize <= 0) {
            threadPoolSize = DEFAULT_THREAD_POOL_SIZE;
        }
        this.init(threadPoolSize);
        hashDispatcherThreadPool=this;
    }

    public static DispatcherExecutor getGlobalDispatcherExecutor(){
        return hashDispatcherThreadPool;
    }

    private ScheduledExecutorService[] threadPool;

    public HashDispatcherThreadPool(int threadSize) {
        init(threadSize);
    }

    public HashDispatcherThreadPool() {

    }

    public void init(int threadSize) {
        threadPool = new ScheduledExecutorService[threadSize];
        for (int i = 0; i < threadSize; i++) {
            threadPool[i] = Executors.newSingleThreadScheduledExecutor();
        }
    }

    public Future submit(DispatcherTask hashDispatcherTask){
        ScheduledExecutorService scheduledExecutorService = getScheduledExecutorService(hashDispatcherTask.getDispatchCode());
        return scheduledExecutorService.submit(hashDispatcherTask);
    }


    public Future scheduleAtFixedRate(DispatcherTask hashDispatcherTask, long initialDelay, long period, TimeUnit unit){
        ScheduledExecutorService scheduledExecutorService = getScheduledExecutorService(hashDispatcherTask.getDispatchCode());
        return scheduledExecutorService.scheduleAtFixedRate(hashDispatcherTask,initialDelay,period, unit);
    }

    public Future scheduleWithFixedDelay(DispatcherTask hashDispatcherTask, long initialDelay, long delay, TimeUnit unit){
        ScheduledExecutorService scheduledExecutorService = getScheduledExecutorService(hashDispatcherTask.getDispatchCode());
        return scheduledExecutorService.scheduleWithFixedDelay(hashDispatcherTask,initialDelay,delay, unit);
    }

    protected ScheduledExecutorService getScheduledExecutorService(int dispathCode) {
        return threadPool[dispathCode % threadPool.length];
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
