package com.qworldr.mmorpg.logic.player.entity;

import com.qworldr.mmorpg.anno.Generator;
import com.qworldr.mmorpg.entity.IEntity;
import com.qworldr.mmorpg.logic.player.enu.RoleType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlayerEntity implements IEntity<Long> {
    @Id
    @Column(length = 36)
    @Generator("snowflake")
    private Long id;


    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private String account;

    @Column
    private int level;

    /**
     *  属性点
     */
    @Column
    private int statPoint;
    /**
     * 根据枚举的or
     */
    @Column(length = 1)
    private RoleType role;

    /**
     *  0 男  1 女
     */
    @Column(length = 1)
    private int sex;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
