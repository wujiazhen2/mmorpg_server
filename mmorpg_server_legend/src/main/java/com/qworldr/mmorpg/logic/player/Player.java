package com.qworldr.mmorpg.logic.player;


import com.qworldr.mmorpg.common.utils.PacketUtils;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeSourceType;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeType;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.object.BiologyObject;
import com.qworldr.mmorpg.logic.map.object.MapObject;
import com.qworldr.mmorpg.logic.map.object.ObjectType;
import com.qworldr.mmorpg.logic.map.protocal.ObjectEnterSyncResp;
import com.qworldr.mmorpg.logic.map.protocal.RegionEnterResp;
import com.qworldr.mmorpg.logic.map.protocal.vo.ObjectInfo;
import com.qworldr.mmorpg.logic.player.entity.PlayerEntity;
import com.qworldr.mmorpg.logic.skill.entity.SkillEntity;
import com.qworldr.mmorpg.session.TcpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player extends BiologyObject {

    /**
     *  各模块实体
     */
    private SkillEntity skillEntity;
    private TcpSession session;
    public Player(PlayerEntity playerEntity) {
        this.setId(playerEntity.getId());
        this.setPosition(new Position(playerEntity.getX(),playerEntity.getY()));
        this.setMapId(playerEntity.getMapId());
        this.playerEntity = playerEntity;
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

    public void updateAttr(AttributeSourceType sourceType, Map<AttributeType,Integer> attrs){
        //TODO 属性加减
        this.getAttributeManager().updateAttr(sourceType,attrs);
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
        List<ObjectInfo> objectInfos = new ArrayList<>();
        players.forEach((k,player)->{
            PlayerEntity playerEntity = player.getPlayerEntity();
            objectInfos.add(ObjectInfo.valueOf(player));
        });
        // TODO 怪物，物品，NPC,.....
        RegionEnterResp regionEnterResp=new RegionEnterResp();
        regionEnterResp.setObjectInfos(objectInfos);
        PacketUtils.sendPacket(session,regionEnterResp);
    }

    @Override
    public void see(MapObject mapObject) {
        super.see(mapObject);

        //看到对象，发送包给客户端同步
        ObjectInfo objectInfo=null;
        //TODO 暂时只处理玩家，还有怪物，物品。。。。。
        if(mapObject instanceof Player){
             objectInfo = ObjectInfo.valueOf((Player) mapObject);
        }
        if(objectInfo!=null){
            ObjectEnterSyncResp objectEnterSyncResp = new ObjectEnterSyncResp();
            objectEnterSyncResp.setObjectInfo(objectInfo);
            PacketUtils.sendPacket(this,objectEnterSyncResp);
        }
    }

    public SkillEntity getSkillEntity() {
        return skillEntity;
    }

    public void setSkillEntity(SkillEntity skillEntity) {
        this.skillEntity = skillEntity;
    }
}
