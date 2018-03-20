package com.good.server.service.share.impl;

import com.good.server.dao.share.ShareDaoImpl;
import com.good.server.entity.share.ShareDetailInfo;
import com.good.server.entity.share.ShareReportInfo;
import com.good.server.service.share.ShareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mg on 2018/3/20.
 */
@Service("shareService")
public class ShareServiceImpl implements ShareService{

    @Resource(name="shareDao")
    private ShareDaoImpl shareDao;
    /**
     * 取得 当前最新数据
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<ShareDetailInfo> getShareDetail() throws Exception {
        return shareDao.getShareDetail();
    }

    /**
     * 取得 统计
     *
     * @param type 0-今天 1-最近3天 2-最近7天
     * @return
     * @throws Exception
     */
    @Override
    public List<ShareReportInfo> getShareReportInfo(int type) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        ShareReportInfo info = new ShareReportInfo();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if(type>0){
            calendar.add(Calendar.DATE,-type);
        }
        int noDay = Integer.valueOf(sdf.format(calendar.getTime()));
        info.setNoDay(noDay);
        return shareDao.getShareReportInfo(info);
    }
}
