package com.qworldr.mmorpg.reader;

import com.qworldr.mmorpg.enu.ReaderType;
import com.qworldr.mmorpg.meta.ResourceMetaData;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface  Reader {
     Map read(ResourceMetaData resourceMetaData) throws  Exception;
     ReaderType getReaderType();
}
