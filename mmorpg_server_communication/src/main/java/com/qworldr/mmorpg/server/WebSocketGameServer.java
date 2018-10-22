package com.qworldr.mmorpg.server;

import com.qworldr.mmorpg.handler.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author wujiazhen
 */
public class WebSocketGameServer  extends GameServer{

    public ChannelInitializer buildChannelInitializer() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
                ch.pipeline().addLast(new HttpServerCodec());
                //以块的方式来写的处理器
                ch.pipeline().addLast(new ChunkedWriteHandler());
                //netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
                ch.pipeline().addLast(new HttpObjectAggregator(65535));
                ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
                ch.pipeline().addLast(getSessionHandler());
                ch.pipeline().addLast(getLimitFlowHandler());
                //ByteToMessageCodec 不能加@Sharable,因为内部存在共享变量
                ch.pipeline().addLast(new BinaryWebSocketFrameCodec());
                ch.pipeline().addLast(getDispatchHandler());

            }
        };
    }

}
