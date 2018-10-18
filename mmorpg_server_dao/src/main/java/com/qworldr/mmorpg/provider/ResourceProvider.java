package com.qworldr.mmorpg.provider;

import java.util.List;
import java.util.Map;

public interface ResourceProvider<T, ID> extends DataProvider<T, ID> {

    Map<ID,T> asMap();
    void reload() throws Exception;
}
