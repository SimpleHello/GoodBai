package com.good.server.controller.redis;

import com.good.server.base.JsonResult;
import com.good.server.entity.RedisInfo;
import com.good.server.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by John on 2017/10/20.
 */
@Controller
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/send.do")
    public @ResponseBody  JsonResult getList(@RequestBody RedisInfo info) throws Exception {
        // TODO Auto-generated method stub
        try{
            redisUtil.lpubish(info.getKey(),info.getValue());
            return new JsonResult("ok");
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
        }
    }
}
