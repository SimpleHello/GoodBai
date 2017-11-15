/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.good.netty.udp.common;

import com.good.netty.udp.io.UdpClient;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mosaico
 */
public class UdpClientConfig {
    
    private UdpClient client;

    private Thread tcpServerThread;
    
    private UdpFactory udpFactory;

    public void initServer(UdpConfigInit init) {
        if (tcpServerThread != null) {
            tcpServerThread.interrupt();
        }
        client = new UdpClient(init);
        udpFactory = new UdpFactory(client);
    }
    
    public boolean enbaleServer() throws InterruptedException {
        if (client != null) {
            // 启动 C1 接口服务线程
            tcpServerThread = new Thread(client);
            tcpServerThread.start();
    		Thread.sleep(500);
            return true;
        }
        return false;
    }
    
    public boolean disableServer() {
        try {
        	client.setRunFlag(false);
        	client.stop();   
            if (tcpServerThread != null) {
                tcpServerThread.interrupt();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(UdpClientConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        tcpServerThread = null;
        return true;
    }

    public UdpFactory getUdpFactory() {
        return udpFactory;
    }

    public void setUdpFactory(UdpFactory udpFactory) {
        this.udpFactory = udpFactory;
    }
}
