package com.qworldr.mmorpg.logic.skill.controller;

import com.qworldr.mmorpg.annotation.SocketController;
import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.logic.player.event.PlayerModuleInitializeEvent;
import com.qworldr.mmorpg.logic.skill.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

/**
 * @author wujiazhen
 */
@SocketController
public class SkillController {
    @Autowired
    private SkillService skillService;

    @EventListener
    public void moduleInitialize(PlayerModuleInitializeEvent event){
        //技能模块加载
        Player player = event.getPlayer();
        player.setSkillEntity(skillService.getSkillEntity(player));
    }
}
