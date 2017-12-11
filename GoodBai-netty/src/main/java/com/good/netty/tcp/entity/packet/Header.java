package com.good.netty.tcp.entity.packet;

import java.nio.charset.StandardCharsets;

import com.good.netty.tcp.entity.body.PacketType;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.CorruptedFrameException;

/**
 *  Created by mag on 2017/06/22.
 *  C1 接口消息内消息体消息头定义
 *  帧头	ASCII码 "#dlhjtxxy#"
	长度(Length)	总报文长度(不包含帧头,CRC字段)  --4位
	报文序号(SerialsNo)	自增, 请求与响应相同     --4 位
	命令字(PK_Type)	报文的类型定义
	内容	数据
 */
public class Header {
    // check head magic
    public static final String MAGIC = "#dlhjtxxy#";
    public static final int BYTE_LEN = 12;

    private String magic;
    private long length;
    private long serialsNo;
    private PacketType packetType;
    
    public Header(ByteBuf frame) throws CorruptedFrameException{
        //check length
        if (frame.readableBytes() < BYTE_LEN) {
            throw new CorruptedFrameException(String.format("Invalid header frame, the length(%d) is small than BYTE_LEN:%d ", frame.readableBytes(), BYTE_LEN));
        } 
        this.magic = frame.getCharSequence(0, 10, StandardCharsets.UTF_8).toString().trim();
        this.length = frame.getUnsignedInt(10);
        this.serialsNo = frame.getUnsignedInt(14);
        this.packetType = PacketType.toType(frame.getUnsignedInt(18));
    }

    public Header(long body_length, long serialsNo, PacketType type) {
        this.magic = MAGIC;
        this.length = body_length + BYTE_LEN;
        this.serialsNo = serialsNo;
        this.packetType = type;
    }

    public void packFrame(ByteBuf frame) {
    	frame.writeCharSequence(this.magic, StandardCharsets.UTF_8);
        frame.writeInt((int) this.length);
        frame.writeInt((int) this.serialsNo);
        frame.writeInt((int) this.packetType.getType());
    }

    public void packFrameLE(ByteBuf frame) {
    	frame.writeCharSequence(this.magic, StandardCharsets.UTF_8);
        frame.writeIntLE((int) this.length);
        frame.writeIntLE((int) this.serialsNo);
        frame.writeIntLE((int) this.packetType.getType());
    }

    public String getMagic() {
        return this.magic;
    }

    public long getSerialsNo() {
        return this.serialsNo;
    }

    public void setSerialsNo(long serialsNo) {
        this.serialsNo = serialsNo;
    }

    public long getLength() {
        return this.length;
    }

    public PacketType getType() {
        return this.packetType;
    }

    public void setType(PacketType type) {
        this.packetType = type;
    }

    public String toString() {
        return "magic:" + this.magic + " length: " + this.length +
                " serialsNo: " + this.serialsNo + " packetType: " + this.packetType.getType() + " ";
    }

    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }

        if (o.getClass() == Header.class)
        {
            Header n = (Header)o;
            return (n.magic == magic)  && (n.length == length) &&
                    (n.serialsNo == serialsNo) && (n.packetType == packetType)  ;
        }
        return false;
    }

}
