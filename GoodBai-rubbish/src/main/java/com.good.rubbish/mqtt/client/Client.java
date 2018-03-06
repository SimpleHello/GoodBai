package com.good.rubbish.mqtt.client;

import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Scanner;

/**
 * Created by Mg on 2018/2/26.
 */
public class Client {
    private static final String OPERATION_EXIT = "EXIT";

    public static void main(String[] args) throws MqttException {
        ClientCommon common1 = new ClientCommon();
        common1.start("client01");
        ClientCommon common2 = new ClientCommon();
        common2.start("client02");
        ClientCommon common3 = new ClientCommon();
        common3.start("client03");
        ClientCommon common4 = new ClientCommon();
        common4.start("client04");
        //怎么让程序一直运行
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String in = scan.next().toString();
            if (OPERATION_EXIT.equals(in.toUpperCase())
                    || OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())) {
                System.out.println("您成功已退出！");
                break;
            }
            System.out.println("1");
        }
    }
}
