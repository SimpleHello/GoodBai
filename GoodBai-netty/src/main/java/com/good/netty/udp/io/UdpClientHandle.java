package com.good.netty.udp.io;

import com.good.netty.udp.packet.receive.UdpCmdRevHeader;
import com.good.netty.udp.packet.receive.UdpCmdRevTail;
import com.good.netty.udp.packet.receive.UdpDoorRevHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mag on 2017/07/14. C1 接口业务相关接口处理
 */
public class UdpClientHandle extends SimpleChannelInboundHandler<DatagramPacket> {

	private static final Logger logger = LoggerFactory.getLogger(UdpClientHandle.class);

	public UdpClientHandle() {

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 启动心跳
		logger.info("start Running for {}", ctx.channel().remoteAddress());
		super.channelActive(ctx);

	}

	/**
	 * 服务器 关闭 之后触发事件
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("服务器断开 进行断开重连  for {}", ctx.channel().remoteAddress());
		super.channelInactive(ctx);
	}


	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
		ByteBuf in = msg.content();
		UdpDoorRevHeader revHeader = new UdpDoorRevHeader(in);
		if(revHeader==null){
			logger.error("报文收到数据失败");
			return ;
		}
		short commType = revHeader.getCommType();
		//上行方向（FSU=>SC）
		if(commType==1){
			System.out.println(in);
			UdpCmdRevHeader cmdRevHeader = new UdpCmdRevHeader(in);
			UdpCmdRevTail tail = new UdpCmdRevTail(in);
			System.out.println(cmdRevHeader.toString());
			System.out.println(tail.toString());
		}else if(commType==2){//FSU透传通道心跳

		}else{
			logger.error("未知commType {} 命令",commType);
			return ;
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (cause instanceof UnsupportedMessageTypeException) {
			logger.error("Channel {} {}, close it", ctx.channel().remoteAddress(), cause.getMessage());
			ctx.close();
		} else {
			super.exceptionCaught(ctx, cause);
		}
	}



}
