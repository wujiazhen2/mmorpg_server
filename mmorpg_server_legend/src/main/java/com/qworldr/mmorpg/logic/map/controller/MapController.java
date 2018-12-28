package com.qworldr.mmorpg.logic.map.controller;

import com.qworldr.mmorpg.annotation.SocketController;
import com.qworldr.mmorpg.annotation.SocketRequest;
import com.qworldr.mmorpg.common.exception.MessageException;
import com.qworldr.mmorpg.common.resp.ErrorResp;
import com.qworldr.mmorpg.common.utils.PacketUtils;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.protocal.MoveReq;
import com.qworldr.mmorpg.logic.map.service.MapService;
import com.qworldr.mmorpg.logic.player.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wujiazhen
 */
@SocketController
public class MapController {
    @Autowired
    private MapService mapService;
    @SocketRequest
    public void move(Player player, MoveReq req){
        /**
         *  TODO 对时 https://blog.codingnow.com/2012/03/dev_note_12.html#comment-42474
         *  1. 登录时同步客户端和服务端的时间，客户端加载服务端时间，保存一个时间差，t= 客户端时间-（服务端和客户端时间差）。
         *  为了保证客户端时间和服务端时间基本同步（还是存在传输的时差）
         *  2. 移动包带上客户端的时间搓t1，当服务端广播时计算从客户端发出请求到服务端广播所消耗时间（t2-t1），稍微减小时延
         *  3. 根据时间差，平滑加快速度移动。
         */
        Position position = req.getPosition();
        List<Position> path = req.getPath();
        try {
            mapService.move(player, position, path);
        }catch (MessageException e){
            PacketUtils.sendPacket(player, ErrorResp.valueOf(e.getMessageId()));
        }
    }
}
