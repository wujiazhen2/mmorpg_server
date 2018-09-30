package com.qworldr.mmorpg.logic.player;

import com.qworldr.mmorpg.bean.IdentityProvider;
import com.qworldr.mmorpg.session.Session;
import org.springframework.stereotype.Component;

@Component
public class PlayerProvider implements IdentityProvider<Player> {
    public Player getIdentity(Session session) {
        Player player = new Player();
        player.setId(session.getId());
        player.setName("wujiazhen");
        return player;
    }
}
