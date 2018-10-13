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
    protected Map<ID, T> loadAll(ResourceMetaData resourceMetaData) throws Exception {
        Map<ID, T> data = null;
        try {
            data = (Map<ID, T>) ReaderManager.getInstance().read(resourceMetaData);
        } catch (Exception e) {
            LOGGER.error(String.format("%s读取失败",resourceMetaData.getPath()),e);
            throw e;
        }
        return data;
    }
}
