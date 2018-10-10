package com.qworldr.mmorpg;

import com.qworldr.mmorpg.logic.player.entity.PlayerEntity;
import com.qworldr.mmorpg.provider.EntityProvider;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringTest {

    @Autowired
    private SessionFactory req;
    @Autowired
    private EntityProvider<PlayerEntity,String> playerEntity;
    @Test
    public void test(){
        System.out.println(req);
        System.out.println(playerEntity);
    }

}
