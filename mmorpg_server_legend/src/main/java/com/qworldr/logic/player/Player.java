package com.qworldr.logic.player;

import com.qworldr.bean.Identity;

public class Player implements Identity<Long> {
    private long id;
    private String name;
    public Long getId() {
        return null;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
