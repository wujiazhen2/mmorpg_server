package com.qworldr.mmorpg.reader;


import com.qworldr.mmorpg.enu.ReaderType;
import com.qworldr.mmorpg.meta.ResourceMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader implements Reader {

    private final Logger logger = LoggerFactory.getLogger(PropertiesReader.class);

    @Override
    public Map<Object, Object> read(ResourceMetaData resourceMetaData) {
        Properties properties = new Properties();
        Map<Object, Object> map = new HashMap<>();
        Resource[] resources = null;
        try {
            resources = resourceMetaData.getResources();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Arrays.stream(resources).forEach(resource -> {
            try {
                properties.load(resource.getInputStream());
                properties.forEach((a, b) -> {
                    map.put(String.valueOf(a), String.valueOf(b));
                });
            } catch (IOException e) {
                logger.error(String.format("%s配置文件读取失败", resourceMetaData.getPath()));
                e.printStackTrace();
            }
        });
        return map;
    }

    @Override
    public ReaderType getReaderType() {
        return ReaderType.PROPERTIES;
    }
}
