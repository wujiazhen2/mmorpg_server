package com.qworldr.mmorpg;

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

public class ConfigurationResourceProviderManager implements InitializingBean,InstantiationAwareBeanPostProcessor, PriorityOrdered, BeanFactoryAware {
    private String suffix;
    private String path;
    private ConfigurableListableBeanFactory beanFactory;
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE-3 ;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
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

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }
}
