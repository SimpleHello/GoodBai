package com.good.netty.tcp.entity.body;

/**
 * Created by wuyadong on 2016/12/7.
 * 消息体常量定义
 */
public class BodyStatic {
    public final static int NAME_LENGTH     = 40;   //名字命名长度
//    public final static int USER_LENGTH     = 20;   //用户名长度
    public final static int PASSWORD_LEN    = 20;   //口令长度
    public final static int EVENT_LENGTH    = 160;  //事件信息长度
    public final static int ALARM_LENGTH    = 165;  //告警事件信息长度
    //public final static int LOGIN_LENGTH    = 100;  //登录事件信息长度 ，好像没用 by wuyadong
    public final static int DES_LENGTH      = 160;   //描述信息长度
    public final static int UNIT_LENGTH     = 8;    //数据单位的长度
    public final static int STATE_LENGTH    = 160;  //态值描述长度
    public final static int VER_LENGTH      = 40;   //版本描述的长度
    public final static int AREACODE_LENGTH = 7;    //区域编码长度
    public final static int STATIONCODE_LENGTH = 12; //机房编码长度
    public final static int NODECODE_LENGTH = 10;   //监控信号编码长度
}
