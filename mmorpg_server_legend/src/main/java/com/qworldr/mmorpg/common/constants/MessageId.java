package com.qworldr.mmorpg.common.constants;

import com.qworldr.mmorpg.common.anno.Desc;

public interface MessageId {
    @Desc("注册成功")
    int REGISTER_SUCCESS = 10001;
    @Desc("创建角色成功")
    int CREATE_ROLE_SUCCESS = 10002;
    @Desc("登录认证成功")
    int AUTH_SUCCESS = 10003;
    @Desc("非法帐号")
    int ACCOUNT_ILLEGAL = 20001;
    @Desc("帐号已存在")
    int ACCOUNT_EXIST = 20002;
    @Desc("账号未登录")
    int NO_LOGIN = 20003;

    @Desc("认证失败")
    int AUTH_FAIL = 20004 ;

}
