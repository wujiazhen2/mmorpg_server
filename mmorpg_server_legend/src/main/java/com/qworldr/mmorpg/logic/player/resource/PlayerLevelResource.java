package com.qworldr.mmorpg.logic.player.resource;

import com.qworldr.mmorpg.anno.Resource;
import com.qworldr.mmorpg.enu.ReaderType;
import com.qworldr.mmorpg.logic.attribute.AttributeType;
import com.qworldr.mmorpg.logic.player.enu.RoleType;

import javax.persistence.Id;
import java.util.Map;

/**
 * @Author wujiazhen
 */
@Resource
public class PlayerLevelResource {
    /**
     *  roleType.ordinal() *1000 +level
     */
    @Id
    private int id;

    private int level;

    private RoleType roleType;

    private Map<AttributeType,Integer> attrs;


    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public Map<AttributeType, Integer> getAttrs() {
        return attrs;
    }
}
