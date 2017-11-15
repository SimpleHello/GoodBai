package com.good.netty.udp.packet.receive;

import com.good.netty.udp.packet.Tailer;
import com.good.netty.udp.packet.UdpCmdMessage;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.CorruptedFrameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 下行方向（SC =>FSU）
 *
 */
public class UdpDoorRevMessage {

    private UdpDoorRevHeader header;
    private UdpCmdMessage body;
    private Tailer tailer;//报文 尾部 2个字段：校验位 和 结尾标识位

    private static final Logger LOGGER = LoggerFactory.getLogger(UdpDoorRevMessage.class);

    /**
     * 构造函数，用于收到消息后解析
     * @param frame 消息帧
     * @throws Exception CorruptedFrameException 帧错误
     */
    public UdpDoorRevMessage(ByteBuf frame) throws Exception {
        this.header = new UdpDoorRevHeader(frame.readBytes(UdpDoorRevHeader.HEAD_LEN));
        // check magic
        if(this.header.getHeader()!=0xff) {//前2位不等于0xff
            frame.resetReaderIndex();
            throw new CorruptedFrameException("UDP透传协议包的开始标识错误!(0xff) : " + this.header.toString());
        }
        //frame.setIndex(Header.BYTE_LEN, frame.writerIndex());
//      Class<? extends Body> bodyClass = this.header.getType().getBodyClass();
//      this.body = bodyClass.newInstance();
//      this.body.parseFrame(frame.readBytes((int)this.header.getLength() - Header.BYTE_LEN));

        LOGGER.info("[CI] Frame recieved. " + this);
    }

    public final UdpDoorRevHeader getHeader() {
        return this.header;
    }

    public final void setHeader(UdpDoorRevHeader header) {
        this.header = header;
    }

    public final UdpCmdMessage getBody() {
        return this.body;
    }

    public final void setBody(UdpCmdMessage body) {
        this.body = body;
    }


    @Override
    public String toString() {
        return "Message: " + header.toString() + body.toString();
    }
}
