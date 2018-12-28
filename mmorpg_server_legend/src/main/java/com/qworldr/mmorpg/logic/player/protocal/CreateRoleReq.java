package com.qworldr.mmorpg.logic.player.protocal;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;
import com.qworldr.mmorpg.logic.player.enu.RoleType;

/**
 * @author wujiazhen
 */
@Protocal(ProtocalId.CreateRoleReq)
public class CreateRoleReq {
    @Protobuf
    private RoleType role;
    @Protobuf
    private int sex;
    @Protobuf
    private String playerName;

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
