package com.good.task01.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 运行的时候 把 pom.xml里面的<resources> 控制资源文件的拷贝 这个 注释掉 不然报错！！！
 * @author John
 *
 */
public class SyncServer {

    private final AbstractApplicationContext springContext;

    public SyncServer() {
        springContext = new ClassPathXmlApplicationContext("classpath:conf/applicationContext.xml");
    }

    public void stop() {
        springContext.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SyncServer server = new SyncServer();
        Runtime.getRuntime().addShutdownHook(new ShutdownThread(server));
    }

    private static class ShutdownThread extends Thread {

        private final SyncServer server;

        public ShutdownThread(SyncServer server) {
            this.server = server;
        }

        @Override
        public void run() {
            server.stop();
        }
    }
    
}
