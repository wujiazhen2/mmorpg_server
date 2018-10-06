package com.qworldr.mmorpg.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;

@Protocal(ProtocalId.RegisterAccountReq)
@ProtobufClass
public class RegisterAccountReq {
    private String account;
    private String pwd;

    public String getAccount() {
        return account;
    }

    public String getPwd() {
        return pwd;
    }
}
