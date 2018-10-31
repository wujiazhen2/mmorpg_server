package com.qworldr.mmorpg.logic.map.manager;

import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.object.BiologyObject;
import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.utils.HashUtils;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @Author wujiazhen
 */
public class MoveManager {
    private BiologyObject own;
    private Future future;
    private List<Position> path;
    private long lastMoveTime;
    private int hash;
    public MoveManager(BiologyObject own){
        this.own=own;
    }
    public void move(List<Position> path){

    }
    public void refreshHash(){
        if(own instanceof Player){
            this.hash=((Player) own).getSession().getId();
        }else{
            this.hash= HashUtils.hash(own.getRegion().getScene().getMapId());
        }
    }
    public List<Position> getPath() {
        return path;
    }

    public void setPath(List<Position> path) {
        this.path = path;
    }

    public long getLastMoveTime() {
        return lastMoveTime;
    }

    public void setLastMoveTime(long lastMoveTime) {
        this.lastMoveTime = lastMoveTime;
    }
}
