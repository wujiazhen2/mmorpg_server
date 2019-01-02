package com.qworldr.mmorpg.logic.skill.manager;

import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.logic.player.enu.RoleType;
import com.qworldr.mmorpg.logic.player.resource.PlayerInitializationResource;
import com.qworldr.mmorpg.logic.skill.entity.SkillEntity;
import com.qworldr.mmorpg.logic.skill.resource.SkillResource;
import com.qworldr.mmorpg.provider.EntityProvider;
import com.qworldr.mmorpg.provider.ResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wujiazhen
 */
@Component
public class SkillManager {

    @Autowired
    private EntityProvider<SkillEntity,Long>  skillEntityProvider;
    @Autowired
    private ResourceProvider<PlayerInitializationResource, RoleType>  playerInitializationResourceProvider;

    @Autowired
    private ResourceProvider<SkillResource,Integer> skillResourceProvider;
    public SkillEntity getSkillEntity(Player player) {
        return skillEntityProvider.loadAndCreate(player.getId(),id->{
            SkillEntity skillEntity = new SkillEntity();
            skillEntity.setId(id);
            //设置初始技能
            PlayerInitializationResource playerInitializationResource = playerInitializationResourceProvider.get(player.getPlayerEntity().getRole());
            skillEntity.setSkillIds(playerInitializationResource.getSkillIds());
            return skillEntity;
        });
    }
}
