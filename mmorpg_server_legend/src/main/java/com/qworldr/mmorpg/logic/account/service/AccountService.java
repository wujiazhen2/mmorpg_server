package com.qworldr.mmorpg.logic.account.service;
import com.qworldr.mmorpg.logic.monster.resource.MonsterResource;
import com.qworldr.mmorpg.provider.ResourceProvider;
import com.qworldr.mmorpg.session.TcpSession;
import com.qworldr.mmorpg.common.constants.MessageId;
import com.qworldr.mmorpg.common.constants.SessionKey;
import com.qworldr.mmorpg.common.constants.StatusCode;
import com.qworldr.mmorpg.common.resp.Status;
import com.qworldr.mmorpg.logic.account.entity.AccountEntity;
import com.qworldr.mmorpg.provider.EntityProvider;
import com.qworldr.mmorpg.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AccountService  {

    @Autowired
    private  EntityProvider<AccountEntity,String>  accountProvider;
    public boolean loginCheck(String account,String pwd){
        //TODO 验证登录
        Long count = accountProvider.queryForSingle("countByAccountAndPwd",Long.class, account, SecurityUtils.md5(pwd));
        if(count ==0){
            return false;
        }
        return true;
    }

    public void handlerLogin(TcpSession session, String account) {
        session.put(SessionKey.SESSION_ID,account);
    }

    public Status register(AccountEntity accountEntity) {
        //验证帐号格式
        if(!isLegalAccount(accountEntity.getAccount())){
            return Status.valueOf(StatusCode.ERROR,MessageId.ACCOUNT_ILLEGAL);
        }
        //验证帐号是否已存在
        Long count = accountProvider.queryForSingle("countByAccount", Long.class, accountEntity.getAccount());
        if(count==0) {
            accountProvider.save(accountEntity);
            return Status.valueOf(StatusCode.SUCCESS, MessageId.REGISTER_SUCCESS);
        }
        return Status.valueOf(StatusCode.ERROR,MessageId.ACCOUNT_EXIST);
    }

    private boolean isLegalAccount(String account) {
        //TODO 验证帐号合法
        return true;
    }
}
