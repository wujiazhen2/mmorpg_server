package com.qworldr.mmorpg.logic.account.entity;

import com.qworldr.mmorpg.entity.IEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NamedQueries({@NamedQuery(name = "countByAccountAndPwd", query = "select count(*) from AccountEntity where account = ? and pwd=?")
,@NamedQuery(name = "countByAccount", query = "select count(*) from AccountEntity where account = ?")})

public class AccountEntity implements IEntity<String> {
    @Id
    @Column(length = 255)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    public String getId() {
        return id;
    }

    @Column(length = 32)
    private String account;
    @Column(length = 64)
    private String pwd;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
