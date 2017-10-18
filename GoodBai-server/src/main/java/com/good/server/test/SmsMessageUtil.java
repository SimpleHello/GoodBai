package com.good.server.test;

import com.good.server.entity.MessageInfo;

import java.util.regex.Pattern;

/**
 * Created by John on 2017/10/18.
 */
public class SmsMessageUtil {

    public static String sendMessage(String phone,String message) throws Exception {
        if(!isMobile(phone)){
            throw new Exception("请正确输入手机号码!");
        }
        if(message==null||message.equals("")||message.length()>200){
            throw new Exception("发送的消息体不能超过200长度 或者 不能为空 !");
        }
        MessageInfo messageEntity = new MessageInfo(phone,message);
        return HttpClient.httpPost(messageEntity);
    }

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        if(mobile==null||"".equals(mobile)){
            return false;
        }
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
}
