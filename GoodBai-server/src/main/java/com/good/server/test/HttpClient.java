package com.good.server.test;

import com.good.server.base.JSONUtil;
import com.good.server.entity.MessageInfo;
import okhttp3.*;

import java.io.IOException;

/**
 * Created by John on 2017/10/18.
 */
public class HttpClient {

    private static  String urlPath = "http://10.0.6.36:8087/GoodBai-server/message/sendMessage.do";

    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    public static String httpGet(String url) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string(); // 返回的是string 类型，json的mapper可以直接处理
    }

    public static String httpPost(String url, String json) throws Exception {
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    public static String httpPost(MessageInfo message) throws Exception {
        String json = JSONUtil.toJsonString(message);
        return httpPost(urlPath,json);
    }



}
