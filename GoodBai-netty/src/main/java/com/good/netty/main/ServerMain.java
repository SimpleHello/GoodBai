package com.good.netty.main;

import com.good.netty.common.CInterfaceConfigServer;

/**
 * Created by mag on 2017/07/14.
 * C1 服务端 测试类
 */
public class ServerMain {

    public static void main(String[] args) {

        try {
            CInterfaceConfigServer config = new CInterfaceConfigServer();
            config.initServer("10.0.6.135",1234);
            config.enbaleServer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
