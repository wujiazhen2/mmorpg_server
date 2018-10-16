package com.qworldr.mmorpg.logic.player.service;

import com.qworldr.mmorpg.common.resp.Status;
import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.logic.player.entity.PlayerEntity;
import com.qworldr.mmorpg.logic.player.protocal.CreateRoleReq;
import com.qworldr.mmorpg.provider.EntityProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author wujiazhen
 */
@Service
public class PlayerService {

    @Autowired
    private EntityProvider<PlayerEntity,Long> playerEntityProvider;
    public Status createRole(String account, CreateRoleReq req) {

        return null;
    }
}
