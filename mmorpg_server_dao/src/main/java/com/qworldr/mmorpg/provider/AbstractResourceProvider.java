package com.qworldr.mmorpg.provider;

import com.google.common.collect.ImmutableMap;
import com.qworldr.mmorpg.meta.ResourceMetaData;
import com.qworldr.mmorpg.utils.ReflectUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @param <T>  资源类类型
 * @param <ID> 资源类ID类型
 * @author wujiazhen
 * 资源提供者抽象类，实现类实现loadAll方法来实现加载文件的逻辑
 */
public abstract class AbstractResourceProvider<T, ID> extends ResourceProvider<T, ID> {
    protected Class<T> entityClass;
    protected ResourceMetaData resourceMetaData;
    protected volatile Map<ID, T> resourceMap = new HashMap<>();

    public AbstractResourceProvider() {
        this.entityClass = (Class<T>) ReflectUtils.getSuperGenericType(getClass());
    }

    @Override
    protected void reload() {
        try {
            if (resourceMetaData.getResources() == null || resourceMetaData.getResources().length == 0) {
                throw new FileNotFoundException(resourceMetaData.getPath());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resourceMap = ImmutableMap.copyOf(loadAll(resourceMetaData));
        afterLoad();
    }

    @Override
    public T get(ID id) {
        return resourceMap.get(id);
    }

    @Override
    public Map<ID, T> asMap() {
        return resourceMap;
    }

    protected void afterLoad() {
    }

    protected abstract Map<ID, T> loadAll(ResourceMetaData resourceMetaData);

    public ResourceMetaData getResourceMetaData() {
        return resourceMetaData;
    }
}
