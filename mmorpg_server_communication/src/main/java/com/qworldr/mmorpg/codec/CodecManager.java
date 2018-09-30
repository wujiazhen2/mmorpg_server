package com.qworldr.mmorpg.codec;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.google.common.collect.Maps;
import com.qworldr.mmorpg.annotation.Protocal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class CodecManager implements ApplicationListener<ContextRefreshedEvent> {

    private static CodecManager seft;
    private static final Map<Short,Codec> codecs = Maps.newHashMap();
    public static  CodecManager getInstance(){
        return seft;
    }
    @PostConstruct
    public void init(){
        seft=this;
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Protocal.class);
        beansWithAnnotation.forEach((key,value)->{
            Class<?> aClass = value.getClass();
            Protocal annotation = aClass.getAnnotation(Protocal.class);
            Codec<?> codec = ProtobufProxy.create(aClass);
            codecs.put(annotation.value(),codec);
        });
    }
    public Codec getCodec(Short protocalId){
        return codecs.get(protocalId);
    }

}
