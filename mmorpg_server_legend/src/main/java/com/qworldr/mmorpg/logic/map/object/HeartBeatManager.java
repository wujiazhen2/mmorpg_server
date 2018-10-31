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
    private  int hash;
    private Future future;
    private ConcurrentLinkedQueue taskQueue= new ConcurrentLinkedQueue();
    public HeartBeatManager(BiologyObject own){
        this.own=own;
        //TODO hash决定心跳执行线程，是否应该在当前场景线程执行?
    }
    public void refreshHash(){
        if(own instanceof Player){
            this.hash=((Player) own).getSession().getId();
        }else{
            this.hash= HashUtils.hash(own.getRegion().getScene().getMapId());
        }
    }
    public void start(){
        //更新hash，关闭原来任务，再开启新的任务,因为切换地图后 hash不一样了。一般都是玩家宠物之类的才会开启多次
        refreshHash();
        stop();
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
        if(this.future!=null){
            future.cancel(false);
            future=null;
        }

    }


}
