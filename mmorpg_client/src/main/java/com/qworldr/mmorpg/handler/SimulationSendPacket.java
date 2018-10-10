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
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            PropertyDescriptor[] propertyDescriptors;
            while (true) {
                try {
                    System.out.println("输入协议id:");
                    String s = bufferedReader.readLine();
                    short id = Short.parseShort(s);
                    System.out.println("协议字段-----------------------------------");
                    List<String> strs = new ArrayList<>();
                    Codec codec = CodecManager.getInstance().getCodec(id);
                    Class clazz = getProtocalClass(codec);
                    Field[] declaredFields = clazz.getDeclaredFields();
                    for (Field field : declaredFields) {
                        System.out.println(field.getName() + ":");
                        s = bufferedReader.readLine();
                        strs.add(s);
                    }
                    if (strs.size() > 0) {
                        Object resp = createResp(id, codec, strs);
                        ctx.channel().writeAndFlush(resp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {

        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
    }


    private Object createResp(short id, Codec codec, List<String> strs) {
        Class clazz = getProtocalClass(codec);
        Object o=null;
        try {
            o = clazz.newInstance();
            Field[] declaredFields = clazz.getDeclaredFields();
            int i = 0;
            for (Field field : declaredFields) {
                field.setAccessible(true);
                if (field.getType().equals(String.class)) {
                    field.set(o, strs.get(i++));
                } else {
                    field.set(o, defaultConvert.covert(strs.get(i++), field.getType()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    private Class getProtocalClass(Codec codec) {
        Type type = codec.getClass().getGenericInterfaces()[0];
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
        Type param = params[0];
        return (Class) param;
    }
}
