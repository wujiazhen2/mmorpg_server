package com.qworldr.mmorpg.utils;

/**
 * @author wujiazhen
 */
public class HashUtils {

    /**
     *  高低位异或 再截取高位
     * @param value
     * @return
     */
    public static int hash(long value){
        return (int)(value>>>32 ^ value);
    }


}
