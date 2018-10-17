package com.qworldr.mmorpg.common.protocal;

import com.qworldr.mmorpg.common.anno.Desc;

public interface ProtocalId {
    @Desc("错误响应")
    short ErrorResp = 10000;
    @Desc("登录请求")
    short LoginReq = 11111;
    @Desc("登录响应")
    short LoginResp = 11112;
    @Desc("注册帐号")
    short RegisterAccountReq = 11113;
    @Desc("注册帐号响应")
    short RegisterAccountResp = 11114;
    @Desc("创建角色")
    short CreateRoleReq = 11115;
    @Desc("创建角色响应")
    short CreateRoleResp = 11116;
    @Desc("角色登录响应")
    short RoleLoginReq = 11117;
    @Desc("角色进入地图响应")
    short PlayerEnterWorldResp = 11118;
    @Desc("角色登录响应")
    short PlayerLoginResp = 11119;
    @Desc("请求角色列表")
    short RoleListReq = 11120;
    @Desc("角色列表响应")
    short RoleListResp = 11121;
}
