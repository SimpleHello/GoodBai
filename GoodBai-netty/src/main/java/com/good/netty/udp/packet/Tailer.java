package com.good.netty.udp.packet;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.CorruptedFrameException;

/**
 * 报文 尾部 2个字段：校验位 和 结尾标识位
 */
public class Tailer {

    private byte verify;            // 校验（1 byte）：转义前对数据包进行异或校验，不含包头包尾

    public static final byte TAILER = (byte) 0xFE;  // 协议包结束标记（1 byte）

    public Tailer(ByteBuf frame) throws CorruptedFrameException {
        this.verify = frame.readByte();
    }

    public Tailer() {

    }
    public Tailer(byte  verify) {
        this.verify = verify;
    }

    public void packFrame(ByteBuf frame) {
        ByteBuf last = frame;
        last.readerIndex(1);//跳过第一字节的head
        byte[] req = new byte[last.readableBytes()];
        frame.readBytes(req);
        byte verify = getVerify(req);
        frame.resetReaderIndex();
        frame.writeByte(verify);
        frame.writeByte(TAILER);
    }

    /**
     * 获得校验位
     * @param data
     * @return
     */
    private byte getVerify(byte[] data) {
        byte verify = data[0];
        for (int i = 1; i < data.length; i++) {
            verify ^= data[i];
        }
        return verify;
    }
}
