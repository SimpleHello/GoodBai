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
public class CMCCUDPPackageUtil {

    private static CMCCUDPPackageUtil instance;
    
    private CMCCUDPPackageUtil() {
    }
    
    public static CMCCUDPPackageUtil instance() {
        return instance == null ? new CMCCUDPPackageUtil() : instance;
    }
    
    /**
     * 封包
     * @param udpPackage
     * @return 
     */
    public byte[] pack(CMCCUDPPackage udpPackage) {
        // 获取数据长度
        String data = udpPackage.getData();
        byte[] dataBytes = data.getBytes();
        System.out.println("Data: " + ByteUtil.toStringBytes(dataBytes));
        short dataLen = (short) dataBytes.length;
        short pLen = (short) (dataBytes.length + 5);
        
        // 对象属性转换成byte数组
        byte[] descAddrBytes = new byte[20];
        String descAddr = udpPackage.getDescAddr();
        if (descAddr != null) {
            descAddrBytes = descAddr.getBytes();
            descAddrBytes = Arrays.copyOf(descAddrBytes, 20);
        }
        System.out.println("DescAddr: " + ByteUtil.toStringBytes(descAddrBytes));
        byte[] srcAddrBytes = new byte[8];
        String srcAddr = udpPackage.getSrcAddr();
        if (srcAddr != null) {
            srcAddrBytes = srcAddr.getBytes();
            srcAddrBytes = Arrays.copyOf(srcAddrBytes, 8);
        }
        System.out.println("SrcAddr: " + ByteUtil.toStringBytes(srcAddrBytes));
        byte[] subDevBytes = { udpPackage.getSubDevType(), udpPackage.getSubDevAddr() };
        System.out.println("subDevType:"+udpPackage.getSubDevType() +";subDevAddr:"+udpPackage.getSubDevAddr());
        System.out.println("SubDev(Type+Addr): " + ByteUtil.toStringBytes(subDevBytes));
        byte[] pLenBytes = { (byte) pLen, (byte) (pLen >> 8) };
        System.out.println("pLen: " + ByteUtil.toStringBytes(pLenBytes));
        byte[] rtnFlagBytes = { udpPackage.getRtnFlag() };
        System.out.println("RtnFlag: " + ByteUtil.toStringBytes(rtnFlagBytes));
        byte[] commTypeBytes = { (byte) udpPackage.getCommType(), (byte) (udpPackage.getCommType() >> 8) };
        System.out.println("CommType: " + ByteUtil.toStringBytes(commTypeBytes));
        byte[] dataLenBytes = { (byte) dataLen, (byte) (dataLen >> 8) };
        System.out.println("DataLen: " + ByteUtil.toStringBytes(dataLenBytes));
        
        // 连接数据的byte数组，计算校验值
        byte[] conn1 = ByteUtil.concatBytes(
                descAddrBytes, srcAddrBytes, subDevBytes, pLenBytes, rtnFlagBytes, commTypeBytes, dataLenBytes, dataBytes);
        byte[] verifyBytes = { getVerify(conn1) };
        
        // 连接校验值，并进行转义
        byte[] conn2 = ByteUtil.concatBytes(conn1, verifyBytes);
        byte[] bytesEscape = escape(conn2);
        
        // 拼接首尾标识
        byte[] head = { CMCCUDPPackage.HEADER };
        byte[] tail = { CMCCUDPPackage.TAILER };
        byte[] resultPacket = ByteUtil.concatBytes(head, bytesEscape, tail);
        System.out.println("Pack Result: " + ByteUtil.toStringBytes(resultPacket));
        return resultPacket;
    }
    
    /**
     * 获得校验位
     * @param data
     * @return 
     */
    public byte getVerify(byte[] data) {
        byte verify = data[0];
        for (int i = 1; i < data.length; i++) {
            verify ^= data[i];
        }
        return verify;
    }
    
