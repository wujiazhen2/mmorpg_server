package com.qworldr.mmorpg.logic.map.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;
import com.qworldr.mmorpg.logic.map.object.ObjectType;
import com.qworldr.mmorpg.logic.map.protocal.vo.ObjectInfo;
import com.qworldr.mmorpg.logic.player.protocal.vo.PlayerInfo;

import java.util.List;

/**
 * @author wujiazhen
 */
@Protocal(ProtocalId.RegionEnterResp)
public class RegionEnterResp {
    /**
     *  区域内玩家信息
     */
    @Protobuf
    private List<ObjectInfo> objectInfos;
    //TODO  怪物， 物品 。。。。


    public void setObjectInfos(List<ObjectInfo> objectInfos) {
        this.objectInfos = objectInfos;
    }
}
