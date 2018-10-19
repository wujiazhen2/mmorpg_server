package com.qworldr.mmorpg.logic.player.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;

/**
 * @Author wujiazhen
 */
@Protocal(ProtocalId.RoleLoginReq)
@ProtobufClass
public class RoleLoginReq {

    private long playerId;

    public long getPlayerId() {
        return playerId;
    }

}
