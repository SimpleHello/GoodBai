package com.good.rubbish;

import com.good.rubbish.other.Father;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 */
public class App5 {

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws Exception {
        List<Father> list = new ArrayList<>();
        list.add(new Father("张三",1));
        list.add(new Father("李四",2));
        list.add(new Father("王武",3));
        for(Father father:list){
            final String name = father.getName();
            final int age = father.getAge();
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(name+age);
                }
            });
        }
    }
}
