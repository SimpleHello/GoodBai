package com.good.server.test;

/**
 * 发送短信 成功 案例
 */
public class X01 {
    public static void main(String args[]) {
        try {
            String value = SmsMessageUtil.sendMessage("15868474510","你好 你拨打的测试(2017年10月18日 16:12:40)无法拨通");
            System.out.print(value);
        } catch (Exception e) {

        }

    }

}
