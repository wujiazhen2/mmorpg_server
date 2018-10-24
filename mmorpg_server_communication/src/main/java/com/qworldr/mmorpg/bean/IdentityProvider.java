package com.qworldr.mmorpg.bean;

import com.qworldr.mmorpg.session.Session;

/**
 * 会话转化为标识用户对象。例如Player
 * @param <T>
 */
public interface IdentityProvider<T> {

    T  getIdentity(Session session);

    void clearIdentity(Session session);
}
