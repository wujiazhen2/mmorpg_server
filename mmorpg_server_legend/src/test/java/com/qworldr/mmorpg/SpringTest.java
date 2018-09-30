package com.qworldr.mmorpg;

import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.logic.account.protocal.LoginReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringTest {

    @Autowired
    private LoginReq req;
    @Test
    public void test(){
        System.out.println(req.getClass().getAnnotation(Protocal.class));
    }

}
