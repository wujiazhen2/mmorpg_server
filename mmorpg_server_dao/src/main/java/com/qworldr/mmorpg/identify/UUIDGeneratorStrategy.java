package com.qworldr.mmorpg.identify;

import org.hibernate.id.UUIDGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;
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
