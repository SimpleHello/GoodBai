package com.good.netty.udp.packet.send;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;


public class UdpDoorSendHeader {


    public static final short BYTE_LEN = 5;//rtnFlag(1)+commType(2)+dataLen(2)+data

    public static byte PHeader = (byte) 0xFF;  // 协议包开始标记（1 byte）
    private String descAddr;        // 目标设备地址（20 bytes）
    private String srcAddr = "00";  // 源设备地址（8 bytes）
    private byte subDevType = 0x01; // 子设备类型（1 byte）：1 - 串口；2 - USB；3 - IP网络
    private byte subDevAddr=0x03;        // 透传模块（1 byte）：串口号（4 bits）+地址号（4 bits）
    private short pLen;             // 协议族数据包长度（2 bytes）:rtnFlag+commType+dataLen+data
    public static byte rtnFlag = (byte) 0xEE;     // 报文类型（1 byte）：0xEE - 下行数据；0x00 - 上行数据；0xED - 上行心跳
    private static short commType = 1;        // 命令编号（2 bytes）：0x0001 - 数据传输；0x0002 - 心跳
    private short dataLen;//数据包长度

    public UdpDoorSendHeader() {
    }

    public UdpDoorSendHeader(String descAddr, String srcAddr, byte subDevAddr,short dataLength) {
        this.descAddr = descAddr;
        this.srcAddr = srcAddr;
        this.subDevAddr = subDevAddr;
        this.pLen = (short)(dataLength+BYTE_LEN);
        this.dataLen = dataLength;
    }

    public void packFrame(ByteBuf frame) {
        frame.writeByte(PHeader);
        getNomStr(frame,this.descAddr,20);
        getNomStr(frame,this.srcAddr,8);
        frame.writeByte(this.subDevType);
        frame.writeByte(this.subDevAddr);
        frame.writeShortLE(this.pLen);
        frame.writeByte(this.rtnFlag);
        frame.writeShortLE(this.commType);
        frame.writeShortLE(this.dataLen);
    }

    private String getNomStr(ByteBuf frame,String str,int len ){
        frame.writeCharSequence(str, StandardCharsets.UTF_8);
        if(str==null){
            return null;
        }
        if(str.length()>len){
            return  str.substring(0,len);
        }else if(str.length()<len){
            for(int i=0;i<(len-str.length());i++){
                frame.writeByte(0);
            }
            return str;
        }
        return str;
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

    @Override
    public String toString() {
        return "UdpDoorSendHeader{" +
                "descAddr='" + descAddr + '\'' +
                ", srcAddr='" + srcAddr + '\'' +
                ", subDevType=" + subDevType +
                ", subDevAddr=" + subDevAddr +
                ", pLen=" + pLen +
                '}';
    }
}
