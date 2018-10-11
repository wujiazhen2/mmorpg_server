package com.qworldr.mmorpg.enu;

public enum ReaderType {
    PROPERTIES("properties")
    ;


    private String suffix;
     ReaderType(String suffix){
        this.suffix=suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}
