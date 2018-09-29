package com.qworldr.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.annotation.Protocal;

@Protocal(ProtocalId.TestPlayerReq)
@ProtobufClass
public class TestPlayerReq {
    private String name;

    public String getName() {
        return name;
    }
}
