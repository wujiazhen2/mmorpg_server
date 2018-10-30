package com.qworldr.mmorpg.logic.map.object;

import com.qworldr.mmorpg.logic.attribute.AttributeManager;

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
     * 属性管理器，管理生物属性
     */
    private AttributeManager attributeManager;
    private HeartBeatManager heartBeatManager;

    public BiologyObject() {

    }



    public void see(MapObject mapObject) {

    }

    /**
     * 出生
     */
    public void spawn() {

        //出生才创建心跳管理器
        if(this.heartBeatManager==null) {
            this.heartBeatManager = new HeartBeatManager(this);
        }
        //心跳开始
        this.heartBeatManager.start();
    }

    //生物心跳处理
    public void heartbeat() {


    }
}
