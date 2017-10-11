package com.good.server.sms;

/**
 * Created by John on 2017/10/11.
 */
public class SendMessage {

//    private class OutboundNotification implements IOutboundMessageNotification {
//        public void process(String gatewayId, OutboundMessage msg) {
//            System.out.println("Outbound handler called from Gateway: "
//                    + gatewayId);
//            System.out.println(msg);
//        }
//
//        @Override
//        public void process(AGateway aGateway, OutboundMessage outboundMessage) {
//
//        }
//    }
//    @SuppressWarnings("deprecation")
//    public void sendSMS(String mobilePhones, String content) {
//        Service srv;
//        OutboundMessage msg;
//        OutboundNotification outboundNotification = new OutboundNotification();
//        srv = new Service();
//        SerialModemGateway gateway = new SerialModemGateway("modem.com3",
//                "COM3", 9600, "wavecom", ""); //设置端口与波特率
//        gateway.setInbound(true);
//        gateway.setOutbound(true);
//        gateway.setSimPin("0000");
//        gateway.setOutboundNotification(outboundNotification);
//        srv.addGateway(gateway);
//        System.out.println("初始化成功，准备开启服务");
//        try {
//            srv.startService();
//            System.out.println("服务启动成功");
//            String[] phones = mobilePhones.split(",");
//            for (int i = 0; i < phones.length; i++) {
//                msg = new OutboundMessage(phones[i], content);
//                msg.setEncoding(MessageEncodings.ENCUCS2); // 中文
//                srv.sendMessage(msg);
//            }
//            srv.stopService();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public static void main(String[] args) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.sendSMS("您要发送的手机号", "您要发送的内容！");
//    }
}
