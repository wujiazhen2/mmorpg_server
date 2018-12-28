package com.qworldr.mmorpg.server;

import com.qworldr.mmorpg.exception.ServerOpenException;
import io.netty.channel.ChannelFuture;

public interface IServer {

    public ChannelFuture start(int port) throws ServerOpenException;

    public void shutdown();
}
