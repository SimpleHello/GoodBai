package com.good.netty.entity.model;

import java.nio.charset.StandardCharsets;

import com.good.netty.entity.body.Body;
import com.good.netty.entity.body.BodyStatic;

import io.netty.buffer.ByteBuf;


/**
 * Created by wuyadong on 2016/12/5.
 * 用户(用于登录)消息体封装
 */

public class Login extends Body {
	
    private static final int BYTE_LEN = 60;

    private String name;
    private String password;

    public Login() {
    }

    public Login(ByteBuf frame) {
        this.parseFrame(frame);
    }

    public Login(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public int packLength() {
        return BYTE_LEN;
    }

    @Override
    public final boolean parseFrame(ByteBuf frame) {
        this.name = frame.getCharSequence(0, BodyStatic.NAME_LENGTH, StandardCharsets.UTF_8).toString().trim();
        this.password = frame.getCharSequence(BodyStatic.NAME_LENGTH, BodyStatic.PASSWORD_LEN, StandardCharsets.UTF_8).toString().trim();
        return true;
    }

    @Override
    public final void packFrame(ByteBuf frame) {
        String nameWithStaticLen = String.format("%1$-40s", this.name);
        String password = String.format("%1$-20s", this.password);

        frame.writeCharSequence(nameWithStaticLen, StandardCharsets.UTF_8);
        frame.writeCharSequence(password, StandardCharsets.UTF_8);
//        frame.writeCharSequence("cr", StandardCharsets.UTF_8);
    }

    @Override
    public void packFrameLE(ByteBuf frame) {
        // 仅字符串，对齐方式一致
        this.packFrame(frame);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public final String toString() {
        return "User:" + "name: " + this.name + " password: " + this.password;
    }
}
