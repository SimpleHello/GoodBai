package com.good.netty.entity.body;

import io.netty.buffer.ByteBuf;

/**
 * Created by wuyadong on 2016/12/7.
 * C接口消息内消息体抽象类
 */
public abstract class Body {

    public Body(){

    }
    public abstract int packLength();

    /**
     * 解析报文接口
     * @param frame 报文
     * @return false - 报文解析有误
     */
    public abstract boolean parseFrame(ByteBuf frame);
    //public abstract boolean parseFrameLE(ByteBuf frame);
    public abstract void packFrame(ByteBuf frame);
    public abstract void packFrameLE(ByteBuf frame);
    public abstract String toString();
}

