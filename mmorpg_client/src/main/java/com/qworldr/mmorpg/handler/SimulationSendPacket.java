package com.qworldr.mmorpg.handler;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.qworldr.mmorpg.codec.CodecManager;
import com.qworldr.mmorpg.convert.DefaultConvert;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
@ChannelHandler.Sharable
public class SimulationSendPacket extends ChannelInboundHandlerAdapter {

    @Autowired
    private DefaultConvert defaultConvert;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
    }





}
