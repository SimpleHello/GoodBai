package com.good.core.util.sms;

/**
 * Created by John on 2017/10/18.
 */
public class SmsUtil {

    /**
     * 发送 短信 方法，根据不同的 系统 采用不同的发送方法
     * windos系统:采用 smslib jar包接口
     * liunx系统 采用gnokii 调用命令
     * @param phone
     * @param message
     */
    public static boolean sendMessage(String phone,String message)  throws Exception{
        String osName = CmdUtil.getOS();//获取当前系统
        if(osName.startsWith("Windows")){//windows下调用系统命令
            System.out.print("windows下的短信功能 尚未完成");
        }else if(osName.startsWith("Linux")||osName.startsWith("Unix")){//Linux下调用系统命令
            System.out.print(osName+"  >> liunx环境下 发送短信猫");
            return sendMessageLinux(phone,message);
        }
        return false;
    }


    public static boolean sendMessageLinux(String phone,String message) throws Exception{
        String commond = "echo -n \""+message+"\" | gnokii --sendsms "+ phone;
        return CmdUtil.execCmd(commond);
    }


}
