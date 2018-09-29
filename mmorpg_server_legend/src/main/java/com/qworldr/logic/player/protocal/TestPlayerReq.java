package com.qworldr.logic.player.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.annotation.Protocal;
import com.qworldr.common.protocal.ProtocalId;

@Protocal(ProtocalId.TestPlayerReq)
@ProtobufClass
public class TestPlayerReq {
    private String name;

    public String getName() {
        return name;
    }
}
