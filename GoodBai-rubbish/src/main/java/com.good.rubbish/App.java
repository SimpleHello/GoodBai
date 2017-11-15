package com.good.rubbish;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        short lth01 = 1;
        short lth02 = 1;
        short x0 = (short)(lth01 << 12);
        short x1 = (short)((lth01 << 12)&(lth02 | 0x0F));
        System.out.println(lth01 << 12);
        System.out.println(x0);
    }
}
