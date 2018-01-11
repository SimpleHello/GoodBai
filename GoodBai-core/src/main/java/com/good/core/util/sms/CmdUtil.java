package com.good.core.util.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * java 程序调用 命令
 * Created by John on 2017/10/18.
 */
public class CmdUtil {


    private static int TIME_OUT = 8; // 短信发送 等待时长 单位 秒,一般短信发送时间 5秒左右


    private static final Logger LOGGER = LoggerFactory.getLogger(CmdUtil.class);

    /**
     * 获取操作系统名称
     * @return
     */
    public static String getOS(){
        Properties pros = System.getProperties();
        String os = (String) pros.get("os.name");
        System.out.println(os);
        if(os.startsWith("Windows")){//windows下调用系统命令
            return "Windows";
        }else if(os.startsWith("Linux")){//Linux下调用系统命令
            return "Linux";
        }else if(os.startsWith("Unix")){
            return "Unix";
        }
        return os;
    }

    public static boolean  execCmd(String cmd) {
        String os = getOS();
        Runtime rt = Runtime.getRuntime();
        try {
            Process proc = null;
            LOGGER.info("系统:"+os+"=>>开始执行的命令:"+cmd+"#!end");
            if("Linux".equals(os)||"Unix".equals(os)){
                String[] cmds = { "/bin/sh", "-c", cmd };
                proc = rt.exec(cmds);
            }else{
                proc = rt.exec(cmd);
            }
            ExecutorService exec = Executors.newCachedThreadPool();
//            showTime("start.......");//开发调试时间
            testTask(exec,TIME_OUT,proc.getInputStream(),"Console"); // 任务成功结束后等待计算结果，不需要等到15秒
//            System.out.println("发送:ConsoleFlag:"+ConsoleFlag);
            showTime("ConsoleFlag End ");
            Boolean errorFalg = testTask(exec,TIME_OUT,proc.getErrorStream(),"Error"); // 任务成功结束后等待计算结果，不需要等到15秒
//            System.out.println("发送:ErrorFalg:"+ErrorFalg);
//            showTime("ErrorFalg End ");
//            System.out.println("==============================================================");
            exec.shutdown();
            if(errorFalg==null){
                errorFalg = false;
            }
            LOGGER.info("End!");
            return errorFalg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean  testTask(ExecutorService exec,int timeout,InputStream is, String type) {
        StreamGobbler task = new StreamGobbler(is,type);
        Future<Boolean> future = exec.submit(task);
        Boolean taskResult = null;
        String failReason = null;
        if("Console".equals(type)){
            return  true;
        }
        try {
            showTime(type);
            // 等待计算结果，最长等待timeout秒，timeout秒后中止任务
            taskResult = future.get(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            failReason = "短信正在发送中，被停止发送...";
        } catch (ExecutionException e) {
            e.printStackTrace();
            failReason = "短信发送异常 ！";
        } catch (TimeoutException e) {
            e.printStackTrace();
            failReason = "短信发送超时 ！请检测短信设备是否连接正常";
            exec.shutdownNow();
        }
        showTime(type+"End");
        System.out.println("\ntaskResult : " + taskResult);
        System.out.println("failReason : " + failReason);
        return  taskResult;
    }

    private static  void showTime(String prx){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(" show the time: " + sdf.format(new Date())+ "   ## "+prx);
    }

    static class StreamGobbler implements Callable<Boolean> {

        private InputStream is;
        private String type;
        private Boolean result;

        public StreamGobbler(InputStream is, String type) {
            this.is = is;
            this.type = type;
        }

        @Override
        public Boolean call() throws Exception {
            InputStreamReader isr = null;
            BufferedReader br = null;
            try {
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                String line = null;
                result = false;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("Send succeeded")) {//发送成功
                        System.out.println("接收到 发送 成功的 记录");
                        result = true;
                    }
                }
                return result;
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (isr != null) {
                        isr.close();
                    }
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }
}
