package com.qworldr.mmorpg.logic.map;

import com.google.common.collect.Maps;
import com.qworldr.mmorpg.common.identify.SnowflakeIdentifyGeneratorStrategy;
import com.qworldr.mmorpg.logic.map.resource.MapGridResource;
import com.qworldr.mmorpg.logic.map.resource.MapResource;
import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.provider.ResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author wujiazhen
 */
@Component
public class SceneManager {

    @Autowired
    private ResourceProvider<MapGridResource,Integer> mapGridResourceProvider;
    @Autowired
    private ResourceProvider<MapResource,Integer> mapResourceProvider;
    private Map<Integer, List<Scene>> scenes= Maps.newConcurrentMap();
    private Map<Integer, AtomicInteger> map2SceneSeq=Maps.newConcurrentMap();
    @PostConstruct
    public void init(){
        Map<Integer, MapResource> MapResourceMap = mapResourceProvider.asMap();
        MapResourceMap.forEach((k,v)->{
            //初始化序号
            map2SceneSeq.put(k,new AtomicInteger(0));
            createSence(k, v);
        });
    }

    private Scene createSence(Integer mapId, MapResource v) {
        // 生成场景对象
        MapGridResource mapGridResource = mapGridResourceProvider.get(mapId);
        Scene scene = new Scene(mapGridResource.getGrids(), createSceneId(mapId), v);
        List<Scene> list = this.scenes.get(mapId);
        if(list==null){
            list = new ArrayList<>();
            scenes.put(mapId,list);
        }
        list.add(scene);
        return scene;
    }

    /**
     * scenceId规则 mapId*10000+seq
     * @param mapId
     * @return
     */
    public int createSceneId(int mapId){
        AtomicInteger atomicInteger = map2SceneSeq.get(mapId);
        int i = atomicInteger.incrementAndGet();
        return mapId*10000+i;
    }

    /**
     * 进入场景，场景位置信息已经设置在Player上时
     * @param player
     */
    public void enter(Player player) {
        //TODO 要考虑线程安全问题
        List<Scene> list = this.scenes.get(player.getMapId());
        //TODO　考虑进入那个分线，现在不考虑，进入第一个
        Scene scene=null;
        if(list!=null && list.size()>0){
            scene=list.get(0);
        }else {
            scene=createSence(player.getMapId(),mapResourceProvider.get(player.getMapId()));
        }
        player.setScene(scene);
    }
}
