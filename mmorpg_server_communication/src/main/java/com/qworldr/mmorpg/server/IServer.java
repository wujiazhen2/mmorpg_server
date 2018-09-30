package com.qworldr.mmorpg.server;

import com.qworldr.mmorpg.exception.ServerOpenException;

public interface IServer {

    public void start(int port) throws ServerOpenException;

    public void shutdown();
}
