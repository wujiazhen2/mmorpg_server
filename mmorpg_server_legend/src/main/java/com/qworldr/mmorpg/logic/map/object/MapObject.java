package com.qworldr.mmorpg.logic.map.object;

import com.qworldr.mmorpg.logic.map.Position;

/**
 * 地图上的对象
 */
public  class  MapObject {
    /**
     * 地图上对象Id
     */
    private long id;
    /**
     *  对象地图上的位置
     */
    private Position position;
    /**
     *  对象是否可视化
     */
    private boolean visible;

    /**
     * 对象类型
     */
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
