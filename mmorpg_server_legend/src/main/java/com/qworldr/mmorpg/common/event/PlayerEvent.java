package com.qworldr.mmorpg.common.event;

import com.qworldr.mmorpg.logic.player.Player;
import org.springframework.context.ApplicationEvent;

/**
 * @Author wujiazhen
 *  玩家事件，事件源是玩家
 */
public class PlayerEvent extends ApplicationEvent {

    public PlayerEvent(Player source) {
        super(source);
    }

    public Player getPlayer(){
        return (Player) this.source;
    }
}
