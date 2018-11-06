package com.qworldr.mmorpg.logic.skill.entity;

import com.qworldr.mmorpg.entity.IEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;

/**
 * @Author wujiazhen
 */
@Entity
public class SkillEntity implements IEntity<Long> {
    @Id
    private long playerId;
    @Type(type = "json")
    public Map<Integer,Integer> skillIds;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public Map<Integer, Integer> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(Map<Integer, Integer> skillIds) {
        this.skillIds = skillIds;
    }

    @Override
    public Long getId() {
        return playerId;
    }

    @Override
    public void setId(Long aLong) {
        this.playerId=playerId;
    }
}
