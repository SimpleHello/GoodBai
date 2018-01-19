package com.good.rubbish;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Mg on 2017/11/23.
 */
public class App2 {
    public static void main(String[] args) throws  Exception{
//        getRandom(75,50);
        int i = 0;
        while (true){
            System.out.println("开始打印:"+i);
            sleep(10);
            i = i+1;
        }
    }

    private  static void Sxx()  throws  Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = sdf.parse("2017-12-21 13:21:55");
        Date xi= sdf.parse("2017-12-09 16:08:48");
        double xio = (double)((now.getTime()-xi.getTime())/1000)/60;
        System.out.println("7a57a5a743894a0e >> "+xio);
    }

    /**
     * 反转二进制数并输出结果的十进制值是多少
     */
    public static void test() {
        int d = 10;
        int len = Integer.toBinaryString(d).length();
        System.out.println(len);
        int b[] = new int[len];
        int index = 0;
        while (d != 0) {
            b[index++] = d & 0x01;
            d >>= 1;
        }
        int c = 0;
        d = 0;
        while (c < len) {
            d = d * 2 + b[c];
            c++;
        }
        System.out.println(d);
    }

    private  static void Sxx2()  throws  Exception{
        String str = "060200001000002";
        System.out.println(str.substring(str.length()-3));
    }

    /**
     * 范围内 随机数
     * @param max
     * @param min
     * @return
     */
    private static double getRandom(int max,int min){
        Random random = new Random();
        double s = (double)random.nextInt(max)%(max-min+1) + min;
        double pr = random.nextInt(max)%(max-min+1) + min;
        double value = s + pr/100;
        System.out.println(value);
        return value;

    }
}
