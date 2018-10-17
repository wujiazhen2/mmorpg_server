package com.qworldr.mmorpg.logic.player.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;
import com.qworldr.mmorpg.logic.player.protocal.vo.PlayerInfo;

import java.util.List;

/**
 * @Author wujiazhen
 */
@Protocal(ProtocalId.RoleListResp)
@ProtobufClass
public class RoleListResp {
    private List<PlayerInfo> playerInfos;

    public List<PlayerInfo> getPlayerInfos() {
        return playerInfos;
    }

    public void setPlayerInfos(List<PlayerInfo> playerInfos) {
        this.playerInfos = playerInfos;
    }
}
