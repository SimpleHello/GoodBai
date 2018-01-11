package com.good.rubbish;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Hello world!
 */
public class App6 {
    public static void main(String[] args) throws Exception {
        int[] ix = new int[20];
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<20;i++){
            double x = Math.random()*10;
            int num = (int)x;
            ix[i] = num;
            list.add(num);
            System.out.print(num+" ");
        }
        System.out.println();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        Date start = new Date();
        System.out.println("开始进行==："+sdf.format(start));
        test02(list);
        Date end = new Date();
        System.out.println("结束进行==："+sdf.format(end));
        System.out.println("耗时："+(end.getTime()-start.getTime()));
    }

    private static void test02(List<Integer> ix){
        Collections.sort(ix);
        for(int i=0;i<ix.size();i++){
            System.out.print(ix.get(i)+" ");
        }
        System.out.println();
        Map<Integer,Integer> maps = new HashMap<>();
        int st = 0;
        for(int i=0;i<ix.size();i++){
            if(i==ix.size()-1){
                return ;
            }
            if(ix.get(i+1)!=ix.get(i)){
                int value = (i+1-st);
                if(i==18){
                    System.out.println("1");
                }
                if(i ==ix.size()-2){
                    System.out.println("key= "+ ix.get(i+1) + " and value= " + 1);
                    value = value+1;
                }
                System.out.println("key= "+ ix.get(i) + " and value= " + value);
                st = i+1;
            }

        }
    }

    private static void test01(int[] ix){
        Map<Integer,Integer> maps = new HashMap<>();
        for(Integer x:ix){
            if(maps.containsKey(x)){
                maps.put(x,maps.get(x)+1);
            }else{
                maps.put(x,1);
            }
        }
        System.out.println("通过Map.keySet遍历key和value：");
        for (Integer key : maps.keySet()) {
            System.out.println("key= "+ key + " and value= " + maps.get(key));
        }
    }
}
