package com.qworldr.mmorpg.bean;

import com.qworldr.mmorpg.session.Session;

/**
 * 会话转化为标识用户对象。例如Player
 * @param <T>
 */
public interface IdentityProvide<T> {

    T  getIdentity(Session session);
}
