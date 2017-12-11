package com.good.netty.tcp.server;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.good.netty.tcp.entity.body.Body;
import com.good.netty.tcp.entity.body.ConeConfigInit;
import com.good.netty.tcp.entity.body.PacketType;
import com.good.netty.tcp.entity.model.Login;
import com.good.netty.tcp.entity.packet.Header;
import com.good.netty.tcp.entity.packet.InterfaceMessage;
import com.good.netty.tcp.decode.ClientDecoder;
import com.good.netty.tcp.handle.ClientHandle;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by mag on 2017/07/14. C1 接口客户端
 */
public final class Client implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Client.class);

	private int port;
	private String host;
	private Channel channel;
	private Bootstrap bootstrap;
	private EventLoopGroup bossGroup;
	private boolean runFlag;
	private ChannelFuture future;
	private int timeLimit; // 单位 毫秒 ; netty 发送消息等待消息响应时间 ;默认 2秒
	private int reconnectTime;// 单位秒 ; FSU 重连间隔时间 ;默认10 秒
	private int disConnectTime;// 单位秒; FSU 超时产生告警;默认10分钟
	private volatile int connectNum = 0;
	private static final AtomicInteger nextSequence = new AtomicInteger();

	public static long getSerial() {
		long serial = nextSequence.getAndIncrement();
		return serial;
	}

	/**
	 * bind server to the specific IP
	 * 
	 * @param host
	 *            String ip address
	 * @param port
	 *            int port number
	 */
	public Client(ConeConfigInit init) {
		this.host = init.getHost();
		this.port = init.getPort();
		this.runFlag = true;
		this.timeLimit = init.getTimeLimit();
		this.reconnectTime = init.getReconnectTime();
		this.disConnectTime = init.getDisConnectTime();
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
	 * 启动C1 接口服务端，注意在独立线程中运行，该接口会阻塞一个线程运行
	 * 
	 * @throws Exception
	 *             see netty
	 */
	public void runner() throws Exception {

		logger.info("C1 Interface server starting...");

		try {
			bootstrap = new Bootstrap();
			bootstrap.group(bossGroup).channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO))
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast("ServerDecoder", new ClientDecoder());
							// ch.pipeline().addLast("ReadTimeoutHandler", new
							// ReadTimeoutHandler(60));// 超时设置
							ch.pipeline().addLast("ServiceHandle", new ClientHandle());
						}
					}).option(ChannelOption.SO_KEEPALIVE, true).option(ChannelOption.SO_RCVBUF, Integer.MAX_VALUE)
					.option(ChannelOption.SO_SNDBUF, Integer.MAX_VALUE)
					.option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(1024 * 1024 * 64));

			doConnect(reconnectTime);

			logger.info("C1 Interface connect server connect !.");

		} finally {
			// logger.error("C1 Interface connect runner server fail ! .");
			// this.stop();
		}
	}

	public void doConnect(int fsuTimeOut) {
		// 连接服务端
		try {
			future = bootstrap.connect(host, port).sync();
			channel = future.channel();

		} catch (Exception e) {
			logger.info("fsu server服务器 连接失败 {}/{}  this fsu server fail in connect", host, port);
		}
		if (channel != null && channel.isActive()) {
			connectNum = 0;
			logger.info("fsu server服务器 连接成功   {}/{} !!", host, port);
			return;
		}

		future.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture futureListener) {
				try {
					channel = futureListener.channel();
					if (futureListener.isSuccess() && channel != null && channel.isActive()) {
						logger.info("fsu server服务器 {}/{} 重连成功", host, port);
						connectNum = 0;// 初始化重连次数
					} else {
						connectNum = connectNum + 1;
						logger.info("fsu server服务器 {}/{} {}秒后进行 重连,重连次数：{} ", host, port, fsuTimeOut, connectNum);
						futureListener.channel().eventLoop().schedule(new Runnable() {
							@Override
							public void run() {
								try {
									doConnect(fsuTimeOut);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}, fsuTimeOut, TimeUnit.SECONDS);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// 异步堵塞 长连接 保持连接
		try {
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	 * 101 登录命令
	 * 
	 * @param data
	 *            数据
	 * @return 发送与否
	 */
	public void login(Login data, PacketType type) throws Exception {
		setMessage(data, type);
	}

	private void setMessage(Body data, PacketType type) throws Exception {
		 setMessage(data, type, timeLimit);
	}

	private void setMessage(Body data, PacketType type, int time) throws Exception {
		Header header = new Header(data.packLength(), getSerial(), type);
		InterfaceMessage msg = new InterfaceMessage(header, data);
		ByteBuf packFrame =  msg.packFrame();
		logger.info("type:{} send netty buffer:{} ",type,packFrame);
		channel.writeAndFlush(packFrame);
	}


}
