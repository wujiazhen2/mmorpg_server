package com.qworldr.mmorpg.logic.map.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;
import com.qworldr.mmorpg.logic.player.protocal.vo.PlayerInfo;

/**
 * @Author wujiazhen
 */
@Protocal(ProtocalId.PlayerStatusResp)
@ProtobufClass
public class PlayerStatusResp {

    private PlayerInfo playerInfo;

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }
}
