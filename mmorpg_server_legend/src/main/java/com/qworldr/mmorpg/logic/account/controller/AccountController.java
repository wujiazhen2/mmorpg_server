package com.qworldr.mmorpg.logic.account.controller;


import com.qworldr.mmorpg.annotation.SocketController;
import com.qworldr.mmorpg.annotation.SocketRequest;
import com.qworldr.mmorpg.logic.account.protocal.LoginReq;
import com.qworldr.mmorpg.session.Session;

@SocketController
public class AccountController {

    @SocketRequest
    public void login(Session session, LoginReq loginReq){

    }
}
