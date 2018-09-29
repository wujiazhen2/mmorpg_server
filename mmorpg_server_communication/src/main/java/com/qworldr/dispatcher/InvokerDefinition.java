package com.qworldr.dispatcher;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qworldr.annotation.Protocal;
import com.qworldr.session.Session;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class InvokerDefinition {

    private Object instance;
    private Method method;

    public InvokerDefinition(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }

    public Object invoke(Session session, Object protocal){
        Class<?>[] parameterTypes = method.getParameterTypes();
        List<Object> params = Lists.newArrayList();
        for(Class type:parameterTypes){
            if(type.isAssignableFrom(Session.class)){
                params.add(session);
            }else if(type.getAnnotation(Protocal.class) !=null) {
                params.add(protocal);
            }else {
                throw new IllegalArgumentException("SocketRequest方法参数只能是Session子类，Protocal类");
            }
        }
        try {
            return  method.invoke(this.instance,params.toArray());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return  null;
    }


}
