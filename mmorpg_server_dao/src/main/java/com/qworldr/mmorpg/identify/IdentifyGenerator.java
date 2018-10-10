package com.qworldr.mmorpg.identify;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class IdentifyGenerator implements ApplicationContextAware {
    private Map<String, GeneratorStrategy> keyStrategy=Maps.newHashMap();
    private ApplicationContext applicationContext;
    private static IdentifyGenerator identifyGenerator;
    public static IdentifyGenerator getInstance(){
        return identifyGenerator;
    }
    @PostConstruct
    public void init(){
        Map<String, GeneratorStrategy> beansOfType = applicationContext.getBeansOfType(GeneratorStrategy.class);
        for (GeneratorStrategy value : beansOfType.values()) {
            keyStrategy.put(value.getType(),value);
        }
        identifyGenerator=this;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public  GeneratorStrategy getGeneratorStrategy(String type){
        return keyStrategy.get(type);
    }
}
