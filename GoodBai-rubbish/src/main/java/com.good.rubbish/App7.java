package com.good.rubbish;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Hello world!
 */
public class App7 {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int noDay = Integer.valueOf(sdfDay.format(date));
        int noHour = calendar.get(Calendar.DATE);
        int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
        System.out.println("日期:"+noDay);
        System.out.println("小时:"+noHour);
        System.out.println("星期:"+week);
    }

    private static int[] getNoHout(int day,int week,int hour){
        int[] i = {9,12,15,18};
        return i;
    }
}
