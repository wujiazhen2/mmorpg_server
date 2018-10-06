package com.qworldr.mmorpg;

import com.qworldr.mmorpg.logic.account.controller.AccountController;
import com.qworldr.mmorpg.logic.account.entity.AccountEntity;
import com.qworldr.mmorpg.logic.player.entity.PlayerEntity;
import com.qworldr.mmorpg.provider.EntityProvider;
import com.qworldr.mmorpg.provider.HibernateEntityProvider;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
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
