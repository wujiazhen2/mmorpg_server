package com.qworldr.bean;

public class Request {

    private  short protocalId;
    private  byte[] data;
    public Request(short protocalId, byte[] data) {
        this.protocalId = protocalId;
        this.data = data;
    }

    public short getProtocalId() {
        return protocalId;
    }

    public byte[] getData() {
        return data;
    }
}
