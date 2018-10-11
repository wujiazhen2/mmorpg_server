package com.qworldr.mmorpg.provider;

import com.qworldr.mmorpg.meta.ResourceMetaData;

import java.util.Map;

public class ConfigurationResourceProvider<T,ID>  extends  AbstractResourceProvider<T,ID>{
    @Override
    protected Map<ID, T> loadAll(ResourceMetaData resourceMetaData) {
        return null;
    }
}
