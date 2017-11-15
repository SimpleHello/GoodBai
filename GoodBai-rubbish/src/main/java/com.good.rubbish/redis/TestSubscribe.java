package com.good.rubbish.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by John on 2017/10/20.
 */
public class TestSubscribe {

    public void testSubscribe() throws Exception{
        Jedis jedis = new Jedis("10.0.6.36");
        jedis.auth("admin");
        RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
        jedis.subscribe(listener, "redisChatTest","123");
    }

    public static void main(String[] args)  throws Exception{
        TestSubscribe sb = new TestSubscribe();
        sb.testSubscribe();
    }
}
