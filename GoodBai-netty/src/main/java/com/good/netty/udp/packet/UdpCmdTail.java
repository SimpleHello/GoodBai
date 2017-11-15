package com.good.netty.udp.packet;

import com.good.netty.util.BitUtil;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.CorruptedFrameException;

import java.nio.charset.StandardCharsets;

/**
 * Created by John on 2017/11/8.
 */
public class UdpCmdTail {
    /**
     *    CHECKSUM 的计算是：整个数据桢中除 SOI , EOI 和 CHECKSUM 本身之外的其他字符，
     *    按 ASCII 码累加求和，结果模 65536 余数取 反加 1；
     */
    private short sum;//SUM (CHECKSUM), 数据桢的校验码
    private  static  byte eoi = 0x0D;//EOI 结束码（固定为 0DH，发送是按 HEX 0DH 发送）；


    public UdpCmdTail(ByteBuf frame) throws CorruptedFrameException {
        this.sum = frame.readShort();
    }

    public UdpCmdTail() {

    }
    public UdpCmdTail(short  sum) {
        this.sum = sum;
    }

    public void packFrame(ByteBuf frame) {
        ByteBuf last = frame;//这个 缓冲区包含了 UDP的透传表头需要跳过(固定38个字节)
        last.readerIndex(1+38);//跳过第一字节的head
        byte[] req = new byte[last.readableBytes()];
        frame.readBytes(req);
        short sum = getSum(req);
        frame.resetReaderIndex();
        frame.writeCharSequence(getHexString(getLthByLenByte(sum)[0]), StandardCharsets.UTF_8);//前四位
        frame.writeCharSequence(getHexString(getLthByLenByte(sum)[1]), StandardCharsets.UTF_8);//低四位
        frame.writeCharSequence(getHexString(getLthByLenByte(sum)[2]), StandardCharsets.UTF_8);//低四位
        frame.writeCharSequence(getHexString(getLthByLenByte(sum)[3]), StandardCharsets.UTF_8);//低四位
        frame.writeByte(eoi);
    }

    private byte[] getLthByLenByte(short num){
        String lenString16 = BitUtil.getStringBit16(num);//获得高四位数据
        String first = lenString16.substring(0,4);//4位相间隔
        String second = lenString16.substring(4,8);//4位相间隔
        String third = lenString16.substring(8,12);//4位相间隔
        String four = lenString16.substring(12);//4位相间隔
        byte[] lenc = new byte[4];
        lenc[0] = (byte)BitUtil.BinstrToInt(first);
        lenc[1] = (byte)BitUtil.BinstrToInt(second);
        lenc[2] = (byte)BitUtil.BinstrToInt(third);
        lenc[3] = (byte)BitUtil.BinstrToInt(four);
        return lenc;
    }

    private String getHexString(int i){
        return Integer.toHexString(i).toUpperCase();//需要大写
    }

    /**
     * 获得校验位
     * @param data
     * @return
     */
    private short getSum(byte[] data) {
        int verify = data[0];
        for (int i = 1; i < data.length; i++) {
            verify += data[i];//码累加求和
        }
        verify &= 0xFFFF;
        verify = ~verify + 1;
        System.out.println("检验："+verify);
        return (short)verify;
    }

    private byte[] checksum(short vak){
        byte[] lenc = new byte[4];
        String bin16 = BitUtil.getStringBit16(vak);//转成16位的二进制字符串
        String first = bin16.substring(0,4);//4位相间隔
        String second = bin16.substring(4,8);//4位相间隔
        String third = bin16.substring(8,12);//4位相间隔
        String four = bin16.substring(12);//4位相间隔
        lenc[0] = (byte)BitUtil.BinstrToInt(first);
        lenc[1] = (byte)BitUtil.BinstrToInt(second);
        lenc[2] = (byte)BitUtil.BinstrToInt(third);
        lenc[3] = (byte)BitUtil.BinstrToInt(four);
        return lenc;
    }
 }
