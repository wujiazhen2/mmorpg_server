package com.qworldr.mmorpg;

import com.qworldr.mmorpg.exception.ServerOpenException;
import com.qworldr.mmorpg.server.IServer;
import io.netty.channel.ChannelFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

public class GameStart {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameStart.class);
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IServer gameServer = applicationContext.getBean(IServer.class);
        try {
            ChannelFuture start = gameServer.start(4010);
            stopWatch.stop();
            LOGGER.debug("启动耗时{}s", stopWatch.getTotalTimeSeconds());
            start.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ServerOpenException e) {
            e.printStackTrace();
        }
    }
}
