package com.good.netty.udp.packet.receive;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.CorruptedFrameException;

import java.nio.charset.StandardCharsets;

/**
 * Created by John on 2017/11/8.
 */
public class UdpCmdRevTail {
    /**
     *    CHECKSUM 的计算是：整个数据桢中除 SOI , EOI 和 CHECKSUM 本身之外的其他字符，
     *    按 ASCII 码累加求和，结果模 65536 余数取 反加 1；
     */
    private short sum;//SUM (CHECKSUM), 数据桢的校验码
    private  byte eoi;//EOI 结束码（固定为 0DH，发送是按 HEX 0DH 发送）；


    public UdpCmdRevTail(ByteBuf frame) throws CorruptedFrameException{
        short lth01 = getShortByteHexString(frame);//高四位数据
        short lth02 = getShortByteHexString(frame);//低四位数据
        this.sum = (short)((lth01 & 0xF0)|(lth02 & 0x0F));
        this.eoi = frame.readByte();
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

    @Override
    public String toString() {
        return "UdpCmdRevTail{" +
                "sum=" + sum +
                ", eoi=" + eoi +
                '}';
    }
}
