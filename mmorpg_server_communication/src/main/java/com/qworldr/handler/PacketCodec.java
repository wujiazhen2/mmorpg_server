package com.qworldr.handler;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.google.common.collect.Maps;
import com.qworldr.annotation.Protocal;
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
public class PacketCodec extends ByteToMessageCodec implements ApplicationListener<ContextRefreshedEvent> {
    private Map<Short, Codec> codecs= Maps.newHashMap();
    public PacketCodec(){

    }
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
        int len = in.readInt();
        if(in.readableBytes()<len){
            in.resetReaderIndex();
            return;
        }
        short protocalId = in.readShort();
        byte[] data= new byte[len-6];
        in.readBytes(data);
        Codec codec = codecs.get(protocalId);
        if(codecs==null){
            throw new ProtocalNotExists(String.format("协议号 %d 不存在",protocalId));
        }
        Object decode = codec.decode(data);
        out.add(decode);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Protocal.class);
        beansWithAnnotation.forEach((key,value)->{
            Class<?> aClass = value.getClass();
            Protocal annotation = aClass.getAnnotation(Protocal.class);
            Codec<?> codec = ProtobufProxy.create(aClass);
            codecs.put(annotation.value(),codec);
        });
    }
}
