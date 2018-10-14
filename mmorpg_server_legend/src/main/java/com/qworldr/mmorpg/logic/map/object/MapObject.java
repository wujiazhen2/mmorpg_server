package com.qworldr.mmorpg.logic.map.object;

import com.qworldr.mmorpg.logic.map.Position;

/**
 * 地图上的对象
 */
public  class  MapObject {
    private long id;
    private Position position;
    private boolean visible;
    private ObjectType type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ObjectType getType() {
        return type;
    }

    public void setType(ObjectType type) {
        this.type = type;
    }
}
