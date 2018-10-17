package com.qworldr.mmorpg.logic.player.protocal.vo;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.logic.player.enu.RoleType;

/**
 * @Author wujiazhen
 */
@ProtobufClass
public class PlayerInfo {
    private long playerId;
    private int level;
    private RoleType role;
    private int sex;
    private String name;
    public PlayerInfo(){};
    public PlayerInfo(long playerId,String name, int level, RoleType role, int sex) {
        this.playerId = playerId;
        this.name=name;
        this.level = level;
        this.role = role;
        this.sex = sex;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
