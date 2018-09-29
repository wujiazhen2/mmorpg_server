package com.qworldr.session;


import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class TcpSession implements Session {
    private Channel channel;
    private Object id;
    public Object getId() {
        return id;
    }
    public void setId(Object id){
        this.id=id;
    }
    public Map<String,String> params= new HashMap<>();
    public String getParam(String id){
        return params.get(id);
    }
    public void  setParam(String id,String value){
        params.put(id,value);
    }

    public static Session valueOf(Channel channel){
        TcpSession tcpSession = new TcpSession();
        tcpSession.channel=channel;
        return  tcpSession;
    }
    @Override
    public Channel getChannel() {
        return channel;
    }


}
