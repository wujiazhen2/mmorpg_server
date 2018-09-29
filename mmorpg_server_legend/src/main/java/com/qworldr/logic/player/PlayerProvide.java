package com.qworldr.logic.player;

import com.qworldr.bean.Identity;
import com.qworldr.bean.IdentityProvide;
import org.springframework.stereotype.Component;

@Component
public class PlayerProvide implements IdentityProvide<Player> {
    public Player getIdentity(Object id) {
        Player player = new Player();
        player.setId(Long.parseLong(id.toString()));
        player.setName("wujiazhen");
        return player;
    }
}
