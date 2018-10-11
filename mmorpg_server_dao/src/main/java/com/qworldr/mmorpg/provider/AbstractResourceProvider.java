package com.qworldr.mmorpg.provider;

import com.qworldr.mmorpg.anno.Resource;
import com.qworldr.mmorpg.meta.ResourceMetaData;
import com.qworldr.mmorpg.utils.ReflectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractResourceProvider<T,ID> implements ResourceProvider<T,ID> {
    private Class<T> entityClass;
    private ResourceMetaData resourceMetaData;
    private volatile Map<ID,T> resourceMap = new HashMap<>();
    public AbstractResourceProvider(){
        this.entityClass= (Class<T>) ReflectUtils.getSuperGenericType(getClass());
        resourceMetaData=ResourceMetaData.valueOf(entityClass);
    }
    @Override
    public void reload() {
        resourceMap = loadAll(resourceMetaData);
    }

    @Override
    public T get(ID id) {
        return resourceMap.get(id);
    }

    protected abstract Map<ID,T> loadAll(ResourceMetaData resourceMetaData);
}
