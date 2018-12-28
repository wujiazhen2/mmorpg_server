package com.qworldr.mmorpg.logic.map.manager;

import com.qworldr.mmorpg.logic.attribute.AttributeManager;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeType;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.object.BiologyObject;
import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.thread.DispatcherTask;
import com.qworldr.mmorpg.thread.HashDispatcherThreadPool;
import com.qworldr.mmorpg.utils.HashUtils;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author wujiazhen
 */
public class MoveManager {
    private BiologyObject own;
    private Future future;

    private List<Position> path;

    private int index;
    private long lastMoveTime;
    private int hash;

    public MoveManager(BiologyObject own) {
        this.own = own;
    }

    public void move(List<Position> opath) {
        path = opath;
        index = 0;
        //每次移动更新hash，以防移动对象改变地图。
        refreshHash();
        doMove();
    }
    public void refreshHash(){
        //玩家使用玩家处理线程完成移动操作。
        if(own instanceof Player){
            this.hash=((Player) own).getSession().getId();
        }else{
            this.hash= HashUtils.hash(own.getRegion().getScene().getMapId());
        }
    }
    public int refreshSpeed() {
        AttributeManager attributeManager = own.getAttributeManager();
        return attributeManager.getAttr(AttributeType.MOV);
    }
    public void stop(){
        index=path.size();
    }
    public void doMove() {
        if (path == null || path.size() == 0) {
            return;
        }
        //按照路径走了一步
        Position position = path.get(index++);
        own.move(position);
        //TODO 思考1 如果移动走出了当前消息区域，接下来的移动是否需要广播给新的九宫格区域的玩家。
        //移动一步，如果还有路径继续移动，移动时速度实时更新
        if (path == null || path.size() == 0 || path.size() >= index) {
            HashDispatcherThreadPool.getGlobalDispatcherExecutor().scheduleWithFixedDelay(new DispatcherTask() {
                @Override
                public int getDispatchCode() {
                    return hash;
                }

                @Override
                public void run() {
                    doMove();
                }
            }, 0, refreshSpeed() * 10, TimeUnit.MILLISECONDS);
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
