package com.qworldr.mmorpg.common.utils;

import com.qworldr.mmorpg.common.constants.SessionKey;
import com.qworldr.mmorpg.session.Session;
import com.qworldr.mmorpg.session.TcpSession;

/**
 * @author wujiazhen
 */
public class SessionUtils {

    public  static  String getAccount(TcpSession session){
        return session.get(SessionKey.SESSION_ID);
    }

    public  static  boolean isLogin(TcpSession session){
        return session.get(SessionKey.SESSION_ID)!=null;
    }
}
