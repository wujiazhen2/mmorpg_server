package com.qworldr.mmorpg.logic.map;

import com.google.common.collect.Maps;
import com.qworldr.mmorpg.common.identify.SnowflakeIdentifyGeneratorStrategy;
import com.qworldr.mmorpg.logic.map.resource.MapGridResource;
import com.qworldr.mmorpg.logic.map.resource.MapMonsterResource;
import com.qworldr.mmorpg.logic.map.resource.MapMonsterResourceProvider;
import com.qworldr.mmorpg.logic.map.resource.MapResource;
import com.qworldr.mmorpg.logic.monster.Monster;
import com.qworldr.mmorpg.logic.monster.manager.MonsterManager;
import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.provider.ResourceProvider;
import com.qworldr.mmorpg.thread.HashDispatcherThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wujiazhen
 */
@Component
public class SceneManager {

    @Autowired
    private ResourceProvider<MapGridResource, Integer> mapGridResourceProvider;
    @Autowired
    private ResourceProvider<MapResource, Integer> mapResourceProvider;
    @Autowired
    private MapMonsterResourceProvider mapMonsterResourceProvider;
    private Map<Integer, List<Scene>> scenes = Maps.newConcurrentMap();
    private Map<Integer, AtomicInteger> map2SceneSeq = Maps.newConcurrentMap();
    private static SceneManager sceneManager;
    @Autowired
    private MonsterManager monsterManager;
    /**
     * 线程分发,目前还没使用。只是为了保证hashDispathcherThreadPool比SceneManager先初始化。
     */
    @Autowired
    private HashDispatcherThreadPool hashDispatcherThreadPool;
    public static SceneManager getInstance() {
        return sceneManager;
    }

    @PostConstruct
    public void init() {
        sceneManager = this;
        Map<Integer, MapResource> MapResourceMap = mapResourceProvider.asMap();
        MapResourceMap.forEach((k, v) -> {
            //初始化序号
            map2SceneSeq.put(k, new AtomicInteger(0));
            scenes.put(k, Collections.synchronizedList(new ArrayList<>()));
            createSence(k, v);
        });

    }

    public Scene createSence(int mapId, MapResource mapResource) {
        // 生成场景对象
        Scene scene = mapResource.getMapType().createScene(mapId, mapResource);
        //生成场景上的怪物
        creaateMonsterInSence(scene);
        registerScene(mapId, scene);
        return scene;
    }

    public void creaateMonsterInSence(Scene scene) {
        List<MapMonsterResource> mapMonsterResources = mapMonsterResourceProvider.getMapMonsterResources(scene.getMapId());
        mapMonsterResources.forEach(e -> {
            Monster monster = monsterManager.createMonster(e.getMonsterId());
            monster.setPosition(e.getPosition());
            monster.setMapId(e.getMapId());
            scene.enter(monster);
        });
    }

    public void registerScene(int mapId, Scene scene) {
        List<Scene> list = this.scenes.get(mapId);
        list.add(scene);
    }

    /**
     * scenceId规则 mapId*10000+seq
     *
     * @param mapId
     * @return
     */
    public int createSceneId(int mapId) {
        AtomicInteger atomicInteger = map2SceneSeq.get(mapId);
        int i = atomicInteger.incrementAndGet();
        return mapId * 10000 + i;
    }

    /**
     * 进入场景，场景位置信息已经设置在Player上时
     *
     * @param player 玩家
     */
    public void enter(Player player) {
        List<Scene> list = this.scenes.get(player.getMapId());
        //TODO　考虑进入哪个个分线，现在不考虑，进入第一个
        Scene scene = null;
        if (list != null && list.size() > 0) {
            scene = list.get(0);
        } else {
            scene = createSence(player.getMapId(), mapResourceProvider.get(player.getMapId()));
        }
        scene.enter(player);
    }

    public MapGridResource getMapGridResource(int mapId) {
        return mapGridResourceProvider.get(mapId);
    }
}
