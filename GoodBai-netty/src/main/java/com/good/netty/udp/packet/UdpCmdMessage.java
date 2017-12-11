package com.good.netty.udp.packet;

import com.good.netty.tcp.entity.body.Body;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送数据的 信号实体
 * Created by John on 2017/11/8.
 */
public class UdpCmdMessage {

    private UdpCmdHeader header;
    private Body body;
    private UdpCmdTail tailer;//报文 尾部 2个字段：校验位 和 结尾标识位

    private static final Logger LOGGER = LoggerFactory.getLogger(UdpCmdMessage.class);
    /**
     * 构造函数
     * @param header 消息头
     * @param body 消息体
     */
    public UdpCmdMessage(UdpCmdHeader header, Body body) {
        this.header = header;
        this.body = body;
        this.tailer = new UdpCmdTail();
    }

    public int getLength(){
        return  this.header.getLength();
    }
    public final UdpCmdHeader getHeader() {
        return this.header;
    }

    public final void setHeader(UdpCmdHeader header) {
        this.header = header;
    }

    public final Body getBody() {
        return this.body;
    }

    public final void setBody(Body body) {
        this.body = body;
    }

    public ByteBuf packFrame(ByteBuf frame) {
        this.header.packFrame(frame);
        if(this.body!=null){
            this.body.packFrame(frame);
        }
        this.tailer.packFrame(frame);
        return frame;
    }

    @Override
    public String toString() {
        return "Message: " + header.toString() + body.toString();
    }
}
