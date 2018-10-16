package com.qworldr.mmorpg;

import com.qworldr.mmorpg.entity.IEntity;
import com.qworldr.mmorpg.provider.EntityProvider;
import com.qworldr.mmorpg.provider.HibernateEntityProvider;
import com.qworldr.mmorpg.provider.ProviderProxyFactory;
import com.qworldr.mmorpg.type.JsonType;
import com.qworldr.mmorpg.utils.ReflectUtils;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class EntityProviderSessionFactoryBean extends LocalSessionFactoryBean implements InstantiationAwareBeanPostProcessor,  PriorityOrdered {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityProviderSessionFactoryBean.class);
    public static final String DEFAULT_ENTITYPROVIER = HibernateEntityProvider.class.getName();
    private String entityProviderName;
    private ConfigurableListableBeanFactory beanFactory;
    @Override
    public void afterPropertiesSet() throws IOException {
        super.afterPropertiesSet();
        Configuration configuration = getConfiguration();
        configuration.registerTypeOverride(new JsonType(),new String[]{"json"});


        Map<String, EntityProvider> beansOfType = beanFactory.getBeansOfType(EntityProvider.class);
        Set<Class> classes = new HashSet<>();

        beansOfType.values().forEach(entityProvider -> {
            Class<?> genericType = ReflectUtils.getGenericType(entityProvider.getClass());
            if (genericType != null) {
                classes.add(genericType);
            }
        });
        MetadataSources metadataSources = getMetadataSources();
        Collection<Class<?>> annotatedClasses = metadataSources.getAnnotatedClasses();
        annotatedClasses.forEach(clazz -> {
            //已有该泛型实现类，不用再生成代理类
            if (classes.contains(clazz)) {
                return;
            }
            Object o = null;
            try {
                Class<?> genericType = ReflectUtils.getInterfaceGenericType(clazz);
                if (genericType == null) {
                    LOGGER.warn("{} 没有继承{}接口或没有设置泛型，不能生成相应的EntityProvider", clazz.getName(), IEntity.class.getName());
                    return;
                }
                o = ProviderProxyFactory.getInstance().createEntityProviderProxy(getEntityProviderName(), clazz, genericType);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(o instanceof HibernateDaoSupport){
                ((HibernateDaoSupport) o).setSessionFactory(getObject());
            }
            this.beanFactory.registerSingleton(o.getClass().getName(),o);
        });
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    public String getEntityProviderName() {
        return StringUtils.isEmpty(entityProviderName) ? DEFAULT_ENTITYPROVIER : entityProviderName;
    }

    public void setEntityProviderName(String entityProviderName) throws ClassNotFoundException {
        Class<?> aClass = Class.forName(entityProviderName);
        if (aClass.isAssignableFrom(EntityProvider.class)) {
            throw new IllegalArgumentException(String.format("%s必须是%s的子类", entityProviderName, EntityProvider.class.getName()));
        }
        this.entityProviderName = entityProviderName;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE-3 ;
    }


}
