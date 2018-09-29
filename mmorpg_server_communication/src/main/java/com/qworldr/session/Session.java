package com.qworldr.session;

import io.netty.channel.Channel;


public interface Session {
    Channel getChannel();

    int getId();
}
