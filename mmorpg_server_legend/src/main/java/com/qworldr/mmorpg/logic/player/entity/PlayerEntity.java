package com.qworldr.mmorpg.logic.player.entity;

import com.qworldr.mmorpg.anno.Generator;
import com.qworldr.mmorpg.entity.IEntity;
import com.qworldr.mmorpg.logic.player.enu.RoleType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name="selectByAccount",query = " from PlayerEntity where account=? ")
})
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
    private Integer level;

    /**
     *  属性点
     */
    @Column
    private Integer statPoint;
    /**
     * 根据枚举的or
     */
    @Column(length = 1)
    private RoleType role;

    /**
     *  0 男  1 女
     */
    @Column(length = 1)
    private Integer sex;

    @Column
    private Integer hp;

    @Column
    private Integer mp;

    @Column
    private Integer mapId;

    @Column
    private Integer x;

    @Column
    private Integer y;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStatPoint() {
        return statPoint;
    }

    public void setStatPoint(Integer statPoint) {
        this.statPoint = statPoint;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
