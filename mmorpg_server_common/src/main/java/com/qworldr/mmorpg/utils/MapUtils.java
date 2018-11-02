package com.qworldr.mmorpg.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author wujiazhen
 */
public class MapUtils {

    /**
     * 合并两个Map， left-right
     * @param left
     * @param right
     * @param <K>
     * @return
     */
    public static <K> Map<K,Integer> sub(Map<K,Integer> left,Map<K,Integer> right){
        Map<K,Integer> map = new HashMap<>(left);
        right.forEach((k,v)->{
            map.put(k,map.getOrDefault(k,0)-v);
        });
        return map;
    }
    public static <K> Map<K,Integer> add(Map<K,Integer> left,Map<K,Integer> right){
        Map<K,Integer> map = new HashMap<>(left);
        right.forEach((k,v)->{
            map.put(k,map.getOrDefault(k,0)+v);
        });
        return map;
    }
}