    /**
     * 转义
     * @param data
     * @return 
     */
    public byte[] escape(byte[] data) {
        int escapeLen = data.length;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == (byte) 0xFF || data[i] == (byte) 0xFE || data[i] == (byte) 0xFD) {
                escapeLen += 1;
            }
        }
        byte[] escapeBytes = new byte[escapeLen];
        for (int i = 0, j = 0; i < data.length; i++, j++) {
            byte dataByte = data[i];
            switch (dataByte) {
                case (byte) 0xFF:
                    escapeBytes[j] = (byte) 0xFD;
                    escapeBytes[j+1] = 0x00;
                    j += 1;
                    break;
                case (byte) 0xFE:
                    escapeBytes[j] = (byte) 0xFD;
                    escapeBytes[j+1] = 0x01;
                    j += 1;
                    break;
                case (byte) 0xFD:
                    escapeBytes[j] = (byte) 0xFD;
                    escapeBytes[j+1] = 0x02;
                    j += 1;
                    break;
                default:
                    escapeBytes[j] = dataByte;
            }
        }
        return escapeBytes;
    }
    
    /**
     * 拆包
     * @param packet
     * @return 
     * @throws Exception
     */
    public CMCCUDPPackage unpack(byte[] packet) throws Exception {
        // 确认首尾标记
        String illegal = null;
        for (int i = 0; i < packet.length; i++) {
            if ((packet[i] == (byte) 0xFF) && (i != 0)) {
                illegal = "0xFF";
                break;
            }
            if ((packet[i] == (byte) 0xFE) && (i != packet.length - 1)) {
                illegal = "0xFE";
                break;
            }
        }
        if (illegal != null) {
            throw new Exception("报文数据包含非法字段：" + illegal + "！");
        }

        // 移除首尾标记并解析转义
        byte[] content = new byte[packet.length - 2];
        System.arraycopy(packet, 1, content, 0, packet.length - 2);
        byte[] analyse = escapeAnalyse(content);
        if (verify(analyse) == false) {
            throw new Exception("报文数据包校验位异常！");
        }

        CMCCUDPPackage udpPackage = new CMCCUDPPackage();
        if (analyse[32] == (byte) 0xEE) {
            // 下行报文
            udpPackage.setDescAddr(new String(analyse, 0, 20));
            udpPackage.setSrcAddr(new String(analyse, 20, 8));
        } else {
            // 上行报文
            udpPackage.setDescAddr(new String(analyse, 0, 8));
            udpPackage.setSrcAddr(new String(analyse, 8, 20));
        }
        udpPackage.setSubDevType(analyse[28]);
        udpPackage.setSubDevAddr(analyse[29]);
        udpPackage.setpLen(ByteUtil.cutParseShort(analyse, 30));
        udpPackage.setRtnFlag(analyse[32]);
        udpPackage.setCommType(ByteUtil.cutParseShort(analyse, 33));
        if (udpPackage.getpLen() > 5) {
            udpPackage.setDataLen(ByteUtil.cutParseShort(analyse, 35));
            udpPackage.setData(new String(analyse, 37, udpPackage.getDataLen()));
        }
        udpPackage.setVerify(analyse[analyse.length - 1]);
        return udpPackage;
    }

    /**
     * 校验
     * @param data
     * @return
     */
    public boolean verify(byte[] data) {
        byte verify = data[0];
        for (int i = 1; i < data.length - 1; i++) {
            verify ^= data[i];
        }
        return verify == data[data.length - 1];
    }

    /**
     * 转义解析（不包含开始标记0xFF/结束标记0xFE）
     * @param packet
     * @return
     * @throws Exception
     */
    public byte[] escapeAnalyse(byte[] packet) throws Exception {
        int analyseLen = packet.length;
        for (int i = 0; i < packet.length; i++) {
            if (packet[i] == (byte) 0xFD) {
                analyseLen -= 1;
            }
        }
        byte[] analyseBytes = new byte[analyseLen];
        for (int i = 0, j = 0; i < analyseBytes.length; i++, j++) {
            byte packetByte = packet[j];
            switch (packetByte) {
                case (byte) 0xFD:
                    j += 1;
                    switch (packet[j]) {
                        case 0x00:analyseBytes[i] = (byte) 0xFF;break;
                        case 0x01:analyseBytes[i] = (byte) 0xFE;break;
                        case 0x02:analyseBytes[i] = (byte) 0xFD;break;
                        default:
                            throw new Exception("无法识别的转义字段：0xFD 0x" + ByteUtil.toStringByte(analyseBytes[i]) + "！");
                    }
                    break;
                case (byte) 0xFE:
                    throw new Exception("报文数据包含非法字段：0xFE！");
                case (byte) 0xFF:
                    throw new Exception("报文数据包含非法字段：0xFF！");
                default:
                    analyseBytes[i] = packetByte;
            }
        }
        return analyseBytes;
    }
    
}
