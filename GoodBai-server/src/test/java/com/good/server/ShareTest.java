package com.good.server;

import com.good.server.entity.share.ShareDetailInfo;
import com.good.server.entity.share.ShareReportInfo;
import com.good.server.service.share.ShareService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by John on 2017/10/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ShareTest {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static Date start = null;
    private static Date end = null;

    private static final Logger logger = LoggerFactory.getLogger(ShareTest.class);

    @Before
    public void setUp() {
        start = new Date();
        System.out.println("测试开始进行-----:"+sdf.format(start));
    }

    @After
    public void tearDown() {
        end = new Date();
        Long i = end.getTime()-start.getTime();
        System.out.println("测试 结束-----:"+sdf.format(end));
        System.out.println("共 耗时："+i+"毫秒");
    }

    @Autowired
    private ShareService shareService;


    /**
     * 取得 当前最新数据
     *
     * @return
     * @throws Exception
     */
    @Test
    public void getShareDetail() throws Exception {
        List<ShareDetailInfo> list = shareService.getShareDetail();
        for(ShareDetailInfo info:list){
            System.out.println(info.toString());
        }
    }

    /**
     * 取得 统计
     *
     * 0-今天 1-最近3天 2-最近7天
     * @return
     * @throws Exception
     */
    @Test
    public void getShareReportInfo() throws Exception {
        List<ShareReportInfo> list = shareService.getShareReportInfo(0);
        for(ShareReportInfo info:list){
            System.out.println(info.toString());
        }
    }


}
