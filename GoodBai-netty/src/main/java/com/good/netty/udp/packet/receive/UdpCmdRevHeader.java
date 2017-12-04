package com.good.netty.udp.packet.receive;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.CorruptedFrameException;

import java.nio.charset.StandardCharsets;

/**
 * Created by John on 2017/11/7.
 */
public class UdpCmdRevHeader {



    public static final short BYTE_LEN = 10;


    private byte soi;       // SOI , 信息传输起始标志位 (1 字节)
    private byte ver;                           // VER , 通信协议版本号 (从 0X10 开始)
    private byte adr;                           // ADR , 设备地址描述 (1—254;0 和 255 保留)
    private byte cid1;                          // CID1 , 设备类型标识控制码
    private byte rtnCid2;                          // 表明命令的不同内容
    private short lth;                          // 命令帧中“数据信息部份”的字节长度
    private byte lenId;
    private byte comGroup;//COM GROUP 区分不同种类的门禁产
    private byte type;// 而 TYPE 是该子集内的命令号


    public int getLength(){
        return BYTE_LEN*2-2 + lenId;
    }

    public UdpCmdRevHeader(ByteBuf frame) throws CorruptedFrameException{
        this.soi = frame.readByte();
        this.ver = getByteHexString(frame);
        this.adr = getByteHexString(frame);
        this.cid1 = getByteHexString(frame);
        this.rtnCid2 = getByteHexString(frame);
        short lth01 = getShortByteHexString(frame);//高四位数据
        short lth02 = getShortByteHexString(frame);//低四位数据
        this.lth = (short)((lth01 << 12)|lth02);
//        this.lenId = getByteHexString(frame);
        if(this.lth !=0){
            this.comGroup = getByteHexString(frame);
            this.type = getByteHexString(frame);
        }
    }

    private byte getByteHexString(ByteBuf frame){
        return getByteHexString(frame.readCharSequence(2, StandardCharsets.UTF_8).toString().trim());
    }

    private short getShortByteHexString(ByteBuf frame){
        return getShortHexString(frame.readCharSequence(2, StandardCharsets.UTF_8).toString().trim());
    }

    private byte getByteHexString(String str){
        byte x = (byte)Integer.parseInt(str,16);
        return x;
    }
    private short getShortHexString(String str){
        short x = (short)Integer.parseInt(str,16);
        return x;
    }


    public  byte getSoi() {
        return soi;
    }

    public  void setSoi(byte soi) {
        this.soi = soi;
    }

    public byte getVer() {
        return ver;
    }

    public void setVer(byte ver) {
        this.ver = ver;
    }

    public byte getAdr() {
        return adr;
    }

    public void setAdr(byte adr) {
        this.adr = adr;
    }

    public byte getCid1() {
        return cid1;
    }

    public void setCid1(byte cid1) {
        this.cid1 = cid1;
    }

    public byte getRtnCid2() {
        return rtnCid2;
    }

    public void setRtnCid2(byte rtnCid2) {
        this.rtnCid2 = rtnCid2;
    }

    public short getLth() {
        return lth;
    }

    public void setLth(short lth) {
        this.lth = lth;
    }

    public byte getLenId() {
        return lenId;
    }

    public void setLenId(byte lenId) {
        this.lenId = lenId;
    }

    public byte getComGroup() {
        return comGroup;
    }

    public void setComGroup(byte comGroup) {
        this.comGroup = comGroup;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UdpCmdRevHeader{" +
                "soi=" + soi +
                ", ver=" + ver +
                ", adr=" + adr +
                ", cid1=" + cid1 +
                ", rtnCid2=" + rtnCid2 +
                ", lth=" + lth +
                ", lenId=" + lenId +
                ", comGroup=" + comGroup +
                ", type=" + type +
                '}';
    }
}
