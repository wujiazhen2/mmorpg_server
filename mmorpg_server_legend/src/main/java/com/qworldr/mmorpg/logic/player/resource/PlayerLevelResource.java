package com.qworldr.mmorpg.logic.player.resource;

import com.qworldr.mmorpg.anno.Resource;

import javax.persistence.Id;

/**
 * @Author wujiazhen
 */
@Resource
public class PlayerLevelResource {
    /**
     * level
     */
    @Id
    private int level;

    private int statPoint;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStatPoint() {
        return statPoint;
    }

    public void setStatPoint(int statPoint) {
        this.statPoint = statPoint;
    }
}
