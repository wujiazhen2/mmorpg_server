package com.qworldr.mmorpg.session;


import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TcpSession implements Session {
    private Channel channel;
    private int id;
    private static  final AtomicInteger SEQ = new AtomicInteger(1);
    public int getId() {
        return id;
    }

    public Map<String,String> params= new HashMap<>();
    public String get(String id){
        return params.get(id);
    }
    public void put(String id, String value){
        params.put(id,value);
    }

    public static Session valueOf(Channel channel){
        TcpSession tcpSession = new TcpSession();
        tcpSession.channel=channel;
        tcpSession.id=SEQ.getAndIncrement();
        return  tcpSession;
    }
    @Override
    public Channel getChannel() {
        return channel;
    }


}
