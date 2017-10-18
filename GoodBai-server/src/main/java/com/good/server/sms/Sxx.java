package com.good.server.sms;

import com.good.server.entity.MessageInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by John on 2017/10/18.
 */
public class Sxx {
    public static  void  main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        MessageInfo messageInfo = new MessageInfo("15868474510","测试代码+"+time);
        SmsDemo.sendMessage(messageInfo);
    }
}
