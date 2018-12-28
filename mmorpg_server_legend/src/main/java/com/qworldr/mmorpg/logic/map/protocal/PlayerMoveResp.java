package com.qworldr.mmorpg.logic.map.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;
import com.qworldr.mmorpg.logic.map.Position;

import java.util.List;

/**
 * @author wujiazhen
 */
@Protocal(ProtocalId.PlayerMoveResp)
public class PlayerMoveResp {
    @Protobuf
    private long objectId;
    @Protobuf
    private Position position;
    @Protobuf
    private List<Position> path;
    @Protobuf
    private int speed;

    public static PlayerMoveResp valueOf(long objectId, Position position, List<Position> path,int speed) {
        PlayerMoveResp playerMoveResp = new PlayerMoveResp();
        playerMoveResp.objectId = objectId;
        playerMoveResp.position = position;
        playerMoveResp.path = path;
        playerMoveResp.speed=speed;
        return playerMoveResp;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPath(List<Position> path) {
        this.path = path;
    }
}
