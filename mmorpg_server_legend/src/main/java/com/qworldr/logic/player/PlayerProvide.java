package com.qworldr.logic.player;

import com.qworldr.bean.IdentityProvide;
import org.springframework.stereotype.Component;

@Component
public class PlayerProvide implements IdentityProvide<Player, Long> {
    public Player getIdentity(Long id) {
        Player player = new Player();
        player.setId(Long.parseLong(id.toString()));
        player.setName("wujiazhen");
        return player;
    }
}
