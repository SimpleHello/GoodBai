package com.good.server.redis;

import com.alibaba.fastjson.JSON;
import com.good.core.util.RedisUtil;
import com.good.server.dao.redis.RedisTestDaoImpl;
import com.good.server.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import static java.lang.Thread.sleep;

/**
 * Created by Mg on 2018/1/19.
 */
public class RedisProcess {

    private RedisUtil redisUtil;
    private RedisTestDaoImpl redisTest;
    private String key;
    private TaskExecutor taskExecutor;

    private static final Logger logger = LoggerFactory.getLogger(RedisProcess.class);

    class MyTest implements Runnable{
        @Override
        public void run() {
            try{
                String threadName = Thread.currentThread().getName();
                if(getNum()<=0){
                    logger.info("threadName:"+threadName+" 未获取到数据!");
                    return;
                }
                String json = String.valueOf(redisUtil.llGet(key));
                logger.info("threadName:"+threadName+"json:"+json);
                if(json ==null||"null".equals(json)||"".equals(json)){
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
                System.out.println("threadName:"+threadName+">>"+student.toString());
                sleep(2000);//执行2秒
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("启动 redis List:{} 监听!!",key);
                    while(true){
                        long num = getNum();
                        if(num>0){
                            taskExecutor.execute(new MyTest());
                        }
                        sleep(10);//减少CPU占用率
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private synchronized long getNum() {
        return redisUtil.lGetListSize(key);
    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    public RedisTestDaoImpl getRedisTest() {
        return redisTest;
    }

    public void setRedisTest(RedisTestDaoImpl redisTest) {
        this.redisTest = redisTest;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}
