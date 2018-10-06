package com.qworldr.mmorpg.common.resp;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;

@ProtobufClass
public class Status {
    private short code;
    private int messageId;
    public static Status valueOf(short code,int messageId){
        Status o = new Status();
        o.setCode(code);
        o.setMessageId(messageId);
        return o;
    }
    public short getCode() {
        return code;
    }
    public void setCode(short code) {
        this.code = code;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
