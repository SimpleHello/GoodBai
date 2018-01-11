package com.good.rubbish.other;

/**
 * Created by Mg on 2017/12/15.
 */
public class Su01 {
    public static void main(String[] args) {
        System.out.println(score(42,40));
    }

    public static double score(int num,int throd) {
        if(num<throd){
            return 0;
        }
        double j = (num-throd)/10*0.5+50;
        return j;
    }
}
