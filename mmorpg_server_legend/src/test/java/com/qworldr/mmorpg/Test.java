package com.qworldr.mmorpg;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.List;

/**
 * @author wujiazhen
 */
public class Test {

    private int id;
    private List<Integer> ids;

    public int getId() {
        return id;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public static void main(String[] args) {
        String msg = "[" +
                "{" +
                "\"id\":1," +
                "\"ids\":[1,2]" +
                "}" +
                "]";
        Test obj = JSON.parseObject(msg, new TypeReference<Test>() {
        }.getType(), Feature.SupportNonPublicField);
    }
}
