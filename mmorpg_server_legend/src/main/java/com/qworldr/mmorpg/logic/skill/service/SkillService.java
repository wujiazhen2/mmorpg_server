package com.qworldr.mmorpg.logic.skill.service;

import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.logic.skill.entity.SkillEntity;
import com.qworldr.mmorpg.logic.skill.manager.SkillManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wujiazhen
 */
@Component
public class SkillService {
    @Autowired
    private SkillManager skillManager;

    public SkillEntity getSkillEntity(Player player){
        return skillManager.getSkillEntity(player);
    }
}
