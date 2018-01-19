package com.good.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.good.core.util.RedisUtil;
import com.good.server.dao.redis.RedisTestDaoImpl;
import com.good.server.entity.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by John on 2017/10/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test01 {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static Date start = null;
    private static Date end = null;

    private static final Logger logger = LoggerFactory.getLogger(Test01.class);

    @Before
    public void setUp() {
        start = new Date();
        System.out.println("测试开始进行-----:"+sdf.format(start));
    }

    @After
    public void tearDown() {
        end = new Date();
        Long i = end.getTime()-start.getTime();
        System.out.println("测试 结束-----:"+sdf.format(end));
        System.out.println("共 耗时："+i+"毫秒");
    }

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTestDaoImpl redisTest;

    @Autowired
    TaskExecutor taskExecutor;
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
            for(int i=0;i<1000;i++){
                final int j = i;
                Student student = new Student(j,"name"+j,(int)getRandom(16,49));
                String json = JSONObject.toJSONString(student);
                redisUtil.lrSet("niu",json);
                System.out.println(sdff.format(new Date())+">> "+ student.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class MyTest implements Runnable{
        @Override
        public void run() {
            try{
                String threadName = Thread.currentThread().getName();
                if(getNum()<=0){
                    logger.info("threadName:"+threadName+" 未获取到数据!");
                    return;
                }
                String json = String.valueOf(redisUtil.llGet("student01"));
                logger.info("threadName:"+threadName+" 未获取到数据! json:"+json);
                if(json ==null){
                    logger.info("threadName:"+threadName+" 未获取到数据! json 是 null 啊 !!");
                    return;
                }
                Student student = JSON.parseObject(json,Student.class);
                if(student==null){
                    logger.info("threadName:"+threadName+" 未获取到数据! 但是还是 进来了!!!!");
                    return;
                }
                student.setThreadName(threadName);
                redisTest.saveRedisTest(student);
                System.out.println(sdff.format(new Date())+"threadName:"+threadName+">>"+student.toString());
                Thread.sleep(2000);//执行2秒
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    @Test
    public void alarmTest03() {
        try {
            while(true){
                if(getNum()>0){
                    taskExecutor.execute(new MyTest());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public synchronized long getNum() {
        return redisUtil.lGetListSize("student01");
    }

    @Test
    public void alarmTest() {
        try {
            for(int i=0;i<10000;i++){
                final int j = i;
                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Student student = new Student(j,"name"+j,(int)getRandom(16,49));
                        String json = JSONObject.toJSONString(student);
                        redisUtil.lrSet("student01",json);
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
