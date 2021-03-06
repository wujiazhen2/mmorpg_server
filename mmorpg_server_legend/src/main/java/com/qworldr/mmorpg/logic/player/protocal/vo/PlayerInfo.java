package com.qworldr.mmorpg.logic.player.protocal.vo;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.player.enu.RoleType;

/**
 * @author wujiazhen
 */
public class PlayerInfo {
    @Protobuf
    private long playerId;
    @Protobuf
    private int level;
    @Protobuf
    private RoleType role;
    @Protobuf
    private int sex;
    @Protobuf
    private String name;
    @Protobuf
    private Position position;

    public PlayerInfo() {
    }

    ;

    public PlayerInfo(long playerId, String name, int level, RoleType role, int sex) {
        this.playerId = playerId;
        this.name = name;
        this.level = level;
        this.role = role;
        this.sex = sex;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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
