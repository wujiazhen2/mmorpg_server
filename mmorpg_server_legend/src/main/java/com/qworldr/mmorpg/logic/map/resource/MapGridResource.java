package com.qworldr.mmorpg.logic.map.resource;

import com.qworldr.mmorpg.anno.Resource;
import com.qworldr.mmorpg.enu.ReaderType;
import com.qworldr.mmorpg.logic.map.Grid;

import javax.persistence.Id;

/**
 * @author wujiazhen
 * 地图表格
 */
@Resource(value = "*",reader = ReaderType.MAP,suffix = "map",path = "resource/map")
public class MapGridResource {
    @Id
    private int mapId;
    private int xNum;
    private int yNum;
    private Grid[][] grids;

    public int getMapId() {
        return mapId;
    }

    public int getxNum() {
        return xNum;
    }

    public int getyNum() {
        return yNum;
    }

    public Grid[][] getGrids() {
        return grids;
    }
}
