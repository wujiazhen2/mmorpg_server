package com.qworldr.mmorpg.common.exception;

import com.qworldr.mmorpg.common.constants.StatusCode;
import com.qworldr.mmorpg.common.resp.Status;

/**
 * @Author wujiazhen
 */
public class MessageException extends RuntimeException {
    private short messageId;
    public MessageException(short messageId){
        this.messageId=messageId;
    }
    public MessageException(short messageId,Exception e){
        super(e);
        this.messageId=messageId;
    }

    public short getMessageId() {
        return messageId;
    }

    public Status getStatus(){
      return   Status.valueOf(StatusCode.ERROR,messageId);
    }
}
