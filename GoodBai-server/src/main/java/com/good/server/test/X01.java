package com.good.server.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 发送短信 成功 案例
 */
public class X01 {
    public static void main(String args[]) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 5; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        try {
                            String time = sdf.format(new Date());
                            String meg = "你好"+index+"你拨打的测试"+time+"已经发送到客户手中";
                            System.out.println(time+" "+index+" ^^开始发送消息:"+meg);
                            String value = SmsMessageUtil.sendMessage("15868474510",meg);
                            System.out.println(index+" >> 返回:"+value);

                        } catch (Exception e) {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

}
