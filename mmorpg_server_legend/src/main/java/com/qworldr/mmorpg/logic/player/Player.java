package com.qworldr.mmorpg.logic.player;


import com.qworldr.mmorpg.logic.attribute.AttributeManager;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeSourceType;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeType;
import com.qworldr.mmorpg.logic.map.object.BiologyObject;
import com.qworldr.mmorpg.logic.player.entity.PlayerEntity;

import java.util.Map;

public class Player extends BiologyObject {


    private AttributeManager attributeManager;
    public Player(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
        this.attributeManager=new AttributeManager();
    }

    private PlayerEntity playerEntity;

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public void setPlayerEntity(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

    public  void alterStatPonint(int statPoint){
        playerEntity.setStatPoint(playerEntity.getStatPoint()+statPoint);
    }



    public void setAttr(AttributeSourceType sourceType, Map<AttributeType,Integer> attrs){
        //TODO 属性加减
        attributeManager.setAttr(sourceType,attrs);
    }
    @Override
    public long getId() {
        return getPlayerEntity().getId();
    }
}
