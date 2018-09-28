package com.qworldr.logic.account.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.annotation.Protocal;
import com.qworldr.common.protocal.ProtocalId;

@Protocal(ProtocalId.LoginReq)
@ProtobufClass
public class LoginReq {

    private String account;
    private String sign;
}
