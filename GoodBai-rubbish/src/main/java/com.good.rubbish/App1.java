package com.good.rubbish;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Hello world!
 */
public class App1 {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
//        System.out.println("当前时间:"+sdf.format(date));
//        calendar.setTime(date);
//        calendar.add(Calendar.MINUTE , -5 ) ; //5分钟之前;
        System.out.println("5分钟前:"+sdf.format(calendar.getTime()));
//        System.out.println(calendar.get(Calendar.YEAR));
//        System.out.println(calendar.get(Calendar.MONTH)+1);
//        System.out.println(calendar.get(Calendar.DATE));
//        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
//        System.out.println(calendar.get(Calendar.MINUTE));
//        System.out.println(calendar.get(Calendar.SECOND));
        //将时间点设置为次月1号0点

    }
}
