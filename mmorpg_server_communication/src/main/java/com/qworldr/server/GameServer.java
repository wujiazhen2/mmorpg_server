package com.qworldr.server;

import com.qworldr.exception.ServerOpenException;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

import java.net.InetSocketAddress;
import java.util.List;

public class GameServer implements IServer {

    private List<ChannelInboundHandler> inboundHandlerList;
    private List<ChannelOutboundHandler> outboundHandlerList;
    private ChannelFuture future;
    public void start(int port) throws ServerOpenException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(bossGroup, workGroup)
                .localAddress(port)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //todo
                        ch.pipeline().addLast();
                    }
                });
        try {
            future = serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();
        }catch (InterruptedException e){
            throw new ServerOpenException(e);
        }
    }

    public void shutdown(){
        future.channel().close();
    }
}
