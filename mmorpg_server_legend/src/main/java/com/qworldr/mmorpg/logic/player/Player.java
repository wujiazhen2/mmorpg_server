package com.qworldr.mmorpg.logic.player;


import com.qworldr.mmorpg.common.utils.PacketUtils;
import com.qworldr.mmorpg.logic.attribute.AttributeManager;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeSourceType;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeType;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.object.BiologyObject;
import com.qworldr.mmorpg.logic.map.object.ObjectType;
import com.qworldr.mmorpg.logic.map.protocal.RegionEnterResp;
import com.qworldr.mmorpg.logic.map.protocal.vo.ObjectInfo;
import com.qworldr.mmorpg.logic.player.entity.PlayerEntity;
import com.qworldr.mmorpg.logic.player.protocal.vo.PlayerInfo;
import com.qworldr.mmorpg.session.TcpSession;

import java.util.Map;

public class Player extends BiologyObject {


    private AttributeManager attributeManager;
    private TcpSession session;
    public Player(PlayerEntity playerEntity) {
        this.setId(playerEntity.getId());
        this.setPosition(new Position(playerEntity.getX(),playerEntity.getY()));
        this.setMapId(playerEntity.getMapId());
        this.playerEntity = playerEntity;
        this.attributeManager=new AttributeManager();
        this.setType(ObjectType.PLAYER);
    }

    private PlayerEntity playerEntity;

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public void setPlayerEntity(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

    public TcpSession getSession() {
        return session;
    }

    public void setSession(TcpSession session) {
        this.session = session;
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

    @Override
    public void notifyIntoRegion() {
        super.notifyIntoRegion();
        // 进去区域后，获取区域内情况
        //1. 玩家
        Map<Long, Player> players = this.getRegion().getPlayers();
        ObjectInfo[] objectInfos = new ObjectInfo[players.size()];
        players.forEach((k,player)->{
            PlayerEntity playerEntity = player.getPlayerEntity();
            ObjectInfo objectInfo  =ObjectInfo.valueOf(player);
            objectInfos[objectInfos.length]=objectInfo;
        });
        // TODO 怪物，物品，NPC,.....
        RegionEnterResp regionEnterResp=new RegionEnterResp();
        regionEnterResp.setObjectInfos(objectInfos);
        PacketUtils.sendPacket(session,regionEnterResp);
    }
}
