package com.qworldr.mmorpg.logic.monster.enu;

import com.qworldr.mmorpg.logic.monster.Monster;
import com.qworldr.mmorpg.logic.monster.resource.MonsterResource;

public enum MonsterType {
    /**
     * 普通怪物
     */
    GENERAL,
    /**
     * 精英怪物
     */
    ELITE,
    /**
     * boss
     */
    BOSS;


    public Monster createMonster(MonsterResource monsterResource) {
        return new Monster(monsterResource);
    }

}
