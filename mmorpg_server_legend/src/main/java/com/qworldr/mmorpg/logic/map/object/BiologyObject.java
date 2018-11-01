package com.qworldr.mmorpg.logic.map.object;

import com.qworldr.mmorpg.common.utils.MapUtils;
import com.qworldr.mmorpg.logic.attribute.AttributeManager;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.Region;
import com.qworldr.mmorpg.logic.map.manager.MoveManager;

/**
 * 地图上的生物对象
 */
public class BiologyObject extends MapObject {

    /**
     * 生物的血量状态
     */
    private int hp;
    private int mp;

    private boolean isSpawn;
    /**
     * 游戏中 生物必须有级别
     */
    private int level;

    /**
     * 属性管理器，管理生物属性
     */
    private AttributeManager attributeManager;
    private HeartBeatManager heartBeatManager;
    private MoveManager moveManager;

    public BiologyObject() {
        this.heartBeatManager = new HeartBeatManager(this);
        this.moveManager = new MoveManager(this);
    }



    public void see(MapObject mapObject) {

    }

    /**
     * 出生
     */
    public void spawn() {


        //心跳开始
        this.heartBeatManager.start();
        isSpawn=true;
    }

    /**
     * 生物移动
     * @param position
     */
    public void move(Position position){
        setPosition(position);
        //移动位置，检查是否走出当前消息区域
        int regionId = MapUtils.createRegionId(position);
        if(regionId!=this.getRegion().getRegionId()){
            this.getRegion().removeMapObject(this);
            Region region = this.getRegion().getScene().getRegion(regionId);
            region.addMapObject(this);
        }
    }

    public boolean isSpawn() {
        return isSpawn;
    }

    //生物心跳处理
    public void heartbeat() {


    }

    public AttributeManager getAttributeManager() {
        return attributeManager;
    }

    public MoveManager getMoveManager() {
        return moveManager;
    }
}
