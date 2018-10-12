package com.qworldr.mmorpg;

import com.qworldr.mmorpg.provider.EntityProvider;
import com.qworldr.mmorpg.provider.ResourceProvider;
import com.qworldr.mmorpg.utils.ReflectUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConfigurationResourceProviderManager implements InitializingBean,InstantiationAwareBeanPostProcessor, PriorityOrdered, BeanFactoryAware {
    private String suffix;
    private String path;
    private ConfigurableListableBeanFactory beanFactory;
    private String scanPackage;
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE-3 ;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, ResourceProvider> beansOfType = beanFactory.getBeansOfType(ResourceProvider.class);
        Set<Class> classes = new HashSet<>();
        beansOfType.values().forEach(reesourceProvider -> {
            Class<?> genericType = ReflectUtils.getGenericType(reesourceProvider.getClass());
            if (genericType != null) {
                classes.add(genericType);
            }
        });
        System.out.println(suffix);
        System.out.println(path);
    }
    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }
}
