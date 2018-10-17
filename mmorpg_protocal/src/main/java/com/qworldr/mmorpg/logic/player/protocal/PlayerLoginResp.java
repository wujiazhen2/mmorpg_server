package com.qworldr.mmorpg.logic.player.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;
import com.qworldr.mmorpg.common.resp.Status;

@Protocal(ProtocalId.PlayerLoginResp)
@ProtobufClass
public class PlayerLoginResp {
    private Status status;
    private long  id;
    public PlayerLoginResp(){}
    public PlayerLoginResp(long id,Status status) {
        this.status = status;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
