package com.qworldr.bean;

/**
 * 提供该会话用户的唯一表示对象。例如Player
 * @param <T>
 */
public interface IdentityProvide<T extends Identity> {

    T  getIdentity(Object id);
}
