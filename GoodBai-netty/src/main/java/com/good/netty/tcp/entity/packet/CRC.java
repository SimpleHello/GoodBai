package com.good.netty.tcp.entity.packet;

import com.good.netty.util.CRC16M;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.CorruptedFrameException;

public class CRC {
	// check head magic
    private int crc = 4660;
    
    
    public CRC(ByteBuf frame) throws CorruptedFrameException{
        //check length

        this.crc = frame.readShort();

    }
    public CRC() {

    }
    public CRC(int  crc) {
        this.crc = crc;
    }

    public void packFrame(ByteBuf frame) {
    	ByteBuf last = frame;
    	last.readerIndex(10);
    	byte[] req = new byte[last.readableBytes()];
    	frame.readBytes(req);
    	int crcc = CRC16M.getCRCInt(req);
    	System.out.println("CRC:"+crcc);
    	frame.resetReaderIndex();
    	frame.writeShort(crcc);
    }

    public void packFrameLE(ByteBuf frame) {
    	ByteBuf last = frame;
    	last.readerIndex(10);
    	byte[] req = new byte[last.readableBytes()];
    	frame.readBytes(req);
    	int crcc = CRC16M.getCRCInt(req);
    	frame.resetReaderIndex();
    	frame.writeShortLE(crcc);
    }

    public boolean parseFrame(ByteBuf frame) {
        this.crc = frame.readShort();
        return true;
    } 


	public int getCrc() {
		return crc;
	}

	public void setCrc(int crc) {
		this.crc = crc;
	}

}
