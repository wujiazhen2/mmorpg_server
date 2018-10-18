package com.qworldr.mmorpg.logic.player;


import com.qworldr.mmorpg.logic.attribute.AttributeManager;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeSourceType;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeType;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.Scene;
import com.qworldr.mmorpg.logic.map.object.BiologyObject;
import com.qworldr.mmorpg.logic.player.entity.PlayerEntity;

import java.util.Map;

public class Player extends BiologyObject {


    private AttributeManager attributeManager;
    private Scene scene;
    public Player(PlayerEntity playerEntity) {
        this.setId(playerEntity.getId());
        this.setPosition(new Position(playerEntity.getX(),playerEntity.getY()));
        this.setMapId(playerEntity.getMapId());
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


    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
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
