package com.qworldr.mmorpg.logic.map.controller;

import com.qworldr.mmorpg.annotation.SocketController;
import com.qworldr.mmorpg.annotation.SocketRequest;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.protocal.MoveReq;
import com.qworldr.mmorpg.logic.player.Player;

import java.util.List;

/**
 * @Author wujiazhen
 */
@SocketController
public class MapController {

    @SocketRequest
    public void move(Player player, MoveReq req){
        Position position = req.getPosition();
        List<Position> path = req.getPath();
    }
}
