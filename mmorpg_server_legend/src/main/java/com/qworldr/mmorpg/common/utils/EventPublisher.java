package com.qworldr.mmorpg.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EventListener;

/**
 * @Author wujiazhen
 */
@Component
public class EventPublisher  implements ApplicationEventPublisher ,ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;
    private static  EventPublisher self;
    @PostConstruct
    public void init(){
        self=this;
    }
    public static  EventPublisher getInstance(){
        return  self;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher=applicationEventPublisher;
    }
    @Override
    public void publishEvent(Object event) {
        applicationEventPublisher.publishEvent(event);
    }
}
