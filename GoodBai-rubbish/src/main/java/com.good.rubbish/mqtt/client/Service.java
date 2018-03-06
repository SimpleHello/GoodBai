package com.good.rubbish.mqtt.client;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mg on 2018/2/26.
 */
public class Service {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    public static final String HOST = "tcp://10.0.5.38:1883";
    //定义MQTT的ID，可以在MQTT服务配置中指定
    private static final String clientid = "server";

    private MqttClient client;
    private MqttTopic topic11;
    private String userName = "admin";  //非必须
    private String passWord = "password";  //非必须

    private MqttMessage message;

    /**
     * 构造函数
     * @throws MqttException
     */
    public Service() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        connect();
    }

    /**
     *  用来连接服务器
     */
    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
//        options.setUserName(userName);
//        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback("service"));
            client.connect(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param topic
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        System.out.println(topic.getName());
        client.publish(topic.getName(),message);
    }

    /**
     *  启动入口
     * @param args
     * @throws MqttException
     */
    public static void main(String[] args) throws MqttException, InterruptedException {
        Service server = new Service();
        server.message = new MqttMessage();
        server.message.setQos(2);  //保证消息能到达一次
        server.message.setRetained(true);
        server.sendMessage(server,"client01");
        Thread.sleep(1000);
        server.sendMessage(server,"client02");
        Thread.sleep(1000);
        server.sendMessage(server,"client03");
        Thread.sleep(1000);
        server.sendMessage(server,"client04");
        Thread.sleep(1000);
    }

    public void sendMessage(Service server,String topic)  throws MqttException, InterruptedException {
        String info = dateFormat.format(new Date())+" hello word "+ topic +"!";
        server.message.setPayload(info.getBytes());
        server.publish(server.clientTopic(topic) , server.message);
    }

    private MqttTopic clientTopic(String topic){
        return  client.getTopic(topic);
    }
}
