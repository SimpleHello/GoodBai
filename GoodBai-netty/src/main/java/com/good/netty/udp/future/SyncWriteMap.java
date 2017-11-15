package com.good.netty.udp.future;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 保存 数据信息 全局类
 * @author mag
 */
public class SyncWriteMap {
	
    public static Map<String, WriteFuture> writeRecords = new ConcurrentHashMap<String, WriteFuture>();
}
