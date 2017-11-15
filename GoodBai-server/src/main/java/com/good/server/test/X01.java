package com.good.server.test;

import com.good.server.sms.util.SmsCatMessage;
import com.good.server.sms.util.SmsCatUtil;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 发送短信 成功 案例
 */
public class X01 {
    public static void main(String args[]) {
        String url = "http://10.0.6.135:9618";
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 20; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        try {
                            String time = sdf.format(new Date());
                            String msg = index+"#你好"+"你拨测试的短信("+index+")已在"+time+"已经发送到客户手中";
                            msg = URLEncoder.encode(msg,"GBK");
                            SmsCatMessage message = new SmsCatMessage("abc","123",1,"15868474510");
                            message.setMsg(msg);
                            String result = SmsCatUtil.sendMessage(message);
                            System.out.println(index+ " 返回:"+result);
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
