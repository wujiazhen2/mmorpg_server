package com.qworldr.mmorpg.server;

import com.qworldr.mmorpg.exception.ServerOpenException;
import com.qworldr.mmorpg.handler.DispatchHandler;
import com.qworldr.mmorpg.handler.SessionHandler;
import com.qworldr.mmorpg.handler.LimitFlowHandler;
import com.qworldr.mmorpg.handler.PacketCodec;
import com.qworldr.mmorpg.thread.DispatcherExecutor;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GameServer implements IServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameServer.class);
    private List<ChannelHandler> channelHandlers;
    private DispatcherExecutor  dispatcherExecutor;
    private ChannelFuture future;
    @Autowired
    private DispatchHandler dispatchHandler;
    @Autowired
    private LimitFlowHandler limitFlowHandler;
    @Autowired
    private SessionHandler sessionHandler;
    public void start(int port) throws ServerOpenException {
        if(dispatcherExecutor==null){
            LOGGER.error("GameServer.dispatcherExecutor不能为null");
            return;
        }
        dispatchHandler.setDispatcherExecutor(dispatcherExecutor);
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .channel(NioServerSocketChannel.class)
                .group(bossGroup, workGroup)
                .localAddress(port)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //TODO 加解密
                        ch.pipeline().addLast(sessionHandler);
                        ch.pipeline().addLast(limitFlowHandler);
                        //ByteToMessageCodec 不能加@Sharable,因为内部存在共享变量
                        ch.pipeline().addLast(new PacketCodec());
                        ch.pipeline().addLast(dispatchHandler);
                    }
                });
        try {
            future = serverBootstrap.bind().sync();
            LOGGER.debug("game server started ----- port:{}", port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new ServerOpenException(e);
        }
    }

    public void setDispatcherExecutor(DispatcherExecutor dispatcherExecutor) {
        this.dispatcherExecutor = dispatcherExecutor;
    }

    public void setDispatchHandler(DispatchHandler dispatchHandler) {
        this.dispatchHandler = dispatchHandler;
    }

    public void setLimitFlowHandler(LimitFlowHandler limitFlowHandler) {
        this.limitFlowHandler = limitFlowHandler;
    }

    public void setSessionHandler(SessionHandler sessionHandler) {
        this.sessionHandler = sessionHandler;
    }

    public void shutdown() {
        future.channel().close();
    }
}
