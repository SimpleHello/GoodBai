package com.good.netty.udp.packet;

import com.good.netty.util.BitUtil;
import com.good.netty.util.ByteUtil;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

/**
 * Created by John on 2017/11/7.
 */
public class UdpCmdHeader {



    public static final short BYTE_LEN = 10;


    public static byte soi = (byte) 0x7E;       // SOI , 信息传输起始标志位 (1 字节)
    private byte ver;                           // VER , 通信协议版本号 (从 0X10 开始)
    private byte adr;                           // ADR , 设备地址描述 (1—254;0 和 255 保留)
    private byte cid1;                          // CID1 , 设备类型标识控制码
    private byte rtnCid2;                          // 表明命令的不同内容
    private short lth;                          // 命令帧中“数据信息部份”的字节长度
    private  byte lenId;
    private byte comGroup;//COM GROUP 区分不同种类的门禁产
    private byte type;// 而 TYPE 是该子集内的命令号

    public UdpCmdHeader() {
    }

    public UdpCmdHeader(byte ver, byte adr, byte cid1, byte rtnCid2, byte lenId, byte comGroup, byte type) {
        this.ver = ver;
        this.adr = adr;
        this.cid1 = cid1;
        this.rtnCid2 = rtnCid2;
        this.lenId = lenId;
        this.lth = getLthByLenId(lenId);
        this.comGroup = comGroup;
        this.type = type;
    }

    public int getLength(){
        return BYTE_LEN*2-2 + lenId;
    }

    public void packFrame(ByteBuf frame) {
        frame.writeByte(soi);
        frame.writeCharSequence(getHexString(ByteUtil.getHeight4(this.ver)), StandardCharsets.UTF_8);//高四位
        frame.writeCharSequence(getHexString(ByteUtil.getLow4(this.ver)), StandardCharsets.UTF_8);//低四位
        frame.writeCharSequence(getHexString(ByteUtil.getHeight4(this.adr)), StandardCharsets.UTF_8);//高四位
        frame.writeCharSequence(getHexString(ByteUtil.getLow4(this.adr)), StandardCharsets.UTF_8);//低四位
        frame.writeCharSequence(getHexString(ByteUtil.getHeight4(this.cid1)), StandardCharsets.UTF_8);//高四位
        frame.writeCharSequence(getHexString(ByteUtil.getLow4(this.cid1)), StandardCharsets.UTF_8);//低四位
        frame.writeCharSequence(getHexString(ByteUtil.getHeight4(this.rtnCid2)), StandardCharsets.UTF_8);//高四位
        frame.writeCharSequence(getHexString(ByteUtil.getLow4(this.rtnCid2)), StandardCharsets.UTF_8);//低四位
        frame.writeCharSequence(getHexString(getLthByLenByte(this.lenId)[0]), StandardCharsets.UTF_8);//前四位
        frame.writeCharSequence(getHexString(getLthByLenByte(this.lenId)[1]), StandardCharsets.UTF_8);//低四位
        frame.writeCharSequence(getHexString(getLthByLenByte(this.lenId)[2]), StandardCharsets.UTF_8);//低四位
        frame.writeCharSequence(getHexString(getLthByLenByte(this.lenId)[3]), StandardCharsets.UTF_8);//低四位
        frame.writeCharSequence(getHexString(ByteUtil.getHeight4(this.comGroup)), StandardCharsets.UTF_8);//高四位
        frame.writeCharSequence(getHexString(ByteUtil.getLow4(this.comGroup)), StandardCharsets.UTF_8);//低四位
        frame.writeCharSequence(getHexString(ByteUtil.getHeight4(this.type)), StandardCharsets.UTF_8);//高四位
        frame.writeCharSequence(getHexString(ByteUtil.getLow4(this.type)), StandardCharsets.UTF_8);//低四位
    }


    private String getHexString(int i){
        return Integer.toHexString(i).toUpperCase();//需要大写
    }

    private short getLthByLenId(byte lenId){
        String lenId12 = BitUtil.getStringBit12(lenId);//将lenId转成12位二进制数
        String first = lenId12.substring(0,4);//4位相间隔
        String second = lenId12.substring(4,8);//4位相间隔
        String third = lenId12.substring(8,12);//4位相间隔
        int sumBit = BitUtil.BinstrToInt(first)+BitUtil.BinstrToInt(second)+BitUtil.BinstrToInt(third);//按照4位累加
        int sumRepair = 16-(sumBit%16);//对16取补
        String lenString4 = BitUtil.getStringBit4(sumRepair);//获得高四位数据
        String lenString = lenString4 + lenId12;//获得完整 字符串
        short value = (short)BitUtil.BinstrToInt(lenString);//二进制字符串转int型
        return value;
    }

    private byte[] getLthByLenByte(byte lenId){
        String lenId12 = BitUtil.getStringBit12(lenId);//将lenId转成12位二进制数
        String first = lenId12.substring(0,4);//4位相间隔
        String second = lenId12.substring(4,8);//4位相间隔
        String third = lenId12.substring(8,12);//4位相间隔
        int sumBit = BitUtil.BinstrToInt(first)+BitUtil.BinstrToInt(second)+BitUtil.BinstrToInt(third);//按照4位累加
        int sumRepair = 16-(sumBit%16);//对16取补
        String lenString4 = BitUtil.getStringBit4(sumRepair);//获得高四位数据
        byte[] lenc = new byte[4];
        lenc[0] = (byte)BitUtil.BinstrToInt(lenString4);
        lenc[1] = (byte)BitUtil.BinstrToInt(first);
        lenc[2] = (byte)BitUtil.BinstrToInt(second);
        lenc[3] = (byte)BitUtil.BinstrToInt(third);
        return lenc;
    }

    public static byte getSoi() {
        return soi;
    }

    public static void setSoi(byte soi) {
        UdpCmdHeader.soi = soi;
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
}
