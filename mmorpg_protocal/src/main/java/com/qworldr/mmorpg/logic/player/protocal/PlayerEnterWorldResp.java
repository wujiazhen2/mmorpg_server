package com.qworldr.mmorpg.logic.player.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;
import com.qworldr.mmorpg.common.resp.Status;

/**
 * @Author wujiazhen
 */
@Protocal(ProtocalId.PlayerEnterWorldResp)
@ProtobufClass
public class PlayerEnterWorldResp {
    private int sceneId;
    private int x;
    private int y;
    public PlayerEnterWorldResp(){}
    public PlayerEnterWorldResp(int sceneId, int x, int y) {
        this.sceneId = sceneId;
        this.x = x;
        this.y = y;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
