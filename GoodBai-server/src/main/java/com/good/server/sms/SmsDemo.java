package com.good.server.sms;

import com.alibaba.fastjson.JSONObject;
import com.good.server.entity.MessageInfo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;

/**
 * Created by John on 2017/10/18.
 */
public class SmsDemo {

    private static Logger logger = LogManager.getLogger(SmsDemo.class);

    private static <T extends MessageInfo> String createPostString(T message) throws IllegalAccessException, UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        Class cls = message.getClass();
        Field[] fields = cls.getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(message) != null) {
                    String value = field.get(message).toString();
                    value = URLEncoder.encode(value, "UTF-8");
                    stringBuilder.append(URLEncoder.encode(field.getName(), "UTF-8")).append("=").append(value).append("&");
                }
            }
        }
        if (cls.getGenericSuperclass() != null) {
            Class superCls = cls.getSuperclass();
            Field[] superFields = superCls.getDeclaredFields();
            if (superFields != null) {
                for (Field superField : superFields) {
                    superField.setAccessible(true);
                    if (superField.get(message) != null) {
                        stringBuilder.append(URLEncoder.encode(superField.getName(), "UTF-8")).append("=").append(URLEncoder.encode(String.valueOf(superField.get(message)), "UTF-8")).append("&");
                    }
                }
            }
        }
        if (stringBuilder.indexOf("&") != -1) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public static boolean sendMessage(MessageInfo message) throws IllegalAccessException, UnsupportedEncodingException {
        String url = "http://10.0.6.36:8087/GoodBai-server/message/sendMessage.do";
        String requestParams = createPostString(message);
        JSONObject responseObject = CommonUtil.httpRequest(url, "POST", requestParams);
        logger.info("Send Cloud Msg (JSON): {}"+ responseObject);
        return true;
    }

}
