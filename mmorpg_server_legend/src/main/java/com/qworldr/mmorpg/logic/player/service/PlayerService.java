package com.qworldr.mmorpg.logic.player.service;

import com.qworldr.mmorpg.common.constants.MessageId;
import com.qworldr.mmorpg.common.constants.StatusCode;
import com.qworldr.mmorpg.common.resp.Status;
import com.qworldr.mmorpg.common.utils.SessionUtils;
import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.logic.player.PlayerManager;
import com.qworldr.mmorpg.logic.player.entity.PlayerEntity;
import com.qworldr.mmorpg.logic.player.protocal.CreateRoleReq;
import com.qworldr.mmorpg.provider.EntityProvider;
import com.qworldr.mmorpg.session.TcpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author wujiazhen
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerManager playerManager;
    public Status createRole(TcpSession session, CreateRoleReq req) {
        Player player = playerManager.createPlayer(SessionUtils.getAccount(session), req.getPlayerName(), req.getRole(), req.getSex());
        playerManager.loginPlayer(session,player);
        return Status.valueOf(StatusCode.SUCCESS, MessageId.CREATE_ROLE_SUCCESS);
    }
}
