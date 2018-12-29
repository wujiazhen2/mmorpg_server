package com.qworldr.mmorpg.logic.map.resource;

import com.google.common.collect.Maps;
import com.qworldr.mmorpg.provider.ConfigurationResourceProvider;
import com.sun.istack.internal.NotNull;
import edu.emory.mathcs.backport.java.util.Collections;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wujiazhen
 * @Date 2018/12/29
 */
@Component
public class MapMonsterResourceProvider extends ConfigurationResourceProvider<MapMonsterResource, Integer> {
    private Map<Integer, List<MapMonsterResource>> mapMonsterResourceMap;

    @Override
    protected void afterLoad() {
        super.afterLoad();
        Map<Integer, MapMonsterResource> resourceMap = this.resourceMap;
        Map<Integer, List<MapMonsterResource>> objectObjectHashMap = Maps.newHashMap();
        resourceMap.forEach((k, v) -> {
            List<MapMonsterResource> mapMonsterResources = objectObjectHashMap.computeIfAbsent(v.getMapId(), e -> {
                return new ArrayList<>();
            });
            mapMonsterResources.add(v);
        });
        this.mapMonsterResourceMap = Collections.unmodifiableMap(objectObjectHashMap);
    }

    public Map<Integer, List<MapMonsterResource>> getMapMonsterResourceMap() {
        return mapMonsterResourceMap;
    }

    /**
     * 返回该地图配置的所有怪物资源
     *
     * @param mapId
     * @return @NotNull
     */
    public List<MapMonsterResource> getMapMonsterResources(Integer mapId) {
        return mapMonsterResourceMap.getOrDefault(mapId, new ArrayList<>());
    }
}
