package com.qworldr.mmorpg.reader;

import com.qworldr.mmorpg.enu.ReaderType;
import com.qworldr.mmorpg.meta.ResourceMetaData;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author wujiazhen
 */
@Component
public class MapReader implements Reader{
    @Override
    public Map read(ResourceMetaData resourceMetaData) throws Exception {
        return null;
    }

    @Override
    public ReaderType getReaderType() {
        return ReaderType.MAP;
    }
}
