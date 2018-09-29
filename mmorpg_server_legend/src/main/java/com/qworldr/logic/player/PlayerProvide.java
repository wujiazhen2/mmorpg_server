package com.qworldr.logic.player;

import com.qworldr.bean.IdentityProvide;
import com.qworldr.session.Session;
import org.springframework.stereotype.Component;

@Component
public class PlayerProvide implements IdentityProvide<Player> {
    public Player getIdentity(Session session) {
        Player player = new Player();
        player.setId(session.getId());
        player.setName("wujiazhen");
        return player;
    }
}
