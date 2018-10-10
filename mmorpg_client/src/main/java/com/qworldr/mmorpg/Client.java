package com.qworldr.mmorpg;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.qworldr.mmorpg.codec.CodecManager;
import com.qworldr.mmorpg.convert.DefaultConvert;
import com.qworldr.mmorpg.handler.PacketCodec;
import com.qworldr.mmorpg.handler.SimulationSendPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private DefaultConvert defaultConvert;
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-client.xml");
        ((ClassPathXmlApplicationContext) applicationContext).start();

        Client client = new Client();
        client.defaultConvert=applicationContext.getBean(DefaultConvert.class);
        client.start();
    }

    public void start() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress("127.0.0.1", 4010)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new PacketCodec());
                            socketChannel.pipeline().addLast(new SimulationSendPacket());
                        }
                    });
            ChannelFuture sync = bootstrap.connect().sync();
            final Channel channel = sync.channel();
            new Thread(){
                @Override
                public void run() {
                    simulationSendPacket(channel);
                }
            }.start();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void simulationSendPacket(Channel channel){
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
                        channel.writeAndFlush(resp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {

        }
    }
    private Class getProtocalClass(Codec codec) {
        Type type = codec.getClass().getGenericInterfaces()[0];
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
        Type param = params[0];
        return (Class) param;
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
}
