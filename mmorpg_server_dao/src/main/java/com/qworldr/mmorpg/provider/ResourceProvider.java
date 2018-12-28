package com.qworldr.mmorpg.provider;

import java.util.Map;

public abstract class ResourceProvider<T, ID> implements DataProvider<T, ID> {

    public abstract Map<ID, T> asMap();

    /**
     * reload方法，加载资源文件。
     */
    protected abstract void reload();
}
