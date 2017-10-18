package com.good.server.util.sms;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by John on 2017/10/18.
 */
public class ThreadPoolWithcallable {

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        final long t1 = System.currentTimeMillis();
        final Thread currentThread = Thread.currentThread();
        Future<String> submit = pool.submit(new Callable<String>(){
            @Override
            public String call() throws Exception {

                Thread.sleep(5000);
                System.out.println("b--"+Thread.currentThread().getName() +" 我在线程里面执行");
                return "b--"+Thread.currentThread().getName();
            }
        });
        //从Future中get结果，这个方法是会被阻塞的，一直要等到线程任务返回结果
        System.out.println("我不是阻塞的，啦啦啦啦");
        System.out.println(submit.get());
        System.out.println("222我不是阻塞的，啦啦啦啦");

        pool.shutdown();

    }

}
