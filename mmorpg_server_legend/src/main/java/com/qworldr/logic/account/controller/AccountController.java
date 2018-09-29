package com.qworldr.logic.account.controller;


import com.qworldr.annotation.SocketController;
import com.qworldr.annotation.SocketRequest;
import com.qworldr.logic.account.protocal.LoginReq;
import com.qworldr.session.Session;

@SocketController
public class AccountController {

    @SocketRequest
    public void login(Session session, LoginReq loginReq){
        session.setId(12345);
    }
}
