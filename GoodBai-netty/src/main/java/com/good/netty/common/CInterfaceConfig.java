/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.good.netty.common;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.good.netty.entity.body.ConeConfigInit;
import com.good.netty.io.server.Client;

/**
 *
 * @author Mosaico
 */
public class CInterfaceConfig {
    
    private Client client;

    private Thread tcpServerThread;
    
    private InterfaceFactory interfaceFactory;

    public void initServer(ConeConfigInit init) {
        if (tcpServerThread != null) {
            tcpServerThread.interrupt();
        }
        client = new Client(init);
        interfaceFactory = new InterfaceFactory(client);
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
            Logger.getLogger(CInterfaceConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        tcpServerThread = null;
        return true;
    }

	public InterfaceFactory getInterfaceFactory() {
		return interfaceFactory;
	}

	public void setInterfaceFactory(InterfaceFactory interfaceFactory) {
		this.interfaceFactory = interfaceFactory;
	}

    

	

}
