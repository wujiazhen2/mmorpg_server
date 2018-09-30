package com.qworldr.mmorpg.session;

import io.netty.channel.Channel;


public interface Session {
    Channel getChannel();

    int getId();
}
