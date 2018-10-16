package com.qworldr.mmorpg.logic.account.controller;


import com.qworldr.mmorpg.annotation.SocketController;
import com.qworldr.mmorpg.annotation.SocketRequest;
import com.qworldr.mmorpg.common.constants.MessageId;
import com.qworldr.mmorpg.common.constants.StatusCode;
import com.qworldr.mmorpg.logic.account.protocal.LoginReq;
import com.qworldr.mmorpg.common.resp.Status;
import com.qworldr.mmorpg.logic.account.entity.AccountEntity;
import com.qworldr.mmorpg.logic.account.protocal.LoginResp;
import com.qworldr.mmorpg.logic.account.protocal.RegisterAccountReq;
import com.qworldr.mmorpg.logic.account.protocal.RegisterAccountResp;
import com.qworldr.mmorpg.logic.account.service.AccountService;
import com.qworldr.mmorpg.session.TcpSession;
import com.qworldr.mmorpg.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

@SocketController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @SocketRequest
    public LoginResp login(TcpSession session, LoginReq loginReq){
        LoginResp loginResp = new LoginResp();
        if(accountService.loginCheck(loginReq.getAccount(),loginReq.getPwd())){
             accountService.handlerLogin(session,loginReq.getAccount());
            loginResp.setStatus(Status.valueOf(StatusCode.SUCCESS, MessageId.AUTH_SUCCESS));
        }else {
            loginResp.setStatus(Status.valueOf(StatusCode.ERROR, MessageId.AUTH_FAIL));
        }
        return loginResp;
    }

    @SocketRequest
    public RegisterAccountResp register(TcpSession session, RegisterAccountReq registerAccountReq){
        AccountEntity accountEntity=new AccountEntity();
        accountEntity.setAccount(registerAccountReq.getAccount());
        accountEntity.setPwd(SecurityUtils.md5(registerAccountReq.getPwd()));
        Status status=accountService.register(accountEntity);
        RegisterAccountResp registerAccountResp = new RegisterAccountResp();
        registerAccountResp.setStatus(status);
        return  registerAccountResp;
    }
}
