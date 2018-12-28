package com.qworldr.mmorpg.provider;

/**
 * @author wujiazhen
 * 用于创建实体
 */
public interface ICreator<T,ID> {

    T create(ID id);
}
