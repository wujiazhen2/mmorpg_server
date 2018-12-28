package com.qworldr.mmorpg.reader;


import com.qworldr.mmorpg.enu.ReaderType;
import com.qworldr.mmorpg.meta.ResourceMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReaderManager implements BeanFactoryAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReaderManager.class);
    private Map<ReaderType, Reader> readerMap = new HashMap<>();
    private ConfigurableListableBeanFactory beanFactory;
    private static ReaderManager readerManager;

    public void init() {
        readerManager = this;
        Map<String, Reader> beansOfType = beanFactory.getBeansOfType(Reader.class);
        beansOfType.forEach((s, reader) -> {
            registerReader(reader);
        });
    }

    public static ReaderManager getInstance() {
        return readerManager;
    }

    public Reader registerReader(Reader reader) {
        return readerMap.put(reader.getReaderType(), reader);
    }

    public Reader getReader(ReaderType readerType) {
        return readerMap.get(readerType);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    public Map read(ResourceMetaData resourceMetaData) throws Exception {
        Reader reader = getReader(resourceMetaData.getReaderType());
        return reader.read(resourceMetaData);
    }
}
