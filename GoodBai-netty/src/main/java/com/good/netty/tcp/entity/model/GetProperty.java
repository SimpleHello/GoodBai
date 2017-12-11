package com.good.netty.tcp.entity.model;

import com.good.netty.tcp.entity.body.Body;

import io.netty.buffer.ByteBuf;

/**
 * 请求数据属性信息协议
 * 
 * @author John
 *
 */
public class GetProperty extends Body{
	
	private int count; // 数据数量
	private int[] ids; // 长度为count
	
	
	public GetProperty() {}
	
	public GetProperty(int[] ids) {
		this.ids = ids;
		this.count = ids.length;
	}
	
    public GetProperty(ByteBuf frame) {
        this.parseFrame(frame);
    }

    @Override
    public int packLength() {
        // dynamic length
        return 4 + count*4;
    }

    @Override
    public boolean parseFrame(ByteBuf frame) {
        this.count = frame.readInt();
        this.ids = new int[this.count];
        for(int i = 0 ; i < this.count; i++) {
            this.ids[i] = frame.readInt();
        }
        return true;
    }

    @Override
    public void packFrame(ByteBuf frame) {
    	 frame.writeInt(this.count);
         for (int v : this.ids) {
        	 frame.writeInt(v);
         }
    }

    public void packFrameLE(ByteBuf frame) {
    	 frame.writeIntLE(this.count);
    	 for (int v : this.ids) {
        	 frame.writeIntLE(v);
         }
    }

    public int getCount() {
        return count;
    }

    public int[] getIds() {
        return ids;
    }

    @Override
    public String toString() {
    	String str = " ";
    	for(int i :this.ids){
    		str =str+i+" ,";
    	}
        return "count:"+count+" ids:"+ str;
    }
}
