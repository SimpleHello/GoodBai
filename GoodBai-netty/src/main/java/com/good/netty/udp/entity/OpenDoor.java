package com.good.netty.udp.entity;

import com.good.netty.tcp.entity.body.Body;
import com.good.netty.util.ByteUtil;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

/**
 * Created by John on 2017/11/8.
 */
public class OpenDoor extends Body{

    private byte info = 0x01;

    @Override
    public int packLength() {
        return 1;
    }

    @Override
    public boolean parseFrame(ByteBuf frame) {
        this.info = frame.readByte();
        return false;
    }

    @Override
    public void packFrame(ByteBuf frame) {
        frame.writeCharSequence(getHexString(ByteUtil.getHeight4(this.info)), StandardCharsets.UTF_8);//高四位
        frame.writeCharSequence(getHexString(ByteUtil.getLow4(this.info)), StandardCharsets.UTF_8);//低四位
    }

    private String getHexString(int i){
        return Integer.toHexString(i).toUpperCase();//需要大写
    }
    @Override
    public void packFrameLE(ByteBuf frame) {
        frame.writeByte(this.info);
    }

    @Override
    public String toString() {
        return "OpenDoor{" +
                "info=" + info +
                '}';
    }
}
