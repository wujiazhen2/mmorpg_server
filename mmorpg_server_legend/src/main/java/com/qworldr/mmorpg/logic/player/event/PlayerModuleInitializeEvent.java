package com.qworldr.mmorpg.logic.player.event;

import com.qworldr.mmorpg.common.event.PlayerEvent;
import com.qworldr.mmorpg.logic.player.Player;

/**
 * @author wujiazhen
 * 玩家登录时发生这个时间，各个模块接受事件，完成Player对象的初始化
 */
public class PlayerModuleInitializeEvent extends PlayerEvent {

    public PlayerModuleInitializeEvent(Player source) {
        super(source);
    }


}
