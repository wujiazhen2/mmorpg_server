package com.qworldr.mmorpg.identify;

public interface GeneratorStrategy<T> {

    T generatorKey();

    String getType();
}
