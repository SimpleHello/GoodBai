package com.good.netty.tcp.decode;

import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.good.netty.tcp.common.SyncWriteMap;
import com.good.netty.tcp.entity.packet.InterfaceMessage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;


/**
 * Created by mag 
 * on 2017/07/14. 
 * C1 接口服务端解码处理
 */
public class ServerDecoder extends LengthFieldBasedFrameDecoder{
	
    private static final Logger logger = LoggerFactory.getLogger(ServerDecoder.class);

    public ServerDecoder() {
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
    	try{
    		String magic = in.getCharSequence(0, 10, StandardCharsets.UTF_8).toString().trim();
			int length = (int) in.getUnsignedInt(10);
			int byteBuf  = in.writerIndex();
			if("#dlhjtxxy#".equals(magic) && byteBuf < length){
				SyncWriteMap.ByteBufServiceMap.put("lostBuf", in);
				SyncWriteMap.ByteBufServiceLengthMap.put("lostBuf",length);
				logger.equals("发现 分包=== 保存到数据库中:"+length);
				return null;
			}
			if(!"#dlhjtxxy#".equals(magic) && SyncWriteMap.ByteBufServiceMap.containsKey("lostBuf")){
				logger.equals("发现 遗失的 分包=== 进行合并操作");
				CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
				compositeByteBuf.addComponents(true, SyncWriteMap.ByteBufServiceMap.get("lostBuf"), in);
				int succlen = SyncWriteMap.ByteBufServiceLengthMap.get("lostBuf");
				in = compositeByteBuf.asReadOnly();
				if(SyncWriteMap.ByteBufServiceMap.get("lostBuf").writerIndex()+byteBuf==succlen){
					SyncWriteMap.ByteBufServiceMap.remove("lostBuf");
					SyncWriteMap.ByteBufServiceLengthMap.remove("lostBuf");
					in = compositeByteBuf.asReadOnly();
					logger.equals("合并遗失的包完成=== 进行合并操作 合并之后的包:"+in);
				}else{
					logger.equals("不够---- 发现 遗失的 分包=== 进行合并操作 合并之后的包:"+in);
					SyncWriteMap.ByteBufServiceMap.put("lostBuf", in);
					return null;
				}
			}  
    		System.out.println("ByteBuf received: " + in);
            msg = new InterfaceMessage(in);
            System.out.println("message received: " + msg);
    	}catch(Exception e){
    		e.printStackTrace();
    		if(in!=null){
    			in.clear();//异常情况 清空 缓冲区 放弃此次读取
    		}
    	}
        return msg;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close(); //解析异常 不进行关闭
    }
}
