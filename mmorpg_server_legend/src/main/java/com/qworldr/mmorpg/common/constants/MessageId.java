package com.qworldr.mmorpg.common.constants;

import com.qworldr.mmorpg.common.anno.Desc;

public interface MessageId {
    @Desc("注册成功")
    int REGISTER_SUCCESS = 10001;
    @Desc("非法帐号")
    int ACCOUNT_ILLEGAL = 20001;

    @Desc("帐号已存在")
    int ACCOUNT_EXIST = 20001;
}
