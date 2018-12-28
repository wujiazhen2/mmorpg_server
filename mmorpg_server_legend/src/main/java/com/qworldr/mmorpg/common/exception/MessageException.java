package com.qworldr.mmorpg.common.exception;

import com.qworldr.mmorpg.common.constants.StatusCode;
import com.qworldr.mmorpg.common.resp.Status;

/**
 * @author wujiazhen
 */
public class MessageException extends RuntimeException {
    private int messageId;
    public MessageException(int messageId){
        this.messageId=messageId;
    }
    public MessageException(int messageId,Exception e){
        super(e);
        this.messageId=messageId;
    }

    public int getMessageId() {
        return messageId;
    }

    public Status getStatus(){
      return   Status.valueOf(StatusCode.ERROR,messageId);
    }
}
