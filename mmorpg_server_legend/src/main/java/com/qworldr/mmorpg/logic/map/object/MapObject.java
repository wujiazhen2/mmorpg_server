package com.qworldr.mmorpg.logic.map.object;

import com.qworldr.mmorpg.common.utils.PacketUtils;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.Region;
import com.qworldr.mmorpg.logic.map.protocal.RegionLeaveResp;
import com.qworldr.mmorpg.logic.map.protocal.vo.ObjectInfo;

import java.util.Map;
import java.util.Objects;

/**
 * 地图上的对象
 */
public class MapObject {
    /**
     * 地图上对象Id
     */
    private long id;

    private int mapId;

    /**
     * 对象地图上的位置
     */
    private Position position;
    /**
     * 对象所处的场景消息区域
     */
    private Region region;
    /**
     * 对象是否可视化
     */
    private boolean visible = true;

    /**
     * 对象类型
     */
    private ObjectType type;


    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapObject mapObject = (MapObject) o;
        return id == mapObject.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public void notifyLeaveRegion() {
        // 广播离开协议包
        RegionLeaveResp regionLeaveResp = new RegionLeaveResp();
        regionLeaveResp.setObjectId(this.getId());
        PacketUtils.sendRegionPacket(this, regionLeaveResp);
    }

    public void notifyIntoRegion() {
        /**
         * 触发区域内生物看到新进入区域的对象
         */
        Map<Long, MapObject> mapObjects = region.getMapObjects();
        mapObjects.forEach((k, mapObject) -> {
            if (!mapObject.equals(this) && mapObject instanceof BiologyObject) {
                ((BiologyObject) mapObject).see(this);
            }
        });
    }

    /**
     * 用于返回封装对象信息，进出区域时传递给区域对象。
     * 后续对象必须覆盖这个方法
     *
     * @return 对象信息
     */
    public ObjectInfo buildObjectInfo() {
        return new ObjectInfo();
    }
}
