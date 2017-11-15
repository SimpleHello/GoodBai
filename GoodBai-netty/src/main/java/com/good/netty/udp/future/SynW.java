package com.good.netty.udp.future;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 同步等待 数据返回
 * @author mag
 */
public class SynW {

    public String writeInSync( 	final Channel channel,
                                final ByteBuf request,
                                final long timeout,
                                final String type) throws Exception {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (request == null) {
            throw new NullPointerException("request");
        }
        if (timeout <= 0) {
            throw new IllegalArgumentException("timeout <= 0");
        }
		String syncMapKey = "getResult";
        WriteFuture<String> future = new SyncWriteFuture(syncMapKey);
        SyncWriteMap.writeRecords.put(syncMapKey, future);
        System.out.println("put key:"+ syncMapKey);
        String response = _doWriteInSync(channel, request, timeout, future);
        SyncWriteMap.writeRecords.remove(syncMapKey);
        return response;
    }

    String _doWriteInSync(  final Channel channel,
                            final ByteBuf request,
                            final long timeout,
                            final WriteFuture<String> writeFuture) throws Exception {
        channel.writeAndFlush(request).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                writeFuture.setWriteResult(future.isSuccess());
                writeFuture.setCause(future.cause());
                // 失败
                if (!writeFuture.isWriteSuccess()) {
                    SyncWriteMap.writeRecords.remove(writeFuture.requestId());
                }
            }
        });
        String response = writeFuture.get(timeout, TimeUnit.SECONDS);
        if (response == null) {
            if (writeFuture.isTimeout()) {
                throw new TimeoutException();
            } else {
                // write exception
                throw new Exception(writeFuture.cause());
            }
        }
        return response;
    }

}
