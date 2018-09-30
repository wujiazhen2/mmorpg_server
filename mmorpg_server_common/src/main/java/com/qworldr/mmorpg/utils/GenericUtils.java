package com.qworldr.mmorpg.utils;

import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtils {


    public static Class<?> getGenericType(Class clazz){
        ResolvableType resolvableType = ResolvableType.forClass(clazz);
        Class<?> resolve = resolvableType.getInterfaces()[0].getGeneric(0).resolve();
        return resolve;
    }

    public static Type getSuperGenericType(Class clazz){
        Type types = clazz.getGenericSuperclass();
        Type actualTypeArgument = ((ParameterizedType) types).getActualTypeArguments()[0];
        return actualTypeArgument;
    }
}
