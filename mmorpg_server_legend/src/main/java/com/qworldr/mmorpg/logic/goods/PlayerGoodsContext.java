package com.qworldr.mmorpg.logic.goods;

import javax.persistence.Entity;
import java.util.Map;

/**
 * @author wujiazhen
 * @Date 2018/12/3
 */
@Entity
public class PlayerGoodsContext {

    private long playerId;
    private Map<Short, PlayerGoods> goodses;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public Map<Short, PlayerGoods> getGoodses() {
        return goodses;
    }

    public void setGoodses(Map<Short, PlayerGoods> goodses) {
        this.goodses = goodses;
    }
}
