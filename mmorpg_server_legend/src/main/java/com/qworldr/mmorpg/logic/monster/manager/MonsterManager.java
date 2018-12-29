package com.qworldr.mmorpg.logic.monster.manager;

import com.qworldr.mmorpg.logic.monster.Monster;
import com.qworldr.mmorpg.logic.monster.resource.MonsterResource;
import com.qworldr.mmorpg.provider.ResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wujiazhen
 * @Date 2018/12/29
 */
@Component
public class MonsterManager {
    @Autowired
    private ResourceProvider<MonsterResource, Integer> monsterResourceProvider;

    public ResourceProvider<MonsterResource, Integer> getMonsterResourceProvider() {
        return monsterResourceProvider;
    }


    public Monster createMonster(int monsterId) {
        MonsterResource monsterResource = monsterResourceProvider.get(monsterId);
        Monster monster = monsterResource.getType().createMonster(monsterResource);
        return monster;
    }

}
