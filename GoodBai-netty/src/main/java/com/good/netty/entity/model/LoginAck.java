package com.good.netty.entity.model;

import com.good.netty.entity.body.Body;

import io.netty.buffer.ByteBuf;

/**
 * Created by wuyadong on 2016/12/7.
 * 登录响应消息
 */
public class LoginAck extends Body{

    private static final int BYTE_LEN = 4;

    private int rigthLevel;

    public LoginAck() {
        this.rigthLevel = 0;
    }

    public LoginAck(ByteBuf frame) {
        this.parseFrame(frame);
    }

    public LoginAck(int rigthLevel) {
        this.rigthLevel = rigthLevel;
    }


    @Override
    public int packLength() {
        return BYTE_LEN;
    }

    @Override
    public boolean parseFrame(ByteBuf frame) {
        this.rigthLevel = frame.readInt();
        return true;
    }

    @Override
    public void packFrame(ByteBuf frame) {
        frame.writeInt(this.rigthLevel);
    }

    @Override
    public void packFrameLE(ByteBuf frame) {
        frame.writeIntLE(this.rigthLevel);
    }

    @Override
    public String toString() {
        return "rigthLevel: "+ this.rigthLevel;
    }
}
