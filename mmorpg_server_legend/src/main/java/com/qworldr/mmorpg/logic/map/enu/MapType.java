package com.qworldr.mmorpg.logic.map.enu;

import com.qworldr.mmorpg.logic.map.Scene;
import com.qworldr.mmorpg.logic.map.SceneManager;
import com.qworldr.mmorpg.logic.map.resource.MapGridResource;
import com.qworldr.mmorpg.logic.map.resource.MapResource;

/**
 * @author wujiazhen
 * 地图类型
 */
public enum MapType {

        NORMAL;


        public Scene createScene(int mapId, MapResource v){
            SceneManager instance = SceneManager.getInstance();
            MapGridResource mapGridResource = instance.getMapGridResource(mapId);
            Scene scene = new Scene(mapGridResource.getGrids(), instance.createSceneId(mapId), v);
            return scene;
        }
}
