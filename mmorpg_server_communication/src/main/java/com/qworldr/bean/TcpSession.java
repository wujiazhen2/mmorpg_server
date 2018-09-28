package com.qworldr.bean;

import java.nio.channels.Channel;

public class TcpSession implements Session {

    private Channel channel;
    private Object id;
    public Object getId() {
        return id;
    }
}
