package com.qworldr.mmorpg;

import com.qworldr.mmorpg.exception.ServerOpenException;
import com.qworldr.mmorpg.server.IServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GameStart {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IServer gameServer = applicationContext.getBean(IServer.class);
        try {
            gameServer.start(4010);
        } catch (ServerOpenException e) {
            e.printStackTrace();
        }
    }
}
