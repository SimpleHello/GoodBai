package com.good.netty.tcp.handle;

import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.good.netty.tcp.entity.packet.Header;
import com.good.netty.tcp.entity.packet.InterfaceMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.util.AttributeKey;

/**
 * Created by mag on 2017/07/14. C1 接口业务相关接口处理
 */
public class ClientHandle extends ChannelInboundHandlerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(ClientHandle.class);

	public static AttributeKey<ScheduledFuture<?>> HeartBeatKey = AttributeKey.valueOf("HeartBeat");
	public static AttributeKey<Long> SerialsNoKey = AttributeKey.valueOf("SerialsNo");

	public ClientHandle() {

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 启动心跳
		logger.info("start Heart Beat Running for {}", ctx.channel().remoteAddress());
//		ScheduledFuture<?> heartBeat = ctx.channel().attr(ClientHandle.HeartBeatKey).getAndSet(null);
//		if (null == heartBeat) {
//			heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatTask(ctx), 10, 20, TimeUnit.SECONDS);
//			ctx.channel().attr(ClientHandle.HeartBeatKey).set(heartBeat);
//		}
		super.channelActive(ctx);

	}

	/**
	 * 服务器 关闭 之后触发事件
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("服务器断开 进行断开重连  for {}", ctx.channel().remoteAddress());
		// 停止心跳
//		ScheduledFuture<?> heartBeat = ctx.channel().attr(ClientHandle.HeartBeatKey).getAndSet(null);
//		if (null != heartBeat) {
//			logger.info("stop Heart Beat for {}", ctx.channel().remoteAddress());
//			heartBeat.cancel(true);
//		}
		super.channelInactive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		InterfaceMessage message = (InterfaceMessage) msg;
		Header header = null;
		InterfaceMessage messageAck = null;
		switch (message.getHeader().getType()) {
		// 102 登陆响应 => 直接等待响应获取
		case LOGIN_ACK:
			System.out.println("得到返回信息");
			break;
		case UNKNOWN:
			// 收到错误的报文类型，这些报文不应该在服务端收到，抛出错误
			throw new UnsupportedMessageTypeException("Invalid message received: " + message);
		default:
			// 收到错误的报文类型，这些报文不应该在服务端收到，抛出错误
			throw new UnsupportedMessageTypeException("Invalid message received: " + message);
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

	/**
	 * 心跳报文进程
	 * 
	 * @author John
	 *
	 */
	private class HeartBeatTask implements Runnable {
		private final ChannelHandlerContext ctx;

		HeartBeatTask(final ChannelHandlerContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public void run() {
			// HeartBeat body = new HeartBeat();
			// Header header = new Header(body.packLength(), Client.getSerial(),
			// PacketType.HEART_BEAT);
			// InterfaceMessage heartBeatMsg = new InterfaceMessage(header,
			// body);
			// logger.debug("HeartBeat Send: {} ", header);
			// ctx.writeAndFlush(heartBeatMsg.packFrame());
		}
	}

}
