package com.qworldr.mmorpg.identify;

/**
 * 主键生成测录基类
 *
 * @param <T> 主键类型
 */
public interface GeneratorStrategy<T> {

    T generatorKey();

    String getType();
}
