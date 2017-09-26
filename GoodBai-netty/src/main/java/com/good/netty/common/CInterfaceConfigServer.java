/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.good.netty.common;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.good.netty.io.server.Server;

/**
 *
 * @author Mosaico
 */
public class CInterfaceConfigServer {
    
    private Server server;


    private Thread tcpServerThread;
    

    public void initServer(String host, int port) {
        if (tcpServerThread != null) {
            tcpServerThread.interrupt();
        }
        server = new Server(host, port);
    }
    
    public boolean enbaleServer() {
        if (server != null) {
            // 启动 C1 接口服务线程
            tcpServerThread = new Thread(server);
            tcpServerThread.start();
            return true;
        }
        return false;
    }
    
    public boolean disableServer() {
        try {
        	server.stop();   
            if (tcpServerThread != null) {
                tcpServerThread.interrupt();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(CInterfaceConfigServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        tcpServerThread = null;
        return true;
    }


}
