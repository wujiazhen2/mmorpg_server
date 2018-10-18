package com.qworldr.mmorpg.logic.map;

import com.qworldr.mmorpg.logic.map.object.MapObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wujiazhen
 *  地图区域，用于同步玩家信息。玩家信息会同步给以他所在区域为中心的九宫格区域内所有玩家。
 */
public class Region {
    //处于该区域的地图对象
    private List<MapObject> mapObjects=new ArrayList<>();
}
