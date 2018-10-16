package com.qworldr.mmorpg.common.identify;

import com.qworldr.mmorpg.identify.GeneratorStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author wujiazhen
 * 雪花算法生成主键
 */
@Component
public class SnowflakeIdentifyGeneratorStrategy implements GeneratorStrategy<Long> {
    //开始生成的时间戳，用当前时间戳-twepoch作为雪花算法的时间戳
    private final long twepoch = 1539658610050L;
    private final long serverIdBits = 10L;
    private final long maxServerId = 2 << serverIdBits -1;
    private final long sequenceBits = 12L;
    private final long serverIdShift = sequenceBits;
    private final long timestampLeftShift = sequenceBits + serverIdBits;
    private final long maxSequence = 2<< sequenceBits-1;
    @Value("${serverId}")
    private long serverId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;
    @PostConstruct
    public void init(){
        if((serverId=serverId-10000) > maxServerId){
            throw new RuntimeException(String.format("serverId不能超过最大值%s",maxServerId));
        }
    }
    @Override
    public synchronized Long generatorKey() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("时间被调整,上次时间戳 %d>%d", lastTimestamp,timestamp));
        }
        if (lastTimestamp == timestamp) {
             sequence=(sequence+1)%maxSequence;
            if (sequence==0) {
                //如果这毫秒的序列号用完，自旋等待下以毫秒再生成
                while ((timestamp=System.currentTimeMillis())<=lastTimestamp){}
            }
        } else {
            sequence=0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift)  | (serverId << serverIdShift) | sequence;
    }
    @Override
    public String getType() {
        return "snowflake";
    }



}
