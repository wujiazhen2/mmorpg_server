package com.qworldr.mmorpg.provider;

/**
 * @param <T>
 * @param <ID>
 */
public interface DataProvider<T,ID> {

    T get(ID id);


}
