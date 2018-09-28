package com.qworldr.server;

import com.qworldr.exception.ServerOpenException;
import com.qworldr.handler.DispatchHandler;
import com.qworldr.handler.LimitFlowHandler;
import com.qworldr.handler.PacketCodec;
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
    private ChannelFuture future;
    @Autowired
    private DispatchHandler dispatchHandler;
    @Autowired
    private LimitFlowHandler limitFlowHandler;
    public void start(int port) throws ServerOpenException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .channel(NioServerSocketChannel.class)
                .group(bossGroup, workGroup)
                .localAddress(port)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
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


    public void shutdown() {
        future.channel().close();
    }
}
