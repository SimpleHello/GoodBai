package com.good.rubbish;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mg on 2017/11/23.
 */
public class App2 {
    public static void main(String[] args) throws  Exception{
        Long ls = 1511749304004L;
        Date dx = new Date(ls);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("7a57a5a743894a0e >> "+sdf.format(dx));
    }

    private  static void Sxx()  throws  Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Date xi= sdf.parse("2017-11-24 13:10:20");
        double xio = (double)((now.getTime()-xi.getTime())/1000)/60;
        System.out.println("7a57a5a743894a0e >> "+xio);
    }

    private  static void Sxx2()  throws  Exception{
        String str = "060200001000002";
        System.out.println(str.substring(str.length()-3));
    }
}
