package com.qworldr.mmorpg.logic.map.controller;

import com.qworldr.mmorpg.annotation.SocketController;
import com.qworldr.mmorpg.annotation.SocketRequest;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.protocal.MoveReq;
import com.qworldr.mmorpg.logic.map.service.MapService;
import com.qworldr.mmorpg.logic.player.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author wujiazhen
 */
@SocketController
public class MapController {
    @Autowired
    private MapService mapService;
    @SocketRequest
    public void move(Player player, MoveReq req){
        /**
         *  移动要考虑网络的时延。 玩家A，在t1发送移动请求并在客户端进行移动，服务端收到请求并校验处理用时t2，
         *  玩家B收到服务端的广播用了t3 。玩家A的已经移动了（t2+t3）*speed;
         *  1. 登录时同步客户端和服务端的时间，客户端加载服务端时间，保存一个时间差，t= 客户端时间-（服务端和客户端时间差）。
         *  为了保证客户端时间和服务端时间基本同步（还是存在传输的时差）
         *  2. 移动包带上客户端的时间搓t1，当服务端广播时计算从客户端发出请求到服务端广播所消耗时间（t2-t1），稍微减小时延
         *  3. 根据时间差，平滑加快速度移动。
         *
         *  这里简单处理，只消除服务端处理时延
         */
        Position position = req.getPosition();
        List<Position> path = req.getPath();
        //验证路径合法
        mapService.move(player,position,path);
    }
}
