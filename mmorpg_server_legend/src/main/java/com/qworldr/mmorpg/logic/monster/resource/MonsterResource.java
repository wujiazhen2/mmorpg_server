package com.qworldr.mmorpg.logic.monster.resource;

import com.qworldr.mmorpg.anno.Resource;
import com.qworldr.mmorpg.logic.monster.enu.MonsterType;

import javax.persistence.Id;
import java.util.Map;

@Resource
public class MonsterResource {
    @Id
    private int id;

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
}
