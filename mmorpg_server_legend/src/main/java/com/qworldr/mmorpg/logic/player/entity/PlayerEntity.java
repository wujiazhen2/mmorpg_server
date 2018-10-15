package com.qworldr.mmorpg.logic.player.entity;

import com.qworldr.mmorpg.anno.Generator;
import com.qworldr.mmorpg.entity.IEntity;
import com.qworldr.mmorpg.logic.player.enu.RoleType;
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

    /**
     *
     */
    @Column(length = 1)
    private RoleType role;

    /**
     *  0 男  1 女
     */
    @Column(length = 1)
    private byte sex;

    @Column
    private int hp;

    @Column
    private int mp;
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

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
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
