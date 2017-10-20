package com.good.rubbish.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by John on 2017/10/20.
 */
public class Test01 {
    private Jedis jedis = null;
    public Test01(){
        jedis =  new Jedis("10.0.6.36");
        jedis.auth("admin");
    }

    public String get(String key) throws Exception{
        return jedis.get(key);
    }

    public void set() throws Exception{
        jedis.set("admin","123xxx123");
    }

    public static void main(String[] args)  throws Exception{
        Test01 pu = new Test01();
        String str = pu.get("admin");
        System.out.println(str);

    }
}
