package com.qworldr.mmorpg.logic.map.resource;

import com.qworldr.mmorpg.anno.Resource;
import com.qworldr.mmorpg.logic.map.enu.MapType;

import javax.persistence.Id;

/**
 * @Author wujiazhen
 * 地图资源
 */
@Resource
public class MapResource {
    @Id
    private int id;
    private String name;
    private MapType mapType;

    public MapType getMapType() {
        return mapType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
