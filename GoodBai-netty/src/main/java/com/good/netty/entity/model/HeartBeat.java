package com.good.netty.entity.model;

import com.good.netty.entity.body.Body;

import io.netty.buffer.ByteBuf;

public class HeartBeat  extends Body {

	@Override
	public int packLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean parseFrame(ByteBuf frame) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void packFrame(ByteBuf frame) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void packFrameLE(ByteBuf frame) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
