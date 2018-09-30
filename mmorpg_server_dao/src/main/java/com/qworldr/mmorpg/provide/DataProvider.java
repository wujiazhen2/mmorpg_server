package com.qworldr.mmorpg.provide;

public interface DataProvider<T,ID> {

    T get(ID id);
}
