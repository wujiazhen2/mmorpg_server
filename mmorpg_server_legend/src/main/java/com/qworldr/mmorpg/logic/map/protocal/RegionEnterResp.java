package com.qworldr.mmorpg.logic.map.protocal;

import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;
import com.qworldr.mmorpg.logic.map.object.ObjectType;
import com.qworldr.mmorpg.logic.map.protocal.vo.ObjectInfo;
import com.qworldr.mmorpg.logic.player.protocal.vo.PlayerInfo;

/**
 * @Author wujiazhen
 */
@Protocal(ProtocalId.RegionEnterResp)
public class RegionEnterResp {
    /**
     *  区域内玩家信息
     */
    private ObjectInfo[] objectInfos;
    //TODO  怪物， 物品 。。。。


    public ObjectInfo[] getObjectInfos() {
        return objectInfos;
    }

    public void setObjectInfos(ObjectInfo[] objectInfos) {
        this.objectInfos = objectInfos;
    }
}
