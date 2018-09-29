package com.qworldr.thread;


import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class DispatcherExecutor {

    public abstract Future submit(DispatcherTask hashDispatcherTask);

    public abstract Future scheduleAtFixedRate(DispatcherTask hashDispatcherTask, long initialDelay, long period, TimeUnit unit);

    public abstract Future scheduleWithFixedDelay(DispatcherTask hashDispatcherTask, long initialDelay, long delay, TimeUnit unit);

    protected  abstract  ScheduledExecutorService getScheduledExecutorService(int dispathCode);
}
