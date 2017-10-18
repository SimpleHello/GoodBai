package com.good.server.controller;

import com.good.server.base.JsonResult;
import com.good.server.entity.MessageInfo;
import com.good.server.util.sms.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by John on 2017/10/18.
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    @RequestMapping("/sendMessage.do")
    public @ResponseBody
    JsonResult getList(@RequestBody MessageInfo message) throws Exception {
        return setMessageInfo(message);
    }

    @RequestMapping("/sendMessageNew.do")
    public @ResponseBody  JsonResult sendMessageNew(@RequestBody MessageInfo message) throws Exception {
        // TODO Auto-generated method stub
        return setMessageInfo(message);
    }

    private JsonResult setMessageInfo(MessageInfo message){
        try{
            SmsUtil.sendMessage(message.getPhone(),message.getMessgae());
            return new JsonResult("发送成功");
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
        }
    }

}
