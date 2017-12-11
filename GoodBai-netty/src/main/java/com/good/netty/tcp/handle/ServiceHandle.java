package com.good.netty.tcp.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.good.netty.tcp.common.GetTestData;
import com.good.netty.tcp.entity.body.PacketType;
import com.good.netty.tcp.entity.model.Login;
import com.good.netty.tcp.entity.model.LoginAck;
import com.good.netty.tcp.entity.packet.Header;
import com.good.netty.tcp.entity.packet.InterfaceMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Created by mag on 2017/07/14.
 * C1 接口业务相关接口处理
 */
public class ServiceHandle extends ChannelInboundHandlerAdapter {
	
    private static final Logger logger = LoggerFactory.getLogger(ServiceHandle.class);

    public static AttributeKey<Long> SerialsNoKey = AttributeKey.valueOf("SerialsNo");

    //读取数据保存至数据库

    public ServiceHandle(){

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception  {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        InterfaceMessage message = (InterfaceMessage) msg;
        System.out.println(">>:"+message.toString());
        Header header = null;
        InterfaceMessage messageAck =null;
        try {
            SocketAddress remoteAddress = ctx.channel().remoteAddress();
            InetSocketAddress isa = (InetSocketAddress) remoteAddress;
            String ipInfo = "remote ip :" + isa.getAddress() + ";hostName:" + isa.getHostName() + ";port:" + isa.getPort();
            System.out.println(ipInfo);
        }catch (Exception e){

        }
        switch (message.getHeader().getType()) {
            // 101 登路
            case LOGIN:
            	Login login = (Login)message.getBody();
            	LoginAck ack = GetTestData.getLoginAck(login);
            	header = new Header(ack.packLength(),message.getHeader().getSerialsNo(),PacketType.LOGIN_ACK);
                messageAck = new InterfaceMessage(header, ack);
                ctx.writeAndFlush(messageAck.packFrame());
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
            logger.error("Channel {} {}, close it", ctx.channel().remoteAddress(), cause.getMessage() );
            ctx.close();
        }else {
            super.exceptionCaught(ctx, cause);
        }
    }
   
}
