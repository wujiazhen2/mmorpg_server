package com.qworldr.mmorpg.provider;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.qworldr.mmorpg.anno.Resource;
import com.qworldr.mmorpg.meta.ResourceMetaData;
import com.qworldr.mmorpg.reader.Reader;
import com.qworldr.mmorpg.utils.ReflectUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractResourceProvider<T,ID> implements ResourceProvider<T,ID> {
    protected Class<T> entityClass;
    protected ResourceMetaData resourceMetaData;
    protected volatile Map<ID,T> resourceMap = new HashMap<>();
    public AbstractResourceProvider(){
        this.entityClass= (Class<T>) ReflectUtils.getSuperGenericType(getClass());
    }
    @Override
    public void reload() throws Exception {
        if(resourceMetaData.getResources()==null || resourceMetaData.getResources().length==0){
            throw new FileNotFoundException(resourceMetaData.getPath());
        }
        resourceMap = ImmutableMap.copyOf(loadAll(resourceMetaData));
        afterLoad();
    }

    @Override
    public T get(ID id) {
        return resourceMap.get(id);
    }

    @Override
    public Map<ID,T> asMap() {
        return resourceMap;
    }

    protected void afterLoad(){}
    protected abstract Map<ID,T> loadAll(ResourceMetaData resourceMetaData) throws Exception;

    public ResourceMetaData getResourceMetaData() {
        return resourceMetaData;
    }
}
