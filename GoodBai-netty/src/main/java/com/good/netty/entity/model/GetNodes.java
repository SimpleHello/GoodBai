package com.good.netty.entity.model;

import com.good.netty.entity.body.Body;

import io.netty.buffer.ByteBuf;

public class GetNodes extends Body {

	private long rootId;

	public GetNodes(){
		
	}
	
	public GetNodes(long rootId){
		this.rootId = rootId;
	}
	
	@Override
	public int packLength() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public boolean parseFrame(ByteBuf frame) {
		// TODO Auto-generated method stub
		this.rootId = frame.readUnsignedInt();
		return true;
	}

	@Override
	public void packFrame(ByteBuf frame) {
		// TODO Auto-generated method stub
		 frame.writeInt((int)this.rootId);
	}

	@Override
	public void packFrameLE(ByteBuf frame) {
		// TODO Auto-generated method stub
		frame.writeIntLE((int)this.rootId);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "this rootId:"+this.rootId;
	}

	public long getRootId() {
		return rootId;
	}

	public void setRootId(long rootId) {
		this.rootId = rootId;
	}

	
}
