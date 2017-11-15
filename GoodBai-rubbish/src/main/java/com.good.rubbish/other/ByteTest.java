package com.good.rubbish.other;

/**
 * Created by John on 2017/11/6.
 */
public class ByteTest {
    public static void main(String[] args) {
        byte si = (byte)245;
        byte sis = (byte)(si & 0xFF);
        System.out.print(sis);
    }
}
