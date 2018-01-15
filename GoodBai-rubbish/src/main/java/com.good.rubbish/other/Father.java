package com.good.rubbish.other;

/**
 * Created by Mg on 2017/11/30.
 */
public class Father {
    private  String name;
    private  int age;

    public Father(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Father() {

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
