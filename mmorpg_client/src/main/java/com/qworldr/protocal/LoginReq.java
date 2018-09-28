package com.qworldr.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.annotation.Protocal;

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
