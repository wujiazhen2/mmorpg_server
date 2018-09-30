package com.qworldr.mmorpg.logic.player.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;

@Protocal(ProtocalId.TestPlayerReq)
@ProtobufClass
public class TestPlayerReq {
    private String name;

    public String getName() {
        return name;
    }
}
