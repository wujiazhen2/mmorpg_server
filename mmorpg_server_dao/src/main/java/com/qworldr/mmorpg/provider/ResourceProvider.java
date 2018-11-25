package com.qworldr.mmorpg.provider;

import java.util.List;
import java.util.Map;

public abstract class ResourceProvider<T, ID> implements DataProvider<T, ID> {

    public abstract Map<ID,T> asMap();
    abstract void reload() ;
}
