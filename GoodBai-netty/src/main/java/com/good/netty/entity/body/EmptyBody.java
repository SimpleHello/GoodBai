package com.good.netty.entity.body;

import io.netty.buffer.ByteBuf;

/**
 * 空消息体定义，为了保证业务的正常运行
 */
public class EmptyBody extends Body{


    public EmptyBody() {}
    public int packLength() {
        return 0;
    }
    public boolean parseFrame(ByteBuf frame) {
        // do nothing
        return true;
    }

    public boolean parseFrameLE(ByteBuf frame) {
        // do nothing
        return true;
    }

    public void packFrame(ByteBuf frame) {
        // do nothing
    }

    public void packFrameLE(ByteBuf frame) {

    }

    public String toString() {
        return "Empty Message Body";
    }
}
