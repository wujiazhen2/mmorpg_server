package com.qworldr.mmorpg.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;

@Protocal(ProtocalId.LoginReq)
@ProtobufClass
public class LoginReq {
    private String account;
    private String sign;

    public String getAccount() {
        return account;
    }

    public String getSign() {
        return sign;
    }
}
