package com.good.server.controller;

import com.good.server.base.JsonResult;
import com.good.server.entity.MessageInfo;
import com.good.core.util.sms.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/10/18.
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    private final List<MessageInfo> list = new ArrayList<>();

    @RequestMapping("/sendMessage.do")
    public @ResponseBody
    JsonResult getList(@RequestBody MessageInfo message) throws Exception {
        try{
            list.add(message);
            boolean flag = SmsUtil.sendMessage(message.getPhone(),message.getMessgae());
            if(flag){
                return new JsonResult("发送成功");
            }else{
                return new JsonResult(-1,"短信信息发送失败",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
        }
    }

}
