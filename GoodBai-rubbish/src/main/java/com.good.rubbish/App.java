package com.good.rubbish;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String str = "330102000101700030217002001";
        String code = "0"+str.substring(19,24);
        String lastthee = code.substring(3);
        System.out.println(code);
        System.out.println(lastthee);
    }
}
