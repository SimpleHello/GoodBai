package com.good.netty.tcp.entity.packet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.good.netty.tcp.entity.body.Body;
import com.good.netty.tcp.entity.body.PacketType;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.CorruptedFrameException;

/**
 * Created by wuyadong on 2016/12/6.
 * C接口消息体类，包含消息头和消息体，封装了消息的解包、封包操作；此类外部不应直接使用，外部接口应使用body内定义的消息体
 */
public class InterfaceMessage {
    public int sendCount = 0;
    private Header header;
    private Body body;
    private CRC crc;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceMessage.class);

    /**
     * 构造函数，用于收到消息后解析
     * @param frame 消息帧
     * @throws Exception CorruptedFrameException 帧错误
     */
    public InterfaceMessage(ByteBuf frame) throws Exception {
        this.header = new Header(frame.readBytes(Header.BYTE_LEN+10));
        // check magic
        if(!Header.MAGIC.equals(this.header.getMagic())) {
            frame.resetReaderIndex();
            throw new CorruptedFrameException("Invalid magic number, error header: : " + this.header);
        }
        /**
         * check length
         * frame.writerIndex() 包含了 该报文的所有字段长度 包括（帧头:10 以及CRC校验：2 需要相应的减去）
         */
        if(this.header.getLength() != (frame.writerIndex()-10-2)) {
            frame.resetReaderIndex();
            throw new CorruptedFrameException("Invalid packet length, error header: " + this.header);
        }

        //frame.setIndex(Header.BYTE_LEN, frame.writerIndex());
        Class<? extends Body> bodyClass = this.header.getType().getBodyClass();
        this.body = bodyClass.newInstance();
        this.body.parseFrame(frame.readBytes((int)this.header.getLength() - Header.BYTE_LEN));
        this.crc = new CRC(frame.readBytes(2));
        LOGGER.info("[CI] Frame recieved. " + this);
    }


    public boolean parseFrame(ByteBuf frame) {
    	  this.header = new Header(frame.readBytes(Header.BYTE_LEN+10));

          // check magic
          if(this.header.getMagic() != Header.MAGIC) {
              frame.resetReaderIndex();
              throw new CorruptedFrameException("Invalid magic number, error header: : " + this.header);
          }

          // check length
          if(this.header.getLength() != frame.writerIndex()) {
              frame.resetReaderIndex();
              throw new CorruptedFrameException("Invalid packet length, error header: " + this.header);
          }

          //frame.setIndex(Header.BYTE_LEN, frame.writerIndex());
          Class<? extends Body> bodyClass = this.header.getType().getBodyClass();
          try {
			this.body = bodyClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          this.body.parseFrame(frame.readBytes((int)this.header.getLength() - Header.BYTE_LEN));
          this.crc = new CRC(frame.readBytes(2));
          LOGGER.info("[CI] Frame recieved. " + this);
        return true;
    }
    /**
     * 构造函数
     * @param header 消息头
     * @param body 消息体
     */
    public InterfaceMessage(Header header, Body body) {
        this.header = header;
        this.body = body;
        this.crc = new CRC();
    }

    public InterfaceMessage(Header header, Body body,int crc) {
        this.header = header;
        this.body = body;
        this.crc = new CRC(crc);
    }
    /**
     * 构建请求的响应消息
     * @param req 请求消息
     * @param ackBody 响应消息体
     * @return 响应消息
     */
    public InterfaceMessage makeAckMessage(InterfaceMessage req,Body ackBody) {
        PacketType ackType = req.header.getType().getAck();

        //检查类型是否匹配
        Class<? extends Body> bodyClass = ackType.getBodyClass();
        if(ackBody.getClass() != bodyClass) {
            throw new IllegalArgumentException("ack body class:" + ackBody.getClass() + " is not right:" + bodyClass);
        }

        Header ackHeader = new Header(ackBody.packLength(),req.header.getSerialsNo(),ackType);
        return new InterfaceMessage(ackHeader, ackBody);
    }

    public final Header getHeader() {
        return this.header;
    }

    public final void setHeader(Header header) {
        this.header = header;
    }

    public final Body getBody() {
        return this.body;
    }

    public final void setBody(Body body) {
        this.body = body;
    }

    public ByteBuf packFrame() {
        ByteBuf frame = Unpooled.buffer((int)(this.header.getLength()+12));
        this.header.packFrame(frame);
        this.body.packFrame(frame);
        this.crc.packFrameLE(frame);//CRC 用小端模式
        return frame;
    }

    public ByteBuf packFrameLE() {

        ByteBuf frame = Unpooled.buffer((int)this.header.getLength());
        this.header.packFrameLE(frame);
        this.body.packFrameLE(frame);
        this.crc.packFrameLE(frame);
        return frame;
    }

    @Override
    public String toString() {
        return "Message: " + header.toString() + body.toString();
    }
}
