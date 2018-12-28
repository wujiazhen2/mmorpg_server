package com.qworldr.mmorpg.logic.player.resource;

import com.qworldr.mmorpg.anno.Resource;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeType;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.player.enu.RoleType;

import javax.persistence.Id;
import java.util.Map;
import java.util.Set;

/**
 * @author wujiazhen
 * 角色初始化数据
 */
@Resource
public class PlayerInitializationResource {
    @Id
    private RoleType role;

    /**
     * 初始属性
     */
    private Map<AttributeType,Integer> attrs;
    /**
     * 初始场景
     */
    private int mapId;

    /**
     * 初始技能 skillId:level
     */
    private Map<Integer,Integer> skillIds;
    /**
     *  初始位置
     */
    private Position position;

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public Map<AttributeType, Integer> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<AttributeType, Integer> attrs) {
        this.attrs = attrs;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Map<Integer, Integer> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(Map<Integer, Integer> skillIds) {
        this.skillIds = skillIds;
    }
}
