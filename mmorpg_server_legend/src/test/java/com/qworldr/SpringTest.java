package com.qworldr;

import com.google.auto.value.AutoAnnotation;
import com.qworldr.annotation.Protocal;
import com.qworldr.logic.account.protocal.LoginReq;
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
