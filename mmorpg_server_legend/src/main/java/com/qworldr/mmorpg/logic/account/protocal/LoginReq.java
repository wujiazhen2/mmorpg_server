package com.qworldr.mmorpg.logic.account.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;

@Protocal(ProtocalId.LoginReq)
@ProtobufClass
public class LoginReq {
    @Protobuf
    private String account;
    @Protobuf
    private String sign;

    public String getAccount() {
        return account;
    }

    public String getSign() {
        return sign;
    }
}
