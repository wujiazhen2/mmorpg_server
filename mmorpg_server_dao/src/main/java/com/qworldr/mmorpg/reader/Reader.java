package com.qworldr.mmorpg.reader;

import com.qworldr.mmorpg.enu.ReaderType;
import com.qworldr.mmorpg.meta.ResourceMetaData;

import java.util.Map;

public interface  Reader {
     Map<String,String> read(ResourceMetaData resourceMetaData);
     ReaderType getReaderType();
}
