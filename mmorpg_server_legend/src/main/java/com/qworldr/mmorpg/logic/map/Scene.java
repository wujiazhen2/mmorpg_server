package com.qworldr.mmorpg.logic.map;

import com.google.common.collect.Maps;
import com.qworldr.mmorpg.common.utils.EventPublisher;
import com.qworldr.mmorpg.common.utils.MapUtils;
import com.qworldr.mmorpg.common.utils.PacketUtils;
import com.qworldr.mmorpg.logic.map.evet.PlayerEnterSceneEvent;
import com.qworldr.mmorpg.logic.map.object.BiologyObject;
import com.qworldr.mmorpg.logic.map.object.MapObject;
import com.qworldr.mmorpg.logic.map.resource.MapResource;
import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.logic.player.protocal.PlayerEnterWorldResp;

import java.util.*;

public class Scene {
    /**
     * 区域id->地图对象List
     * 通过区域id来划分地图上的对象。
     */
    private Map<Long, MapObject> mapObjectMap= Maps.newConcurrentMap();
    private Map<Integer,Region> regionMap=Maps.newConcurrentMap();
    /**
     * 场景地图格子
     */
    private Grid[][] grids;
    private int sceneId;
    private int mapId;

    public Scene(Grid[][] grids, int sceneId, MapResource mapResource) {
        this.grids = grids;
        this.sceneId = sceneId;
        this.mapId = mapResource.getId();
    }

    public void enter(MapObject mapObject) {
        Position position = mapObject.getPosition();
        mapObjectMap.put(mapObject.getId(),mapObject);
        //添加对象进入消息区域
        Region region = getRegion(MapUtils.createRegionId(position));
        region.addMapObject(mapObject);
        //如果是生物对象，开启心跳;
        if(mapObject instanceof BiologyObject){
            ((BiologyObject) mapObject).spawn();
        }
        //触发进入场景事件
        if(mapObject instanceof Player) {
            EventPublisher.getInstance().publishEvent(new PlayerEnterSceneEvent((Player) mapObject));
            //发包 进入场景
            PacketUtils.sendPacket((Player) mapObject,new PlayerEnterWorldResp(this.sceneId,position.getX(),position.getY()));
        }
    }

    public Region getRegion(int regionId){
        Region region = regionMap.get(regionId);
        if(region==null){
            synchronized (this){
                if(region==null){
                    return createRegion(regionId);
                }
            }
        }
        return region;
    }

    public Region createRegion(int regionId){
        Region region =new Region(regionId,this);
        regionMap.put(regionId,region);
        //设置九宫格
        Set<Integer> nineBlockRegionIds = MapUtils.getNineBlockRegionIds(regionId);
        nineBlockRegionIds.forEach(id->{
            Region re = regionMap.get(id);
            if(re!=null){
                region.addNineBlockRegion(re);
                re.addNineBlockRegion(region);
            }
        });
        return region;
    }

    public int getSceneId() {
        return sceneId;
    }

    public int getMapId() {
        return mapId;
    }
//    public List<MapObject>

}
