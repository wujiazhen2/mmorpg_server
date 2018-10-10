package com.qworldr.mmorpg.provider;

public interface ResourceProvider<T,ID> extends DataProvider<T, ID> {


    void reload();
}
