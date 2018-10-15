package com.qworldr.mmorpg.logic.account.entity;

import com.qworldr.mmorpg.anno.Generator;
import com.qworldr.mmorpg.entity.IEntity;
import com.qworldr.mmorpg.type.JsonType;
import org.hibernate.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "countByAccountAndPwd", query = "select count(*) from AccountEntity where account = ? and pwd=?")
,@NamedQuery(name = "countByAccount", query = "select count(*) from AccountEntity where account = ?")})
public class AccountEntity implements IEntity<String> {
    @Id
    @Column(length = 36)
    @Generator
    private String id;

    public String getId() {
        return id;
    }

    @Column(length = 36)
    private String account;
    @Column(length = 64)
    private String pwd;

    @Type(type = "json")
    private List<Integer> ids;


    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

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
