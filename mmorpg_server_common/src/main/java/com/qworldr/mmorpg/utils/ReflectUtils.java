package com.qworldr.mmorpg.utils;

import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectUtils {

    public static Class<?> getGenericType(Class clazz){
        Class<?> genericType=getSuperGenericType(clazz);
        if(genericType ==null){
             genericType = getInterfaceGenericType(clazz);
        }
        return genericType;
    }

    public static Class<?> getInterfaceGenericType(Class clazz){
        ResolvableType resolvableType = ResolvableType.forClass(clazz);
        ResolvableType[] interfaces = resolvableType.getInterfaces();
        if(interfaces==null || interfaces.length==0) {
            return null;
        }
        Class<?> resolve = interfaces[0].getGeneric(0).resolve();
        return resolve;
    }

    public static Class<?> getSuperGenericType(Class clazz){
        ResolvableType resolvableType = ResolvableType.forClass(clazz);
        Class<?> resolve = resolvableType.getSuperType().getGeneric(0).resolve();
        return resolve;
    }

    public static boolean isSuperInterface(Class sub,Class sup){
        Class[] interfaces = sub.getInterfaces();
        for (Class inter:interfaces){
            if(inter.isAssignableFrom(sup)){
                return true;
            }
        }
        return false;
    }


}
