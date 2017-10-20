package com.good.rubbish.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by John on 2017/10/20.
 */
public class Jo01 {
    public static void main(String[] args) {
        String jsonStr = "测试_111_ss_xox_001";
        JSONObject json = JSON.parseObject(jsonStr);
        System.out.println(json);
    }
}
