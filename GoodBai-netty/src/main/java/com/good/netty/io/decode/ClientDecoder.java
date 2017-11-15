package com.good.netty.io.decode;

import com.good.netty.entity.packet.InterfaceMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteOrder;

/**
 * Created by mag 
 * on 2017/07/14. 
 * C1 接口服务端解码处理
 */
public class ClientDecoder extends LengthFieldBasedFrameDecoder {

	private static final Logger logger = LoggerFactory.getLogger(ClientDecoder.class);

	public ClientDecoder() {
		/*
        LengthFieldBasedFrameDecoder(
            ByteOrder byteOrder     解码的大小端模式
            int maxFrameLength,     解码时，处理每个帧数据的最大长度 - 无最大值
            int lengthFieldOffset,  该帧数据中，存放该帧数据的长度的数据的起始位置
            int lengthFieldLength,  记录该帧数据长度的字段本身的长度
            int lengthAdjustment,   修改帧数据长度字段中定义的值，可以为负数
            int initialBytesToStrip 解析的时候需要跳过的字节数
            boolean failFast        为true，当frame长度超过maxFrameLength时立即报TooLongFrameException异常，为false，读取完整个帧再报异常
        )
		 */
		super(ByteOrder.LITTLE_ENDIAN, Integer.MAX_VALUE, 4, 8, -8, 0, true);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		InterfaceMessage msg = null;
		try {
			if (in == null) {
				logger.debug("get the message:> ByteBuf in is null");
			}
			logger.info(" msg:"+ in);
			msg = new InterfaceMessage(in);
			logger.debug("message received: {}", msg);
		} catch (Exception e) {
			e.printStackTrace();
			if (in != null) {
				in.clear();// 异常情况 清空 缓冲区 放弃此次读取
			}
		}
		logger.debug("message received: {}", msg);
		return msg;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		// ctx.close(); 解析异常 不进行关闭
	}
}
