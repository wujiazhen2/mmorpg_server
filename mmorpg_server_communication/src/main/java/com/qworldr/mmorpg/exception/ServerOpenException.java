package com.qworldr.mmorpg.exception;

public class ServerOpenException extends  Exception {

    public ServerOpenException(Exception e){
        super(e);
    }

    public ServerOpenException(String msg){
        super(msg);
    }
}
