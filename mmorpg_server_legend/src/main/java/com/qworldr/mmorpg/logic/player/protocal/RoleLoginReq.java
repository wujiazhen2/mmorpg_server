package com.qworldr.mmorpg.logic.player.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;

/**
 * @author wujiazhen
 */
@Protocal(ProtocalId.RoleLoginReq)
public class RoleLoginReq {
    @Protobuf
    private long playerId;

    public long getPlayerId() {
        return playerId;
    }

}
