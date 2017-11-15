package com.good.netty.udp.packet.receive;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * 心跳报文报
 *
 */
public class UdpDoorRevHeartMessage {

    private  byte header = (byte) 0xFF;  // 协议包开始标记（1 byte）
    private String descAddr;        // 目标设备地址（8 bytes）
    private String srcAddr;       // 源设备地址（20 bytes）
    private byte subDevType; // 子设备类型（1 byte）：1 - 串口；2 - USB；3 - IP网络
    private byte subDevAddr;        // 透传模块（1 byte）：串口号（4 bits）+地址号（4 bits）
    private short pLen;             // 协议族数据包长度（2 bytes）:rtnFlag+commType+dataLen+data
    private byte rtnFlag ;     // 报文类型（1 byte）：0xEE - 下行数据；0x00 - 上行数据；0xED - 上行心跳
    private short commType ;        // 命令编号（2 bytes）：0x0001 - 数据传输；0x0002 - 心跳
    private byte verify;            // 校验（1 byte）：转义前对数据包进行异或校验，不含包头包尾
    public  byte tailer;  // 协议包结束标记（1 byte）

    private static final Logger LOGGER = LoggerFactory.getLogger(UdpDoorRevHeartMessage.class);

    /**
     * 构造函数，用于收到消息后解析
     * @param frame 消息帧
     * @throws Exception CorruptedFrameException 帧错误
     */
    public UdpDoorRevHeartMessage(ByteBuf frame) throws Exception {
        this.header = frame.readByte();
        this.descAddr = frame.getCharSequence(0, 8, StandardCharsets.UTF_8).toString().trim();
        frame.readerIndex(9);
        this.srcAddr = frame.getCharSequence(0, 20, StandardCharsets.UTF_8).toString().trim();
        frame.readerIndex(29);
        this.subDevType = frame.readByte();
        this.subDevAddr = frame.readByte();
        this.pLen = frame.readShort();
        this.rtnFlag = frame.readByte();
        this.commType = frame.readShort();
        this.verify =  frame.readByte();
        this.tailer =  frame.readByte();
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

    public byte getVerify() {
        return verify;
    }

    public void setVerify(byte verify) {
        this.verify = verify;
    }

    public byte getTailer() {
        return tailer;
    }

    public void setTailer(byte tailer) {
        this.tailer = tailer;
    }
}
