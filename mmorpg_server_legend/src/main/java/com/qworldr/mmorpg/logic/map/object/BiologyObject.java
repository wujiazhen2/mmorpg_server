package com.qworldr.mmorpg.logic.map.object;

import com.qworldr.mmorpg.logic.attribute.AttributeManager;
import com.qworldr.mmorpg.thread.DispatcherExecutor;
import com.qworldr.mmorpg.thread.HashDispatcherThreadPool;

/**
 * 地图上的生物对象
 */
public class BiologyObject extends MapObject {

    /**
     * 生物的血量状态
     */
    private int hp;
    private int mp;

    /**
     * 游戏中 生物必须有级别
     */
    private int level;

    /**
     *  属性管理器，管理生物属性
     */
    private AttributeManager attributeManager;
    private HeartBeatManager heartBeatManager;
    public BiologyObject(){
        this.heartBeatManager=new HeartBeatManager(this);
    }
    public void see(MapObject mapObject){

    }

    //生物心跳处理
    public void heartbeat(){


    }
}
