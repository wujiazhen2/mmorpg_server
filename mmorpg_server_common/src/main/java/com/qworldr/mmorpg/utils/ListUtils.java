package com.qworldr.mmorpg.utils;

import java.util.List;

public class ListUtils {


    public static <T> T getFirst(List<T> list){
        if(list==null || list.size()==0){
            return null;
        }
       return list.get(0);
    }
}
