package com.qworldr.mmorpg.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;

@Protocal(ProtocalId.TestPlayerReq)
@ProtobufClass
public class TestPlayerReq {
    private String name;

    public String getName() {
        return name;
    }
}
