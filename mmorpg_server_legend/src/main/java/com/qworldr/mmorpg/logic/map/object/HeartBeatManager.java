package com.qworldr.mmorpg.logic.map.object;

import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.thread.DispatcherExecutor;
import com.qworldr.mmorpg.thread.DispatcherTask;
import com.qworldr.mmorpg.thread.HashDispatcherThreadPool;
import com.qworldr.mmorpg.utils.HashUtils;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author wujiazhen
 */
public class HeartBeatManager {
    private BiologyObject own;
    private final int hash;
    private Future future;
    private ConcurrentLinkedQueue taskQueue= new ConcurrentLinkedQueue();
    public HeartBeatManager(BiologyObject own){
        this.own=own;
        //TODO hash决定心跳执行线程，是否应该在当前场景线程执行?
        if(own instanceof Player){
            this.hash=((Player) own).getSession().getId();
        }else{
            this.hash= HashUtils.hash(own.getRegion().getScene().getMapId());
        }
    }

    public void start(){
        DispatcherExecutor globalDispatcherExecutor = HashDispatcherThreadPool.getGlobalDispatcherExecutor();
        this.future = globalDispatcherExecutor.scheduleAtFixedRate(new DispatcherTask() {
            @Override
            public int getDispatchCode() {
                return hash;
            }

            @Override
            public void run() {
                own.heartbeat();
                tick();
            }
        }, 1000, 33, TimeUnit.MILLISECONDS);
    }

    private void tick() {

    }

    public void stop(){
        future.cancel(false);
    }


}
