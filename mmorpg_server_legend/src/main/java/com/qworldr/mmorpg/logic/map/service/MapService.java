package com.qworldr.mmorpg.logic.map.service;

import com.qworldr.mmorpg.common.constants.MessageId;
import com.qworldr.mmorpg.common.exception.MessageException;
import com.qworldr.mmorpg.common.utils.PacketUtils;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeType;
import com.qworldr.mmorpg.logic.map.Grid;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.manager.MoveManager;
import com.qworldr.mmorpg.logic.map.protocal.PlayerMoveResp;
import com.qworldr.mmorpg.logic.map.resource.MapGridResource;
import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.provider.ResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wujiazhen
 */
@Service
public class MapService {

    @Autowired
    private ResourceProvider<MapGridResource, Integer> mapGridResourceProvider;

    public void move(Player player, Position cur, List<Position> path) {
        //验证玩家是否已经再地图上
        if (!player.isSpawn()) {
            throw new MessageException(MessageId.PLAYER_NOT_SPAWN);
        }

        //服务端玩家点和客户端点不一致的情况下，该怎么处理？？？？
        if (!player.getPosition().equals(cur)) {
            //应该要验证是否在一定误差范围，不然拉扯回来。

            //误差不大以客户端的为准
            player.setPosition(cur);
        }
        MapGridResource mapGridResource = mapGridResourceProvider.get(player.getMapId());
        //路径是否合法
        Grid[][] grids = mapGridResource.getGrids();
        Grid grid;
        try {
            for (Position position : path) {
                grid = grids[position.getY()][position.getX()];
                if (grid.getState() == Grid.BLOCK) {
                    throw new MessageException(MessageId.ROLD_ILLEGAL);
                }
            }
        } catch (Exception e) {
            throw new MessageException(MessageId.ROLD_ILLEGAL);
        }
        MoveManager moveManager = player.getMoveManager();
        moveManager.move(path);

        //将用户的移动路径广播给区域内的客户端。
        PacketUtils.sendRegionPacket(player, PlayerMoveResp.valueOf(player.getId(), player.getPosition(), path, player.getAttributeManager().getAttr(AttributeType.MAG)));
    }
}
