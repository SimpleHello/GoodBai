package com.good.netty.udp.packet.receive;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.CorruptedFrameException;

import java.nio.charset.StandardCharsets;


public class UdpDoorRevHeader {

    public static final short HEAD_LEN = 36;//头总长度

    private byte header;  // 协议包开始标记（1 byte）
    private String descAddr;        // 目标设备地址（8 bytes）
    private String srcAddr;       // 源设备地址（20 bytes）
    private byte subDevType; // 子设备类型（1 byte）：1 - 串口；2 - USB；3 - IP网络
    private byte subDevAddr;        // 透传模块（1 byte）：串口号（4 bits）+地址号（4 bits）
    private short pLen;             // 协议族数据包长度（2 bytes）:rtnFlag+commType+dataLen+data
    private byte rtnFlag ;     // 报文类型（1 byte）：0xEE - 下行数据；0x00 - 上行数据；0xED - 上行心跳
    private short commType ;        // 命令编号（2 bytes）：0x0001 - 数据传输；0x0002 - 心跳
    private short dataLen;//数据包长度

    public UdpDoorRevHeader() {

    }


    public UdpDoorRevHeader(ByteBuf frame) throws CorruptedFrameException {
        this.header = frame.readByte();
        this.srcAddr = frame.getCharSequence(1, 20, StandardCharsets.UTF_8).toString().trim();
        frame.readerIndex(21);
        this.descAddr = frame.getCharSequence(21, 8, StandardCharsets.UTF_8).toString().trim();
        frame.readerIndex(29);
        this.subDevType = frame.readByte();
        this.subDevAddr = frame.readByte();
        this.pLen = frame.readShortLE();
        this.rtnFlag = frame.readByte();
        this.commType = frame.readShortLE();
        this.dataLen = frame.readShortLE();
    }

    public byte getHeader() {
        return header;
    }

    public void setHeader(byte header) {
        this.header = header;
    }

    public String getDescAddr() {
        return descAddr;
    }

    public void setDescAddr(String descAddr) {
        this.descAddr = descAddr;
    }

    public String getSrcAddr() {
        return srcAddr;
    }

    public void setSrcAddr(String srcAddr) {
        this.srcAddr = srcAddr;
    }

    public byte getSubDevType() {
        return subDevType;
    }

    public void setSubDevType(byte subDevType) {
        this.subDevType = subDevType;
    }

    public byte getSubDevAddr() {
        return subDevAddr;
    }

    public void setSubDevAddr(byte subDevAddr) {
        this.subDevAddr = subDevAddr;
    }

    public short getpLen() {
        return pLen;
    }

    public void setpLen(short pLen) {
        this.pLen = pLen;
    }

    public byte getRtnFlag() {
        return rtnFlag;
    }

    public void setRtnFlag(byte rtnFlag) {
        this.rtnFlag = rtnFlag;
    }

    public short getCommType() {
        return commType;
    }

    public void setCommType(short commType) {
        this.commType = commType;
    }

    public short getDataLen() {
        return dataLen;
    }

    public void setDataLen(short dataLen) {
        this.dataLen = dataLen;
    }

    @Override
    public String toString() {
        return "UdpDoorRevHeader{" +
                "header=" + header +
                ", descAddr='" + descAddr + '\'' +
                ", srcAddr='" + srcAddr + '\'' +
                ", subDevType=" + subDevType +
                ", subDevAddr=" + subDevAddr +
                ", pLen=" + pLen +
                ", rtnFlag=" + rtnFlag +
                ", commType=" + commType +
                '}';
    }
}
