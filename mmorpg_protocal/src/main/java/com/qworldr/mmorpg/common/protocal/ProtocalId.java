package com.qworldr.mmorpg.common.protocal;

public interface ProtocalId {
    short ErrorResp = 10000;
    short LoginReq = 11111;
    short LoginResp = 11112;
    short RegisterAccountReq = 11113;
    short RegisterAccountResp = 11114;
    short CreateRoleReq = 11115;
    short CreateRoleResp = 11116;
    short RoleLoginReq = 11117;
    short PlayerEnterWorldResp = 11118;

    short PlayerLoginResp = 11119;
    short RoleListReq = 11120;
    short RoleListResp = 11121;
}
