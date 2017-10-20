package com.good.server;

import com.good.server.redis.RedisUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by John on 2017/10/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test01 {

    @Before
    public void setUp() {
        System.out.println("测试开始进行-----");
    }

    @After
    public void tearDown() {
        System.out.println("测试 结束-----");
    }

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void alarmTest() {
        try {
            System.out.println(redisUtil.set("niubi","\"2222\"牛市"));
            System.out.println(redisUtil.get("niubi"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
