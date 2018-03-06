package com.good.rubbish.mqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mg on 2018/2/26.
 */
public class PushCallback  implements MqttCallback {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String clientName;

    PushCallback(){}

    PushCallback(String name){
        this.clientName = dateFormat.format(new Date())+ "^"+name;
    }

    @Override
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连System.out.println(clientid+"注册成功 时间:"+SIMPLE_DATE_FORMAT);
        System.out.println(this.clientName+" >> 连接断开，可以做重连");
        System.out.println(" cause:"+ cause.getMessage());
    }
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println(this.clientName+" >> deliveryComplete---------" + token.isComplete());
    }
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println(this.clientName+" >> 接收消息主题 : " + topic + "接收消息Qos : " + message.getQos());
        System.out.println(this.clientName+" >> 接收消息内容 : " + new String(message.getPayload()));
    }
}