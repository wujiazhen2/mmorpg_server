package com.qworldr.mmorpg.logic.map.controller;

import com.qworldr.mmorpg.annotation.SocketController;
import com.qworldr.mmorpg.annotation.SocketRequest;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.protocal.MoveReq;
import com.qworldr.mmorpg.logic.player.Player;

/**
 * @Author wujiazhen
 */
@SocketController
public class MapController {

    @SocketRequest
    public void move(Player player, MoveReq req){
        Position position = req.getPosition();
        Position[] path = req.getPath();
    }
}
