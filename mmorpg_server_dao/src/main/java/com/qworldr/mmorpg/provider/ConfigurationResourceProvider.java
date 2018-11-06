package com.qworldr.mmorpg.provider;

import com.qworldr.mmorpg.meta.ResourceMetaData;
import com.qworldr.mmorpg.reader.Reader;
import com.qworldr.mmorpg.reader.ReaderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationResourceProvider<T,ID>  extends  AbstractResourceProvider<T,ID>{
    private static  final Logger LOGGER = LoggerFactory.getLogger(ConfigurationResourceProvider.class);
    @Override
    protected Map<ID, T> loadAll(ResourceMetaData resourceMetaData)  {
        Map<ID, T> data = null;
        try {
            data = (Map<ID, T>) ReaderManager.getInstance().read(resourceMetaData);
        } catch (Exception e) {
            LOGGER.error(String.format("%s读取失败",resourceMetaData.getPath()),e);
            throw new RuntimeException(e);
        }
        //如果实现了AfterLoad接口的资源类,加载完运行afterload方法
        if(IAfterLoad.class.isAssignableFrom(this.entityClass)){
            data.forEach((k,v)->{
                ((IAfterLoad)v).afterLoad();
            });
        }
        return data;
    }
}
