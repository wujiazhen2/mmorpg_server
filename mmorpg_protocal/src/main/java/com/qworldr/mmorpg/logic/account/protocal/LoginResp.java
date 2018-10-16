package com.qworldr.mmorpg.logic.account.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;
import com.qworldr.mmorpg.common.resp.Status;

@Protocal(ProtocalId.LoginResp)
@ProtobufClass
public class LoginResp {
    private Status status;
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
