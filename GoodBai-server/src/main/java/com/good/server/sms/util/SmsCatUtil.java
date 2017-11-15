package com.good.server.sms.util;

/**
 * Created by John on 2017/10/19.
 */
public class SmsCatUtil {

    public static String  sendMessage(SmsCatMessage message) throws Exception {
        String url = "http://10.0.6.135:9618";
        String requestParams = message.getParm();
        String result = HttpRequest.sendGet(url,requestParams);
        return result;
    }
}
