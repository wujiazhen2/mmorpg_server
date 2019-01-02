package com.qworldr.mmorpg.logic.skill.manager;

import com.qworldr.mmorpg.logic.skill.resource.EffectResource;
import com.qworldr.mmorpg.provider.ResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wujiazhen
 * @Date 2019/1/2
 */
@Component
public class EffectManager {
    @Autowired
    private ResourceProvider<EffectResource, Integer> effectResourceProvider;

    private static EffectManager instance;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static EffectManager getInstance() {
        return instance;
    }

    public EffectResource getEffectResource(int effectId) {
        return effectResourceProvider.get(effectId);
    }
}
