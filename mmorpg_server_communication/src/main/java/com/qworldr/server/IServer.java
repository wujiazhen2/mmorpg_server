package com.qworldr.server;

import com.qworldr.exception.ServerOpenException;

public interface IServer {

    public void start(int port) throws ServerOpenException;

    public void shutdown();
}
