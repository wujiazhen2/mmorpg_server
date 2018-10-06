package com.qworldr.mmorpg.logic.account.controller;


import com.qworldr.mmorpg.annotation.SocketController;
import com.qworldr.mmorpg.annotation.SocketRequest;
import com.qworldr.mmorpg.common.constants.SessionKey;
import com.qworldr.mmorpg.common.resp.Status;
import com.qworldr.mmorpg.logic.account.entity.AccountEntity;
import com.qworldr.mmorpg.logic.account.protocal.LoginReq;
import com.qworldr.mmorpg.logic.account.protocal.RegisterAccountReq;
import com.qworldr.mmorpg.logic.account.service.AccountService;
import com.qworldr.mmorpg.session.Session;
import com.qworldr.mmorpg.session.TcpSession;
import com.qworldr.mmorpg.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

@SocketController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @SocketRequest
    public void login(TcpSession session, LoginReq loginReq){
        if(accountService.loginCheck(loginReq.getAccount(),loginReq.getPwd())){
           accountService.handlerLogin(session,loginReq.getAccount());
        }
    }

    @SocketRequest
    public void login(TcpSession session, RegisterAccountReq registerAccountReq){
        AccountEntity accountEntity=new AccountEntity();
        accountEntity.setAccount(registerAccountReq.getAccount());
        accountEntity.setPwd(SecurityUtils.md5(registerAccountReq.getPwd()));
        Status status=accountService.register(accountEntity);
    }
}
