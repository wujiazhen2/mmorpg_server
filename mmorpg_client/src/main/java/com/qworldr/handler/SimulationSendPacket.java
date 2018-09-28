package com.qworldr.handler;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.qworldr.codec.CodecManager;
import com.qworldr.convert.DefaultConvert;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@ChannelHandler.Sharable
public class SimulationSendPacket extends ChannelInboundHandlerAdapter {

    @Autowired
    private DefaultConvert defaultConvert;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            PropertyDescriptor[] propertyDescriptors;
            while(true) {
                System.out.println("输入协议id:");
                String s = bufferedReader.readLine();
                short id = Short.parseShort(s);
                System.out.println("协议字段-----------------------------------");
                List<String> strs = new ArrayList<>();
                Codec codec = CodecManager.getInstance().getCodec(id);
                Class clazz = getProtocalClass(codec);
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field field :declaredFields){
                    System.out.println(field.getName()+":");
                    s = bufferedReader.readLine();
                    strs.add(s);
                }
                if (strs.size() > 0) {
                    ByteBuf packet = encode(id,codec ,strs);
                    ctx.channel().writeAndFlush(packet);
                }
            }
        }catch (IOException e){

        }
    }


    /**
     * 简单测试，数据基本数据类型
     *  \--4字节--\-2字节-\-------data-------\
     *   协议长度  协议id     协议数据
     */
    private ByteBuf encode(short id,Codec codec, List<String> strs) {
        ByteBuf byteBuf = Unpooled.buffer();
        int len=6;
        Class clazz = getProtocalClass(codec);
        try {
            Object o = clazz.newInstance();
            Field[] declaredFields = clazz.getDeclaredFields();
            int i=0;
            for (Field field :declaredFields){
                field.setAccessible(true);
                if(field.getType().equals(String.class)){
                   field.set(o,strs.get(i++));
                }else {
                    field.set(o, defaultConvert.covert(strs.get(i++),field.getType()));
                }
            }
            byte[] data = codec.encode(o);
            len+=data.length;
            byteBuf.writeInt(len);
            byteBuf.writeShort(id);
            byteBuf.writeBytes(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteBuf;
    }

    private Class getProtocalClass(Codec codec) {
        Type type = codec.getClass().getGenericInterfaces()[0];
        Type[] params = ((ParameterizedType)type).getActualTypeArguments();
        Type param = params[0];
        return (Class) param;
    }
}
