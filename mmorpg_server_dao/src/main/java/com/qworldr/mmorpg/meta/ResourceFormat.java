package com.qworldr.mmorpg.meta;

import com.qworldr.mmorpg.enu.ReaderType;

public class ResourceFormat {
    private String suffix;
    private String path;
    private ReaderType readerType;

    public ResourceFormat(String suffix, String path, ReaderType readerType) {
        this.suffix = suffix;
        this.path = path;
        this.readerType = readerType;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ReaderType getReaderType() {
        return readerType;
    }

    public void setReaderType(ReaderType readerType) {
        this.readerType = readerType;
    }
}
