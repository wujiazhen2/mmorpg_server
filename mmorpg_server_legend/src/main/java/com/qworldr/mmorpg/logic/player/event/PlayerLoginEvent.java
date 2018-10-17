package com.qworldr.mmorpg.logic.player.event;

import com.qworldr.mmorpg.common.event.PlayerEvent;
import com.qworldr.mmorpg.logic.player.Player;
import org.springframework.context.ApplicationEvent;

/**
 * @Author wujiazhen
 */
public class PlayerLoginEvent extends PlayerEvent {

    public PlayerLoginEvent(Player source) {
        super(source);
    }


}
