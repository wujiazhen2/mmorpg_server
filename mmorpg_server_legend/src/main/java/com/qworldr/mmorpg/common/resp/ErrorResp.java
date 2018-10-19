package com.qworldr.mmorpg.common.resp;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.protocal.ProtocalId;

/**
 * @Author wujiazhen
 */
@Protocal(ProtocalId.ErrorResp)
@ProtobufClass
public class ErrorResp {
    private int messageId;
    public static ErrorResp valueOf(int messageId) {
        ErrorResp errorResp=new ErrorResp();
        errorResp.messageId = messageId;
        return errorResp;
    }
}
