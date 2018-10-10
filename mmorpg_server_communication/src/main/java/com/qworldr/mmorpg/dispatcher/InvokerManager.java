package com.qworldr.mmorpg.dispatcher;

import com.google.common.collect.Maps;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.annotation.SocketRequest;
import com.qworldr.mmorpg.annotation.SocketController;
import com.qworldr.mmorpg.bean.IdentityProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

@Component
public class InvokerManager implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvokerManager.class);
    //spring初始化结束后就不会再写只读，应该没线程安全问题
    private Map<Class, InvokerDefinition> protocal2Invoker = Maps.newHashMap();

    public InvokerDefinition getInvokerDefintion(Class protocal) {
        return protocal2Invoker.get(protocal);
    }
    @Autowired(required = false)
    private IdentityProvider identityProvide;

    @PostConstruct
    public void init(){
        if(identityProvide!=null){
            try {
                Type[] genericInterfaces = identityProvide.getClass().getGenericInterfaces();
                Type actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments()[0];
                InvokerDefinition.setIdentifyClass(actualTypeArguments.getTypeName());
                InvokerDefinition.setProvide(identityProvide);
            }catch (Exception e){
                LOGGER.debug("identityProvide缺少泛型");
            }
        }
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        SocketController annotation = aClass.getAnnotation(SocketController.class);
        if (annotation == null) {
            return bean;
        }
        InvokerDefinition invokerDefinition;
        ReflectionUtils.doWithMethods(aClass, method -> {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class protoType = null;
            for (Class type : parameterTypes) {
                if (type.getAnnotation(Protocal.class) != null) {
                    protoType = type;
                    break;
                }
            }
            if (protoType == null) {
                LOGGER.debug("{}的{}方法没有协议参数,不是SocketRequest", aClass.getName(), method.getName());
                return;
            }
            protocal2Invoker.put(protoType, new InvokerDefinition(bean, method));
        }, method -> method.getAnnotation(SocketRequest.class) != null);
        return null;
    }
}
