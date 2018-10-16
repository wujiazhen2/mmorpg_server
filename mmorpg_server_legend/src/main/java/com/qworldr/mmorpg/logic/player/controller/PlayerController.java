package com.qworldr.mmorpg.logic.player.controller;


import com.qworldr.mmorpg.annotation.SocketController;
import com.qworldr.mmorpg.annotation.SocketRequest;
import com.qworldr.mmorpg.common.constants.MessageId;
import com.qworldr.mmorpg.common.constants.StatusCode;
import com.qworldr.mmorpg.common.exception.MessageException;
import com.qworldr.mmorpg.common.resp.Status;
import com.qworldr.mmorpg.common.utils.SessionUtils;
import com.qworldr.mmorpg.logic.player.protocal.CreateRoleReq;
import com.qworldr.mmorpg.logic.player.protocal.CreateRoleResp;
import com.qworldr.mmorpg.logic.player.service.PlayerService;
import com.qworldr.mmorpg.session.TcpSession;
import org.springframework.beans.factory.annotation.Autowired;

@SocketController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @SocketRequest
    public CreateRoleResp createRole(TcpSession session, CreateRoleReq req) {
        CreateRoleResp createRoleResp = new CreateRoleResp();
        if (!SessionUtils.isLogin(session)) {
            createRoleResp.setStatus(Status.valueOf(StatusCode.ERROR, MessageId.NO_LOGIN));
            return createRoleResp;
        }
        try {
            createRoleResp.setStatus(playerService.createRole(session, req));
        } catch (MessageException e) {
            createRoleResp.setStatus(e.getStatus());
        }
        return createRoleResp;
    }
}
