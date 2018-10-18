package com.qworldr.mmorpg.logic.map;

import com.qworldr.mmorpg.logic.map.object.MapObject;
import com.qworldr.mmorpg.logic.map.resource.MapResource;
import com.qworldr.mmorpg.logic.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Scene {
    private Map<Integer, List<MapObject>> regionMapObjectMap;
    /**
     * 场景地图格子
     */
    private Grid[][] grids;

    private int sceneId;
    private int mapId;
    public Scene(Grid[][] grids,int sceneId ,MapResource mapResource){
        this.grids=grids;
        this.sceneId=sceneId;
        this.mapId=mapResource.getId();
    }
    public void enter(MapObject mapObject){
        Position position = mapObject.getPosition();
        //TODO 需要研究下，这个参数应该可以设置 一个区域 40*30
        int regionId=position.getX()/40*10000+position.getY()/30;
        //TODO 要考虑线程安全问题
        List<MapObject> mapObjects = regionMapObjectMap.get(regionId);
        if(mapObjects==null){
            mapObjects=new ArrayList<>();
            regionMapObjectMap.put(regionId,mapObjects);
        }
        mapObjects.add(mapObject);
    }


}
