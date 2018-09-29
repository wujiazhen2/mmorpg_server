package com.qworldr.bean;

import com.qworldr.session.Session;

import java.security.spec.PKCS8EncodedKeySpec;

/**
 * 会话转化为标识用户对象。例如Player
 * @param <T>
 */
public interface IdentityProvide<T extends Identity> {

    T  getIdentity(Session session);
}
