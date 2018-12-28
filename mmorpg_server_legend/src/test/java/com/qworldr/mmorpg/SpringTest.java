package com.qworldr.mmorpg;

import com.qworldr.mmorpg.logic.player.service.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@DependsOn({"resourceProviderManager"})
public class SpringTest {

    @Autowired
    private PlayerService playerService;

    @Test
    public void test() {
        System.out.println(playerService);
    }

}
