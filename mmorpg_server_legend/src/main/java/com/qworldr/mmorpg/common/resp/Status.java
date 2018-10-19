package com.qworldr.mmorpg.common.resp;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
public class Status {
    @Protobuf
    private int code;
    @Protobuf
    private int messageId;
    public static Status valueOf(int code,int messageId){
        Status o = new Status();
        o.setCode(code);
        o.setMessageId(messageId);
        return o;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
