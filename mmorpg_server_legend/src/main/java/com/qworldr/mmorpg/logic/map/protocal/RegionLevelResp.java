package com.qworldr.mmorpg.logic.map.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;
import com.qworldr.mmorpg.logic.player.protocal.vo.PlayerInfo;

/**
 * @Author wujiazhen
 */
@Protocal(ProtocalId.RegionLeaveResp)
public class RegionLevelResp {
    @Protobuf
    private Long objectId;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }
}
