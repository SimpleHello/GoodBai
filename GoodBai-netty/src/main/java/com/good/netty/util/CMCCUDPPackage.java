/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.good.netty.util;

/**
 * 
 * @author Mosaico
 */
public class CMCCUDPPackage {
    
    public static final byte HEADER = (byte) 0xFF;  // 协议包开始标记（1 byte）
    private String descAddr;        // 目标设备地址（20 bytes）
    private String srcAddr = "00";  // 源设备地址（8 bytes）
    private byte subDevType = 0x01; // 子设备类型（1 byte）：1 - 串口；2 - USB；3 - IP网络
    private byte subDevAddr=0x03;        // 透传模块（1 byte）：串口号（4 bits）+地址号（4 bits）
    private short pLen;             // 协议族数据包长度（2 bytes）:rtnFlag+commType+dataLen+data
    private byte rtnFlag = (byte) 0xEE;     // 报文类型（1 byte）：0xEE - 下行数据；0x00 - 上行数据；0xED - 上行心跳
    private short commType = 0x0001;        // 命令编号（2 bytes）：0x0001 - 数据传输；0x0002 - 心跳
    private short dataLen;          // 透传数据长度（2 bytes）：上行心跳该属性长度为0
    private String data;            // 透传数据（N bytes）：上行心跳该属性长度为0
    private byte verify;            // 校验（1 byte）：转义前对数据包进行异或校验，不含包头包尾
    public static final byte TAILER = (byte) 0xFE;  // 协议包结束标记（1 byte）

    public CMCCUDPPackage() {
    }

    public CMCCUDPPackage(String descAddr, String srcAddr, byte subDevAddr, String data) {
        this.descAddr = descAddr;
        this.srcAddr = srcAddr;
        this.subDevAddr = subDevAddr;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public byte getVerify() {
        return verify;
    }

    public void setVerify(byte verify) {
        this.verify = verify;
    }

    @Override
    public String toString() {
        return "CMCCUDPPackage{" + "descAddr=" + descAddr + ", srcAddr=" + srcAddr + ", subDevType=" + subDevType + ", subDevAddr=" + subDevAddr + ", pLen=" + pLen + ", rtnFlag=" + rtnFlag + ", commType=" + commType + ", dataLen=" + dataLen + ", data=" + data + ", verify=" + verify + '}';
    }
    
}
