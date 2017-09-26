package com.good.netty.common;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.buffer.ByteBuf;

/**
 * 保存 数据信息 全局类
 * @author mag
 */
public class SyncWriteMap {
	

    /**
     * 客户端 丢包 合并
     */
    public static Map<String,ByteBuf> ByteBufMap = new ConcurrentHashMap<String, ByteBuf>();
    public static Map<String,Integer> ByteBufLengthMap = new ConcurrentHashMap<String, Integer>();
    
    /**
     * 服务端 丢包 合并
     */
    public static Map<String,ByteBuf> ByteBufServiceMap = new ConcurrentHashMap<String, ByteBuf>();
    public static Map<String,Integer> ByteBufServiceLengthMap = new ConcurrentHashMap<String, Integer>();
    
    public static Map<String,Date> ByteBufDateMap = new ConcurrentHashMap<String, Date>();

}
