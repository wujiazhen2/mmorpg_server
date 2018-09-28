package com.qworldr.handler;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.google.common.collect.Maps;
import com.qworldr.annotation.Protocal;
import com.qworldr.codec.CodecManager;
import com.qworldr.exception.ProtocalNotExists;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;
import java.util.Map;

/**
 *  \--4字节--\-2字节-\-------data-------\
 *   协议长度  协议id     协议数据
 */
public class PacketCodec extends ByteToMessageCodec {
    private Map<Short, Codec> codecs= Maps.newHashMap();
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
        int len = in.getInt(0);
        if(in.readableBytes()<len){
            return;
        }
        len = in.readInt();
        short protocalId = in.readShort();
        byte[] data= new byte[len-6];
        in.readBytes(data);
        Codec codec = CodecManager.getInstance().getCodec(protocalId);
        if(codecs==null){
            throw new ProtocalNotExists(String.format("协议号 %d 不存在",protocalId));
        }
        System.out.println(2222222);
        Object decode = codec.decode(data);
        out.add(decode);
    }


}
