package com.good.rubbish;

import com.good.rubbish.util.CRC16M;

/**
 * Hello world!
 */
public class App4 {
    public static void main(String[] args) throws Exception {
//        byte[] si = {0,21,0,22,3,26,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-73,0,0,0,0,0,0,0,0,0,0};
        byte[] si = {0, 21, 0, 22, 3, 32, 3, 0, 2};
        int xio = CRC16M.getCRC(si);
        System.out.println(xio);
        byte sox = (byte)247;
        System.out.println(sox);
    }
}
