package com.good.netty.io.server;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.good.netty.io.decode.ServerDecoder;
import com.good.netty.io.handle.ServiceHandle;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.GlobalEventExecutor;


/**
 * Created by mag on 2017/07/14.
 * C1 接口服务端
 */
public final class Server implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    private int port;
    private String host;
    private int lscId;
    private ChannelGroup channels;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private static final AtomicInteger nextSequence = new AtomicInteger();
//    static long getSerial() {
//        long serial = nextSequence.getAndIncrement();
//        if(serial == Header.MAGIC) {
//            serial = nextSequence.getAndIncrement();
//        }
//        return serial;
//    }

    /**
     * bind server to all IP
     * @param port  int port number
     * @param lscId int (1~64) LSCID
     */
    public Server(int port, int lscId) {
        this(null, port, lscId);
    }

    public Server(String host, int port) {
        channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        this.host = host;
        this.port = port;
        // 监听
        this.bossGroup = new NioEventLoopGroup();
        //业务处理
        this.workerGroup = new NioEventLoopGroup();
    }
    /**
     * bind server to the specific IP
     * @param host String ip address
     * @param port int port number
     */
    public Server(String host, int port, int lscId) {
        channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        this.host = host;
        this.port = port;
        this.lscId = lscId;
        // 监听
        this.bossGroup = new NioEventLoopGroup();
        //业务处理
        this.workerGroup = new NioEventLoopGroup();
    }

    public int getLscId() {
        return lscId;
    }

    @Override
    public void run() {
        try {
            this.runner();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动C1 接口服务端，注意在独立线程中运行，该接口会阻塞一个线程运行
     * @throws Exception see netty
     */
    public void runner() throws Exception {

        logger.info("C1 Interface server starting...");

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("ServerDecoder", new ServerDecoder());
                            // 超时  ch.pipeline().addLast("ReadTimeoutHandler", new ReadTimeoutHandler(60));
                            ch.pipeline().addLast("ServiceHandle", new ServiceHandle());
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_SNDBUF, Integer.MAX_VALUE)
					.childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(1024 * 1024 * 64));;
            		

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();
            logger.info("C1 Interface server start.");

            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
            logger.info("C1 Interface server stop.");
            channels.close();
        } finally {
            this.stop();
        }
    }
    /**
     * 停止服务端运行
     */
    public void stop() throws InterruptedException {
        // Shut down all event loops to terminate all threads.
        this.workerGroup.shutdownGracefully();
        this.bossGroup.shutdownGracefully();

        // Wait until all threads are terminated.
        this.bossGroup.terminationFuture().sync();
        this.workerGroup.terminationFuture().sync();
    }

}
