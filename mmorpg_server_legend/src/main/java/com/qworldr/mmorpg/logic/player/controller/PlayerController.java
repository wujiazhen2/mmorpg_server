package com.qworldr.mmorpg.logic.player.controller;


import com.qworldr.mmorpg.annotation.SocketController;
import com.qworldr.mmorpg.annotation.SocketRequest;
import com.qworldr.mmorpg.common.constants.MessageId;
import com.qworldr.mmorpg.common.constants.StatusCode;
import com.qworldr.mmorpg.common.exception.MessageException;
import com.qworldr.mmorpg.common.resp.ErrorResp;
import com.qworldr.mmorpg.common.resp.Status;
import com.qworldr.mmorpg.common.utils.PacketUtils;
import com.qworldr.mmorpg.common.utils.SessionUtils;
import com.qworldr.mmorpg.logic.player.event.PlayerLoginEvent;
import com.qworldr.mmorpg.logic.player.protocal.*;
import com.qworldr.mmorpg.logic.player.service.PlayerService;
import com.qworldr.mmorpg.session.TcpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

@SocketController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @SocketRequest
    public CreateRoleResp createRole(TcpSession session, CreateRoleReq req) {

        if (!SessionUtils.isLogin(session)) {
            CreateRoleResp createRoleResp = new CreateRoleResp();
            createRoleResp.setStatus(Status.valueOf(StatusCode.ERROR, MessageId.NO_LOGIN));
            return createRoleResp;
        }
        try {
            return playerService.createRole(session, req);
        } catch (MessageException e) {
            CreateRoleResp createRoleResp = new CreateRoleResp();
            createRoleResp.setStatus(e.getStatus());
            return createRoleResp;
        }
    }

    @SocketRequest
    public PlayerLoginResp roleLogin(TcpSession session, RoleLoginReq req){
        //TODO 判断角色是否已经登录
        try{
             playerService.roleLogin(session,req.getPlayerId());
        }catch (MessageException e){
            return new PlayerLoginResp(req.getPlayerId(),e.getStatus());
        }
        return null;
    }

    @SocketRequest
    public RoleListResp roleList(TcpSession session,RoleListReq req){
       return playerService.roleList(SessionUtils.getAccount(session));
    }
    @EventListener
    public void loginListener(PlayerLoginEvent event){
        System.out.println("PlayerLoginEvent"+event.getPlayer().getId());
    }

}
