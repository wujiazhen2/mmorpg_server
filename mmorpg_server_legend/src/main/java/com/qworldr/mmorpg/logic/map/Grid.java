package com.qworldr.mmorpg.logic.map;

/**
 * @Author wujiazhen
 */
public class Grid {
    private int state;
    public Grid() {}
    public Grid(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
