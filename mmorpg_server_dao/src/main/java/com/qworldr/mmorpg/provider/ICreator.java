package com.qworldr.mmorpg.provider;

/**
 * @Author wujiazhen
 */
public interface ICreator<T,ID> {

    T create(ID id);
}
