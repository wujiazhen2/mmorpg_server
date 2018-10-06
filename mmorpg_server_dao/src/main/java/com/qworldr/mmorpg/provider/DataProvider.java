package com.qworldr.mmorpg.provider;

public interface DataProvider<T,ID> {

    T get(ID id);
}
