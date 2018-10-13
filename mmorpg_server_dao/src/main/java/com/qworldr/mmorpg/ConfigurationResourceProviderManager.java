package com.qworldr.mmorpg;

import com.qworldr.mmorpg.enu.ReaderType;
import com.qworldr.mmorpg.meta.ResourceFormat;
import com.qworldr.mmorpg.meta.ResourceMetaData;
import com.qworldr.mmorpg.provider.ConfigurationResourceProvider;
import com.qworldr.mmorpg.provider.ProviderProxyFactory;
import com.qworldr.mmorpg.provider.ResourceProvider;
import com.qworldr.mmorpg.reader.ReaderManager;
import com.qworldr.mmorpg.utils.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConfigurationResourceProviderManager implements InitializingBean, InstantiationAwareBeanPostProcessor, PriorityOrdered, BeanFactoryAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationResourceProviderManager.class);
    public static final String CLASS = "/*.class";
    public static final Class provideClass = ConfigurationResourceProvider.class;
    public static final String RESOURCE_META_DATA = "resourceMetaData";
    private String suffix;
    private String path;
    private ConfigurableListableBeanFactory beanFactory;
    private String scanPackage;
    private ReaderType readerType;

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 3;
    }

    /**
     *  读取资源类并加载资源，生成资源提供者对象代理，注册进Spring。可以通过@Autowire注入其他地方使用。
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //先初始化Readermanager;
        ReaderManager bean = beanFactory.getBean(ReaderManager.class);
        bean.init();
        ResourceFormat resourceFormat = new ResourceFormat(suffix, path, readerType);
        //反射注入resourceMeta 和reader
        Field resourceMetaDataField = ReflectionUtils.findField(provideClass,RESOURCE_META_DATA);
        resourceMetaDataField.setAccessible(true);
        Map<String, ResourceProvider> beansOfType = beanFactory.getBeansOfType(ResourceProvider.class);
        Set<Class> classes = new HashSet<>();
        for (ResourceProvider resourceProvider : beansOfType.values()) {
            Class<?> genericType = ReflectUtils.getGenericType(resourceProvider.getClass());
            if (genericType != null) {
                classes.add(genericType);
                injectResourceMetaData(resourceFormat, resourceMetaDataField, genericType, resourceProvider);
                //TODO　多线程加载
                resourceProvider.reload();
            }
        }
        if (StringUtils.isEmpty(scanPackage)) {
            throw new IllegalArgumentException("缺少scanPackage参数");
        }
        //扫包资源类，加载资源文件
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(patternResolver);
        Resource[] resources = patternResolver.getResources(scanPackage.replaceAll("\\.", "/")+ CLASS);

        MetadataReader metadataReader;
        Class resourceClass;
        Class keyClass=null;
        for (Resource resource : resources) {
            metadataReader = metadataReaderFactory.getMetadataReader(resource);
            if (!metadataReader.getAnnotationMetadata().hasAnnotation(com.qworldr.mmorpg.anno.Resource.class.getName())) {
                continue;
            }
            resourceClass=Class.forName(metadataReader.getClassMetadata().getClassName());
            //已有实现类不需要代理。
            if(classes.contains(resourceClass)){
                continue;
            }
            Field[] declaredFields = resourceClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if(declaredField.getAnnotation(Id.class)!=null){
                    keyClass=declaredField.getType();
                    break;
                }
            }
            if(keyClass==null){
                throw new IllegalArgumentException(String.format("%s资源类没有id字段，请通过@Id标识id字段",resourceClass.getName()));
            }
            ResourceProvider resourceProviderProxy = ProviderProxyFactory.getInstance().createResourceProviderProxy(provideClass.getName(), resourceClass,ReflectUtils.wrapType(keyClass));
            //给provider注入ResourceMetaData
            injectResourceMetaData(resourceFormat, resourceMetaDataField, resourceClass, resourceProviderProxy);
            //注册进Spring容器
            this.beanFactory.registerSingleton(resourceProviderProxy.getClass().getName(),resourceProviderProxy);
            //TODO　多线程加载
            resourceProviderProxy.reload();

        }


    }

    private void injectResourceMetaData(ResourceFormat resourceFormat, Field resourceMetaDataField, Class resourceClass, ResourceProvider resourceProviderProxy) {
        ResourceMetaData resourceMetaData = ResourceMetaData.valueOf(resourceClass, resourceFormat);
        try {
            resourceMetaDataField.set(resourceProviderProxy, resourceMetaData);
        } catch (IllegalAccessException e) {
            LOGGER.error("resourceProvider注入失败", e);
        }
    }

    public void setReaderType(ReaderType readerType) {
        this.readerType = readerType;
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
