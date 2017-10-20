package com.good.rubbish.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by John on 2017/10/20.
 */
public class TestPublish {
    private Jedis jedis = null;
    public TestPublish(){
        jedis =  new Jedis("10.0.6.36");
        jedis.auth("admin");
    }
    public void testPublish() throws Exception{
        jedis.publish("redisChatTest", "我是天才");
        Thread.sleep(5000);
        jedis.publish("redisChatTest", "我牛逼");
        Thread.sleep(5000);
        jedis.publish("redisChatTest", "哈哈");
    }

    public void set() throws Exception{
        jedis.set("admin","123xxx123");
    }

    public static void main(String[] args)  throws Exception{
        TestPublish pu = new TestPublish();
        pu.set();
    }
}
