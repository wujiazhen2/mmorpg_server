package com.qworldr;

import com.qworldr.exception.ServerOpenException;
import com.qworldr.server.GameServer;
import com.qworldr.server.IServer;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GameStart {

    public  static  void main(String[] args){
        ApplicationContext  applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ((ClassPathXmlApplicationContext) applicationContext).start();
        IServer gameServer = applicationContext.getBean(IServer.class);
        try {
            gameServer.start(4010);
        } catch (ServerOpenException e) {
            e.printStackTrace();
        }
    }
}
