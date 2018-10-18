package com.qworldr.mmorpg.logic.map.resource;

import com.qworldr.mmorpg.anno.Resource;

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
