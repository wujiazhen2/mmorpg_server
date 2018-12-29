package com.qworldr.mmorpg.logic.monster;

import com.qworldr.mmorpg.common.identify.SnowflakeIdentifyGeneratorStrategy;
import com.qworldr.mmorpg.identify.IdentifyGenerator;
import com.qworldr.mmorpg.logic.map.Scene;
import com.qworldr.mmorpg.logic.map.object.BiologyObject;
import com.qworldr.mmorpg.logic.map.object.ObjectType;
import com.qworldr.mmorpg.logic.map.protocal.vo.ObjectInfo;
import com.qworldr.mmorpg.logic.monster.enu.MonsterType;
import com.qworldr.mmorpg.logic.monster.resource.MonsterResource;

/**
 * 怪物对象
 *
 * @author wujiazhen
 * @Date 2018/12/29
 */
public class Monster extends BiologyObject {

    private MonsterResource monsterResource;
    private String name;
    private MonsterType monsterType;
    private int monsterId;

    public Monster(MonsterResource resource) {
        this.monsterResource = resource;
        this.setId(resource.getObjectId());
        this.setType(ObjectType.MONSTER);
        this.setVisible(true);
        this.name = monsterResource.getName();
        this.monsterType = monsterResource.getType();
        this.monsterId = monsterResource.getId();
    }

    @Override
    public ObjectInfo buildObjectInfo() {
        ObjectInfo objectInfo = super.buildObjectInfo();
        objectInfo.setName(name);
        return objectInfo;
    }

    public MonsterResource getMonsterResource() {
        return monsterResource;
    }

    public void setMonsterResource(MonsterResource monsterResource) {
        this.monsterResource = monsterResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    /**
     * monsterId不是地图上的唯一id。是怪物的唯一id而已。
     *
     * @return
     */
    public int getMonsterId() {
        return monsterId;
    }
}
