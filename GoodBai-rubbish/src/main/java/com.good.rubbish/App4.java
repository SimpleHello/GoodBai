package com.good.rubbish;

/**
 * Hello world!
 */
public class App4 {
    public static void main(String[] args) throws Exception {
//        byte[] si = {0,21,0,22,3,26,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-73,0,0,0,0,0,0,0,0,0,0};
//        byte[] si = {0, 21, 0, 22, 3, 32, 3, 0, 2};
        byte[] si = {0x00,0x15,0x00,0x16,0x03,0x20,0x00,0x00,0x01};
        byte i =  getXor(si);
        System.out.println(i);

    }

    public static byte getXor(byte[] datas){
        byte temp=datas[0];
        for (int i = 1; i <datas.length; i++) {
            temp ^=datas[i];
        }
        return temp;
    }
}
