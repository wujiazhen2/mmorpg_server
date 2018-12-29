package com.qworldr.mmorpg.logic.monster.resource;

import com.qworldr.mmorpg.anno.Resource;
import com.qworldr.mmorpg.logic.monster.enu.MonsterType;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Resource
public class MonsterResource {
    @Id
    private int id;


    private String name;
    /**
     * 怪物类型
     */
    private MonsterType type;

    private int hp;
    /**
     * 杀死获得经验
     */
    private int exp;

    /**
     * 小弟
     */
    private Map<Integer,Integer>  footmen;

    /**
     * id<<32 + atomicInteger  =地图唯一的怪物id
     */
    @Transient
    private AtomicInteger objectId = new AtomicInteger(0);

    public int getId() {
        return id;
    }

    public MonsterType getType() {
        return type;
    }

    public int getHp() {
        return hp;
    }

    public int getExp() {
        return exp;
    }

    public Map<Integer, Integer> getFootmen() {
        return footmen;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取id并且加1
     *
     * @return
     */
    public int getObjectId() {
        return id << 32 + objectId.getAndIncrement();
    }
}
