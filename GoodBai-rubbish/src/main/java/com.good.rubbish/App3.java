package com.good.rubbish;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.good.rubbish.other.Son;
import com.good.rubbish.other.SonInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App3 {
    public static void main(String[] args) throws Exception {
        List<Son> list = new ArrayList<>();
        Son son = new Son();
        son.setName("张三");
        son.setAge(44);
        son.setNiubi("牛逼");
        list.add(son);
        Son son2 = new Son();
        son2.setName("李四");
        son2.setAge(55);
        son2.setNiubi("牛逼5");
        list.add(son2);
        SonInfo info = new SonInfo();
        info.setSonList(list);
        JSONObject json = (JSONObject) JSON.toJSON(info);
        System.out.println(json);
    }
}
