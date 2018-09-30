package com.qworldr.mmorpg.utils;

import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtils {


    public static Class<?> getGenericType(Class clazz){
        ResolvableType resolvableType = ResolvableType.forClass(clazz);
        Class<?> resolve = resolvableType.getSuperType().getGeneric(0).resolve();
        return resolve;
    }

    public static Class<?> getSuperGenericType(Class clazz){
        ResolvableType resolvableType = ResolvableType.forClass(clazz);
        Class<?> resolve = resolvableType.getSuperType().getGeneric(0).resolve();
        return resolve;
    }


}
