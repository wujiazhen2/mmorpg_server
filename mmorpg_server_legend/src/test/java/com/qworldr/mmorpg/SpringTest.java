package com.qworldr.mmorpg;

import com.qworldr.mmorpg.common.identify.SnowflakeIdentifyGeneratorStrategy;
import com.qworldr.mmorpg.logic.player.entity.PlayerEntity;
import com.qworldr.mmorpg.logic.player.event.PlayerModuleInitializeEvent;
import com.qworldr.mmorpg.logic.player.resource.PlayerInitializationResource;
import com.qworldr.mmorpg.logic.player.resource.PlayerLevelResource;
import com.qworldr.mmorpg.logic.player.service.PlayerService;
import com.qworldr.mmorpg.provider.EntityProvider;
import com.qworldr.mmorpg.provider.ResourceProvider;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@DependsOn({"resourceProviderManager"})
public class SpringTest {

    @Autowired
    private PlayerService playerService;
    @Test
    public void test(){
        System.out.println(playerService);
    }

}
