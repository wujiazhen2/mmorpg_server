package com.qworldr.mmorpg.logic.player.entity;

import com.qworldr.mmorpg.anno.Generator;
import com.qworldr.mmorpg.entity.IEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlayerEntity implements IEntity<String> {
    @Id
    @Column(length = 36)
    @Generator
    private String id;
    public String getId() {
        return id;
    }

    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private String account;

    @Column
    private short level;

    @Column(length = 1)
    private byte role;

    @Column(length = 1)
    private byte sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public void setId(String id) {
        this.id=id;
    }
}
