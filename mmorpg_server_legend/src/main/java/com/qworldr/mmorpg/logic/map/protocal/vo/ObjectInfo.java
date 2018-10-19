package com.qworldr.mmorpg.logic.map.protocal.vo;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.object.ObjectType;
import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.logic.player.enu.RoleType;

/**
 * @Author wujiazhen
 */
@ProtobufClass
public class ObjectInfo {
    private long objectId;
    private int level;
    private RoleType role;
    private int sex;
    private String name;
    private Position position;
    private ObjectType objectType;
    public ObjectInfo(){};

    public static ObjectInfo valueOf(Player player) {
        ObjectInfo objectInfo=new ObjectInfo();
        objectInfo.objectId = player.getId();
        objectInfo.name=player.getPlayerEntity().getName();
        objectInfo.level = player.getPlayerEntity().getLevel();
        objectInfo.role = player.getPlayerEntity().getRole();
        objectInfo.sex = player.getPlayerEntity().getSex();
        objectInfo.objectType=player.getType();
        objectInfo.position=player.getPosition();
        return objectInfo;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
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
