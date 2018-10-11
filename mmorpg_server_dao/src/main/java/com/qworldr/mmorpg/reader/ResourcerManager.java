package com.qworldr.mmorpg.reader;


import com.qworldr.mmorpg.convert.DefaultConvert;
import com.qworldr.mmorpg.meta.ResourceMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ResourcerManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcerManager.class);
    private Map<String, Reader> readerMap = new HashMap<>();
    private static ResourcerManager readerManager ;
    @Autowired
    private DefaultConvert defaultConvert;
    private Map<String, ResourceMetaData> configResourceMap;
    private ResourcerManager() {
        //注册Reader
       this.registerReader(new PropertiesReader());
    }
    @PostConstruct
    public void init(){
        readerManager=this;
    }
    public static ResourcerManager getInstance() {
        return readerManager;
    }

    public Reader registerReader(Reader reader) {
        return readerMap.put(reader.getReaderType().getSuffix(), reader);
    }

    public Reader getReader(String suffix) {
        return readerMap.get(suffix);
    }


    public <T> T injectProperty(T t, ResourceMetaData configResource) {
        //保存configResource用于重新加载时使用
        if(configResourceMap==null){
            configResourceMap=new HashMap<>();
        }
        try {
            configResourceMap.put(configResource.getFile().getPath(),configResource);
        } catch (IOException e) {
            LOGGER.error(String.format("%d读取失败",configResource.getPath()));
        }
        Reader reader = getReader(configResource.getSuffix());
        Map<String, String> read = reader.read(configResource);
        read.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(t.getClass(), k);
            Optional.ofNullable(field).ifPresent(f ->{
                Class type=field.getType();
                f.setAccessible(true);
                try {
                    f.set(t, type.equals(String.class) ?v:defaultConvert.covert(v, type));
                } catch (IllegalAccessException e) {
                    LOGGER.error(String.format("字段%s转化类型错误，目标类型：%s，值：%s", k, type.toString(), String.valueOf(v)));
                }
            } );
        });
        return t;
    }


    public ResourceMetaData getConfigResource(String path) {
        return this.configResourceMap.get(path);
    }
}
