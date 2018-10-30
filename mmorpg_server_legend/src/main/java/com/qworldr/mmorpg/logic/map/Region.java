package com.qworldr.mmorpg.logic.map;

import com.google.common.collect.Maps;
import com.qworldr.mmorpg.logic.map.object.MapObject;
import com.qworldr.mmorpg.logic.player.Player;

import java.util.Map;
import java.util.Objects;

/**
 * @Author wujiazhen
 * 地图区域，用于同步玩家信息。玩家信息会同步给以他所在区域为中心的九宫格区域内所有玩家。
 */
public class Region {
    //处于该区域的地图对象
    private Map<Long, MapObject> mapObjects = Maps.newConcurrentMap();
    //注册接受广播的对象,玩家对象才接收广播
    private Map<Long, Player> players = Maps.newConcurrentMap();
    private int regionId;
    private Scene scene;
    /**
     * 以自己为中心的九宫格区域
     */
    private Map<Integer, Region> nineBlock = Maps.newConcurrentMap();

    public Region(int regionId, Scene scene) {
        this.regionId = regionId;
        this.scene = scene;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return regionId == region.regionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionId);
    }

    public void addMapObject(MapObject mapObject) {
        if(mapObject instanceof Player){
            players.put(mapObject.getId(),(Player)mapObject);
        }
        this.mapObjects.put(mapObject.getId(), mapObject);
        //TODO 进入区域，广播给区域内其他玩家
        mapObject.notifyIntoRegion();
    }

    public void removeMapObject(MapObject mapObject) {
        if(mapObject instanceof Player){
            players.remove(mapObject.getId());
        }
        this.mapObjects.remove(mapObject.getId());
        //TODO 离开区域，广播给区域内其他玩家
        mapObject.notifyLeaveRegion();
    }

    public void addNineBlockRegion(Region region) {
        nineBlock.put(region.getRegionId(), region);
    }

    public Map<Long, MapObject> getMapObjects() {
        return mapObjects;
    }

    public Map<Long, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<Long, Player> players) {
        this.players = players;
    }

    public int getRegionId() {
        return regionId;
    }

    public Scene getScene() {
        return scene;
    }

    public Map<Integer, Region> getNineBlock() {
        return nineBlock;
    }
}
