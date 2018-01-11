package com.good.rubbish;

import java.util.Arrays;

/**
 * Created by Mg on 2017/12/25.
 */
public class Endians {

    public static void main(String[] args) {
       byte[] ax = toLH(1);
        System.out.println(Arrays.toString(ax));
    }

    //将整数按照小端存放，低字节出访低位
    public static byte[] toLH(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * 将int转为大端，低字节存储高位
     *
     * @param n
     * int
     * @return byte[]
     */
    public static byte[] toHH(int n) {
        byte[] b = new byte[4];
        b[3] = (byte) (n & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        b[1] = (byte) (n >> 16 & 0xff);
        b[0] = (byte) (n >> 24 & 0xff);
        return b;
    }
}
