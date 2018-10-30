package com.qworldr.mmorpg.logic.map.evet;

import com.qworldr.mmorpg.common.event.PlayerEvent;
import com.qworldr.mmorpg.logic.player.Player;

/**
 * @Author wujiazhen
 */
public class PlayerEnterSceneEvent extends PlayerEvent {
    public PlayerEnterSceneEvent(Player source) {
        super(source);
    }
}
