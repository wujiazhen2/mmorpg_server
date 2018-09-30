package com.qworldr.mmorpg.logic.account.entity;

import javax.persistence.Entity;

@Entity
public class AccountEntity  {
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }
}
