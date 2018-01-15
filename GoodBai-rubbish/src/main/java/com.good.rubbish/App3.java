package com.good.rubbish;

import com.good.rubbish.other.Son;

import java.lang.reflect.Field;

/**
 * Hello world!
 */
public class App3 {
    public static void main(String[] args) throws Exception {
        Son son = new Son();
        son.setName("张三");
        son.setAge(44);
        son.setNiubi("牛逼");
        Class clazz = son.getClass();
        Class classFather = clazz.getSuperclass();
        Field field1 = classFather.getDeclaredField("name");
        System.out.println(field1);
    }
}
