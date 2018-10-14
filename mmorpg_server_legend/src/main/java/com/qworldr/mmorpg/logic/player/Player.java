package com.qworldr.mmorpg.logic.player;


import com.qworldr.mmorpg.logic.map.object.BiologyObject;
import com.qworldr.mmorpg.logic.player.entity.PlayerEntity;

public class Player extends BiologyObject {

    private PlayerEntity playerEntity;

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public void setPlayerEntity(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }
}
