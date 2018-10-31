package com.qworldr.mmorpg.logic.map.service;

import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.manager.MoveManager;
import com.qworldr.mmorpg.logic.player.Player;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wujiazhen
 */
@Service
public class MapService {


    public void move(Player player, Position cur, List<Position> path) {
        //验证玩家是否已经再地图上
        if(!player.isSpawn()){

        }
        //TODO 路径是否合法
        MoveManager moveManager = player.getMoveManager();
        moveManager.move(path);
    }
}
