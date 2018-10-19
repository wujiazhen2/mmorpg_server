package com.qworldr.mmorpg.logic.map.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;
import com.qworldr.mmorpg.logic.map.Position;

/**
 * @Author wujiazhen
 */
@Protocal(ProtocalId.MoveReq)
@ProtobufClass
public class MoveReq {

    private Position position;
    private Position[] path;

    public Position getPosition() {
        return position;
    }

    public Position[] getPath() {
        return path;
    }
}
