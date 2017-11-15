/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.good.netty.util;

import java.util.Arrays;

/**
 *
 * @author Mosaico
 */
public class ByteUtil {
    
    /**
     * 连接两个byte数组
     * @param first
     * @param second
     * @return 
     */
    public static byte[] concatBytes(byte[] first, byte[] second) {
        int totalLength = first.length + second.length;  
        byte[] result = Arrays.copyOf(first, totalLength);  
        System.arraycopy(second, 0, result, first.length, second.length);  
        return result;  
    }
    
    /**
     * 连接多个byte数组
     * @param first
     * @param rest
     * @return 
     */
    public static byte[] concatBytes(byte[] first, byte[]... rest) {
        int totalLength = first.length;  
        for (byte[] array : rest) {  
            totalLength += array.length;  
        }  
        byte[] result = Arrays.copyOf(first, totalLength);  
        int offset = first.length;  
        for (byte[] array : rest) {  
            System.arraycopy(array, 0, result, offset, array.length);  
            offset += array.length;  
        }  
        return result;  
    }
    
    /**
     * 将byte数组按16进制转化成字符串
     * @param bytes
     * @return 
     */
    public static String toStringBytes(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            byte temp = bytes[i];
            int th8 = (temp & 0x80) >>> 7;
            int th7 = (temp & 0x40) >>> 6;
            int th6 = (temp & 0x20) >>> 5;
            int th5 = (temp & 0x10) >>> 4;
            int th4 = (temp & 0x08) >>> 3;
            int th3 = (temp & 0x04) >>> 2;
            int th2 = (temp & 0x02) >>> 1;
            int th1 = temp & 0x01;
            int[] resultInt = new int[2];
            resultInt[0] = th8 * 8 + th7 * 4 + th6 * 2 + th5 * 1;
            resultInt[1] = th4 * 8 + th3 * 4 + th2 * 2 + th1 * 1;
            for (int j = 0; j < resultInt.length; j++) {
                switch (resultInt[j]) {
                    case 15:builder.append("F");break;
                    case 14:builder.append("E");break;
                    case 13:builder.append("D");break;
                    case 12:builder.append("C");break;
                    case 11:builder.append("B");break;
                    case 10:builder.append("A");break;
                    default:builder.append(resultInt[j]);
                }
            }
            builder.append(" ");
        }
        return builder.toString();
    }
    
    /**
     * 将byte按16进制转化成字符串
     * @param b
     * @return 
     */
    public static String toStringByte(byte b) {
        StringBuilder builder = new StringBuilder();
        int th8 = (b & 0x80) >>> 7;
        int th7 = (b & 0x40) >>> 6;
        int th6 = (b & 0x20) >>> 5;
        int th5 = (b & 0x10) >>> 4;
        int th4 = (b & 0x08) >>> 3;
        int th3 = (b & 0x04) >>> 2;
        int th2 = (b & 0x02) >>> 1;
        int th1 = b & 0x01;
        int[] resultInt = new int[2];
        resultInt[0] = th8 * 8 + th7 * 4 + th6 * 2 + th5 * 1;
        resultInt[1] = th4 * 8 + th3 * 4 + th2 * 2 + th1 * 1;
        for (int j = 0; j < resultInt.length; j++) {
            switch (resultInt[j]) {
                case 15:builder.append("F");break;
                case 14:builder.append("E");break;
                case 13:builder.append("D");break;
                case 12:builder.append("C");break;
                case 11:builder.append("B");break;
                case 10:builder.append("A");break;
                default:builder.append(resultInt[j]);
            }
        }
        return builder.toString();
    }
    
    public static short cutParseShort(byte[] bytes, int pos) {
        if (bytes.length < pos + 2) {
            return 0x0000;
        }
        byte[] shortBytes = new byte[2];
        System.arraycopy(bytes, 30, shortBytes, 0, 2);
        return (short) ((shortBytes[1] << 8) | shortBytes[0]);
    }
    
    public static int getHeight4(byte data){//获取高四位
	    int height;
	    height = ((data & 0xf0) >> 4);
	    return height;
	}

	public static int getLow4(byte data){//获取低四位
	    int low;
	    low = (data & 0x0f);
	    return low;
	}

    public static byte[] getAsciiByByte(byte be){
        byte[] nex = new byte[2];
        nex[0] = getAsciiByByteType(be,1);//获取高四位byte
        nex[1] = getAsciiByByteType(be,0);//获取低四位byte
        return nex;
    }

    public static byte[] getAsciiByByte(String str){
        System.out.println(str);
        byte be = Byte.parseByte(str);
        byte[] nex = new byte[2];
        nex[0] = getAsciiByByteType(be,0);//获取高四位byte
        nex[1] = getAsciiByByteType(be,1);//获取低四位byte
        return nex;
    }

    public static byte getAsciiByByteType(byte be,int ty){
        int i = 0;
        if(ty==0){
            i = ByteUtil.getHeight4(be);
        }else{
            i = ByteUtil.getLow4(be);
        }
        String str = "";
        if(i>=10){
            str = Integer.toHexString(i);
        }else{
            str = String.valueOf(i);
        }
        str = str.toUpperCase();//要大写
        byte ascii = convertHexToStringByte(str);
        return ascii;
    }

    public static byte convertHexToStringByte(String str){
        char[] chars = str.toCharArray();
        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }
        return Byte.parseByte(hex.toString());
    }

    public static String convertHexToString(String str){
        char[] chars = str.toCharArray();
        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }
        return hex.toString();
    }

    /**
     * <p class="detail">
     * 功能：ASC转16进制
     * </p>
     * @author liudd
     * @date 2017年11月9日
     * @param hex
     * @return convertHexToString
     */
    public static String convertStringToHex(String hex){
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for( int i=0; i<hex.length()-1; i+=2 ){
            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }
}
