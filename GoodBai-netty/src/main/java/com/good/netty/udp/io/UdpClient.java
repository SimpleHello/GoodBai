package com.good.netty.udp.io;

import com.good.netty.tcp.entity.body.Body;
import com.good.netty.udp.common.UdpConfigInit;
import com.good.netty.udp.entity.OpenDoor;
import com.good.netty.udp.future.SynW;
import com.good.netty.udp.packet.UdpCmdHeader;
import com.good.netty.udp.packet.UdpCmdMessage;
import com.good.netty.udp.packet.send.UdpDoorSendHeader;
import com.good.netty.udp.packet.send.UdpDoorSendMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mag on 2017/07/14. C1 接口客户端
 */
public final class UdpClient implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(UdpClient.class);

	private int port;
	private String host;
	private Channel channel;
	private Bootstrap bootstrap;
	private EventLoopGroup bossGroup;
	private boolean runFlag;
	private ChannelFuture future;
	private volatile int connectNum = 0;
	private static final AtomicInteger nextSequence = new AtomicInteger();

	public static long getSerial() {
		long serial = nextSequence.getAndIncrement();
		return serial;
	}

	/**
	 *
	 * @param init
     */
	public UdpClient(UdpConfigInit init) {
		this.host = init.getHost();
		this.port = init.getPort();
		this.runFlag = true;
		// 监听
		this.bossGroup = new NioEventLoopGroup();
	}

	@Override
	public void run() {
		try {
			this.runner();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动 接口服务端，注意在独立线程中运行，该接口会阻塞一个线程运行
	 * 
	 * @throws Exception
	 *             see netty
	 */
	public void runner() throws Exception {

		logger.info("UDP Interface server starting...");

		try {
			bootstrap = new Bootstrap();
			bootstrap.group(bossGroup).channel(NioDatagramChannel.class)
					.handler(new UdpClientDecoder()) //ChineseProverbServerHandler是业务处理类
					.handler(new UdpClientHandle())
					.option(ChannelOption.SO_BROADCAST, true)//支持广播
					.option(ChannelOption.SO_KEEPALIVE, true).option(ChannelOption.SO_RCVBUF, Integer.MAX_VALUE)
					.option(ChannelOption.SO_SNDBUF, Integer.MAX_VALUE)
					.option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(1024 * 1024 * 64));

			logger.info("udp connect server connect !.");

			future = bootstrap.connect(host, port).sync();
			channel = future.channel();
			channel.closeFuture().sync();

		} finally {
			 logger.error("udp connect runner server fail ! .");
			 this.stop();
		}

	}


	/**
	 * 停止服务端运行
	 */
	public void stop() throws InterruptedException {
		// Shut down all event loops to terminate all threads.
		this.bossGroup.shutdownGracefully();

		// Wait until all threads are terminated.
		this.bossGroup.terminationFuture().sync();
	}

	public boolean isRunFlag() {
		return runFlag;
	}

	public void setRunFlag(boolean runFlag) {
		this.runFlag = runFlag;
	}


	/**
	 * 开门
	 * @param descAddr
	 * @param srcAddr
	 * @param subDevAddr
	 * @param ver
	 * @param adr
     * @throws Exception
     */
	public String OpenDoor(String descAddr, String srcAddr, byte subDevAddr,byte ver, byte adr) throws Exception {
		byte cid1 = (byte)0x80;
		byte cid2=(byte)0x49;
		byte lenId = 0x06;
		byte comGroup = (byte)0xF1;
		byte type = (byte)0xED;
		OpenDoor dor = new OpenDoor();
		return setMessage(descAddr,srcAddr,subDevAddr,ver,adr,cid1,cid2,lenId,comGroup,type,dor);
	}

	private String setMessage(String descAddr, String srcAddr, byte subDevAddr,
							byte ver, byte adr, byte cid1,byte rtnCid2, byte lenId,byte comGroup,byte type,
							Body body) throws Exception {
		UdpCmdHeader cmdheader = new UdpCmdHeader(ver,adr,cid1,rtnCid2,lenId,comGroup,type);
		UdpCmdMessage cmdMessage = new UdpCmdMessage(cmdheader,body);
		return setMessage(descAddr,srcAddr,subDevAddr,cmdMessage);
	}

//	private void setMessage(String descAddr, String srcAddr, byte subDevAddr,UdpCmdMessage body) throws Exception {
//		UdpDoorSendHeader header = new UdpDoorSendHeader(descAddr,srcAddr,subDevAddr, (short)body.getLength());
//		UdpDoorSendMessage msg = new UdpDoorSendMessage(header, body);
//		ByteBuf packFrame =  msg.packFrame();
//		channel.writeAndFlush(packFrame);
//	}

	private String setMessage(String descAddr, String srcAddr, byte subDevAddr,UdpCmdMessage body) throws Exception {
		UdpDoorSendHeader header = new UdpDoorSendHeader(descAddr,srcAddr,subDevAddr, (short)body.getLength());
		UdpDoorSendMessage msg = new UdpDoorSendMessage(header, body);
		SynW s = new SynW();
		String sx = s.writeInSync(channel, msg.packFrame(), 1000, "login");
		return sx;
	}


}
