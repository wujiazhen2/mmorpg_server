package com.qworldr.mmorpg.identify;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * uuid的主键生成策略
 *
 * @author wujiazhen
 */
@Component
public class UUIDGeneratorStrategy implements GeneratorStrategy<String> {
    @Override
    public String generatorKey() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getType() {
        return "uuid";
    }
}
