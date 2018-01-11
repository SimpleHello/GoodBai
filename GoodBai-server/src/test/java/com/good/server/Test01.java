package com.good.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.good.server.entity.Student;
import com.good.core.util.RedisUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by John on 2017/10/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test01 {

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(80);
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
    public void alarmTest01() {
        try {
            String json = String.valueOf(redisUtil.llGet("student"));
            Student student = JSON.parseObject(json,Student.class);
            System.out.println(">> "+ student.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void alarmTest02() {
        try {
            for(int i=0;i<10;i++){
                final int j = i;
                Student student = new Student(j,"name"+j,(int)getRandom(16,49));
                String json = JSONObject.toJSONString(student);
                redisUtil.lrSet("student",json);
                System.out.println(">> "+ student.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void alarmTest() {
        try {
            for(int i=0;i<1250000;i++){
                final int j = i;
                fixedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Student student = new Student(j,"name"+j,(int)getRandom(16,49));
                        String json = JSONObject.toJSONString(student);
                        redisUtil.lrSet("ceshi",json);
                        System.out.println(">> "+ student.toString());
                    }
                });

            }
            Thread.sleep(600000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 范围内 随机数
     * @param max
     * @param min
     * @return
     */
    private double getRandom(int max,int min){
        Random random = new Random() ;
        double s = (double)random.nextInt(max)%(max-min+1) + min;
        double pr = random.nextInt(max)%(max-min+1) + min;
        double value = s + pr/100;
        return value;

    }
}
