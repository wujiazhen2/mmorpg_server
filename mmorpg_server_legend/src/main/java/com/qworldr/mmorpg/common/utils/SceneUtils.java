package com.qworldr.mmorpg.common.utils;

import com.google.common.collect.Sets;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.Scene;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * @Author wujiazhen
 */
public class SceneUtils {
    public final static int regionX;
    public final  static int regionY;
    public final  static int maxSize;
    static {
        Properties properties=new Properties();
        try {
            properties.load(Scene.class.getClassLoader().getResourceAsStream("scene.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        regionX=Integer.parseInt(properties.getProperty("regionX"));
        regionY=Integer.parseInt(properties.getProperty("regionY"));
        maxSize=Integer.parseInt(properties.getProperty("maxSize"));

    }
    /**
     * 区域id用来划分地图，每个区域大小为regionX*regionY
     * @param position
     * @return
     */
    public static int createRegionId(Position position) {
        return position.getX() / regionX * maxSize + position.getY() / regionY;
    }

    /**
     * 获取以centerRegionId为中心得九宫格区域id
     * @param centerRegionId
     * @return 九宫格区域id
     */
    public static Set<Integer> getNineBlockRegionIds(int centerRegionId){
        //还原区域坐标 ，这个坐标和position坐标不一样
        int x = centerRegionId / maxSize;
        int y = centerRegionId % maxSize;
        // 3 * 3 得到9宫格
        Set<Integer> sets = Sets.newHashSet();
        for(int i=x-1;i<=x+1;i++){
            for(int j=y-1;j<=y+1;j++){
                sets.add(i*maxSize+j);
            }
        }
        return sets;
    }
}
