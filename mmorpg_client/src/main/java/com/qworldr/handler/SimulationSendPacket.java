package com.qworldr.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@ChannelHandler.Sharable
public class SimulationSendPacket extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("输入协议id:");
            String s = bufferedReader.readLine();
            short i = Short.parseShort(s);
            System.out.println("协议字段(send结束):");
            List<String> strs = new ArrayList<>();
            while (true) {
                s = bufferedReader.readLine();
                if("send".equals(s)){
                    break;
                }else {
                    strs.add(s);
                }
            }
            if(strs.size()>0){
                ByteBuf packet=encode(i,strs);
                ctx.channel().write(packet);
            }

        }catch (IOException e){

        }
    }
    /**
     * 简单测试，数据只支持字符串
     *  \--4字节--\-2字节-\-------data-------\
     *   协议长度  协议id     协议数据
     */
    private ByteBuf encode(short id, List<String> strs) {
        ByteBuf byteBuf = Unpooled.buffer();

        int len=6;
        for(String str :strs){
            len+=str.length();
        }
        byteBuf.writeInt(len);
        byteBuf.writeShort(id);
        for(String str :strs){
            byteBuf.writeBytes(str.getBytes());
        }
        return byteBuf;

    }
}
