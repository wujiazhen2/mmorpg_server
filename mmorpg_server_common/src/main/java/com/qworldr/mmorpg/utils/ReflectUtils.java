package com.qworldr.mmorpg.utils;

import org.springframework.core.ResolvableType;

import java.lang.reflect.Field;
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
    public static Class<?> getSuperGenericType(Class clazz,int i){
        ResolvableType resolvableType = ResolvableType.forClass(clazz);
        Class<?> resolve = resolvableType.getSuperType().getGeneric(i).resolve();
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

    public static  Class wrapType(Class cls){
        if(cls.equals(int.class)){
            return Integer.class;
        }else if(cls.equals(byte.class)){
            return Byte.class;
        }else if(cls.equals(short.class)){
            return Short.class;
        }else if(cls.equals(long.class)){
            return Long.class;
        }else if(cls.equals(char.class)){
            return Character.class;
        }else if(cls.equals(float.class)){
            return Float.class;
        }else if(cls.equals(double.class)){
            return Double.class;
        }else if(cls.equals(boolean.class)){
            return Boolean.class;
        }
        return cls;
    }

}
