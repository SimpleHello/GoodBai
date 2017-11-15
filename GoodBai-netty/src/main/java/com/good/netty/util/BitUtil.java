package com.good.netty.util;

/**
 * Created by John on 2017/11/8.
 */
public class BitUtil {

    /**
     * 二进制 转10进制
     * @param binStr
     * @return
     */
    public static   int  BinstrToInt(String binStr) {
        return Integer.valueOf(binStr,2);
    }

    /**
     * 10进制转 固定长度的二进制
     * @param n
     * @param num
     * @return
     */
    public static   String getStringBit(int n,int num) {
        String str = "";
        for (int i = num-1; i >= 0; i--) {
            if ((n & (1 << i)) != 0) {
                str += "1";
            } else {
                str += "0";
            }
        }
        return str;
    }

    public static   String getStringBit16(int n) {
        return getStringBit(n,16);
    }

    public static   String getStringBit12(int n) {
        return getStringBit(n,12);
    }

    public static   String getStringBit4(int n) {
        return getStringBit(n,4);
    }
}
