package com.qworldr.server;

import com.qworldr.exception.ServerOpenException;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class GameServer implements IServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameServer.class);
    private List<ChannelInboundHandler> inboundHandlerList;
    private List<ChannelOutboundHandler> outboundHandlerList;
    private ChannelFuture future;

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
                        //
                        outboundHandlerList.forEach(out-> ch.pipeline().addLast(out));
                        inboundHandlerList.forEach(in-> ch.pipeline().addLast(in));
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


    public void setInboundHandlerList(List<ChannelInboundHandler> inboundHandlerList) {
        this.inboundHandlerList = inboundHandlerList;
    }


    public void setOutboundHandlerList(List<ChannelOutboundHandler> outboundHandlerList) {
        this.outboundHandlerList = outboundHandlerList;
    }

    public void shutdown() {
        future.channel().close();
    }
}
