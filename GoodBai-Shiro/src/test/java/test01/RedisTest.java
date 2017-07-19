package test01;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

	public static void main(String[] args) {
		try{
			
		
		// TODO Auto-generated method stub
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(200);
		config.setMaxIdle(50);
		config.setMinIdle(8);//设置最小空闲数
		config.setMaxWaitMillis(10000);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		//Idle时进行连接扫描
		config.setTestWhileIdle(true);
		//表示idle object evitor两次扫描之间要sleep的毫秒数
		config.setTimeBetweenEvictionRunsMillis(30000);
		//表示idle object evitor每次扫描的最多的对象数
		config.setNumTestsPerEvictionRun(10);
		//表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
		config.setMinEvictableIdleTimeMillis(60000);

		JedisPool pool = new JedisPool(config, "10.0.6.36", 6379, 10000, "admin", 0);
		System.out.println(pool.isClosed());
		Thread.sleep(1000);
		System.out.println(pool.isClosed());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
