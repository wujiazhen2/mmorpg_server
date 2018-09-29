package com.qworldr.bean;

import java.security.spec.PKCS8EncodedKeySpec;

/**
 * 提供该会话用户的唯一表示对象。例如Player
 * @param <T>
 */
public interface IdentityProvide<T extends Identity,PK> {

    T  getIdentity(PK id);
}
