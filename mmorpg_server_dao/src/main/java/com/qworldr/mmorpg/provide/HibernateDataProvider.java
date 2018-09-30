package com.qworldr.mmorpg.provide;

import com.qworldr.mmorpg.utils.GenericUtils;

public class HibernateDataProvider<T,ID> implements DataProvider<T,ID> {
    private Class<T>  entityClass;
    public HibernateDataProvider(){
        entityClass = (Class<T>) GenericUtils.getSuperGenericType(this.getClass());
    }
    public T get(ID id) {
        return null;
    }
}
