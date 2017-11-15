package com.good.netty.udp.packet.send;

import com.good.netty.udp.packet.Tailer;
import com.good.netty.udp.packet.UdpCmdMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 下行方向（SC =>FSU）
 *
 */
public class UdpDoorSendMessage {

    private UdpDoorSendHeader header;
    private UdpCmdMessage body;
    private Tailer tailer;//报文 尾部 2个字段：校验位 和 结尾标识位

    private static final Logger LOGGER = LoggerFactory.getLogger(UdpDoorSendMessage.class);
    /**
     * 构造函数
     * @param header 消息头
     * @param body 消息体
     */
    public UdpDoorSendMessage(UdpDoorSendHeader header, UdpCmdMessage body) {
        this.header = header;
        this.body = body;
        this.tailer = new Tailer();
    }


    public final UdpDoorSendHeader getHeader() {
        return this.header;
    }

    public final void setHeader(UdpDoorSendHeader header) {
        this.header = header;
    }

    public final UdpCmdMessage getBody() {
        return this.body;
    }

    public final void setBody(UdpCmdMessage body) {
        this.body = body;
    }

    public ByteBuf packFrame() {
        ByteBuf frame = Unpooled.buffer(this.header.getpLen()+2);
        this.header.packFrame(frame);
        this.body.packFrame(frame);
        this.tailer.packFrame(frame);
        return frame;
    }

    @Override
    public String toString() {
        return "Message: " + header.toString() + body.toString();
    }
}
