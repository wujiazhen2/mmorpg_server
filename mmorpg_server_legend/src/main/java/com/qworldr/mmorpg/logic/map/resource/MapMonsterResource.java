package com.qworldr.mmorpg.logic.map.resource;

import com.qworldr.mmorpg.anno.Resource;
import com.qworldr.mmorpg.logic.map.Position;

import javax.persistence.Id;

/**
 * 地图上的怪物配置
 *
 * @author wujiazhen
 * @Date 2018/12/29
 */
@Resource
public class MapMonsterResource {
    /**
     * 没有特殊意义，只是表示顺序
     */
    @Id
    private int id;
    private int mapId;
    private int monsterId;
    private Position position;

    public int getId() {
        return id;
    }


    public int getMapId() {
        return mapId;
    }


    public int getMonsterId() {
        return monsterId;
    }


    public Position getPosition() {
        return position;
    }


}
