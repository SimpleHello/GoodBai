package com.good.server.service.share;

import com.good.server.entity.share.ShareDetailInfo;
import com.good.server.entity.share.ShareReportInfo;

import java.util.List;

/**
 * Created by Mg on 2018/3/20.
 */
public interface ShareService {

    /**
     * 取得 当前最新数据
     * @return
     * @throws Exception
     */
    List<ShareDetailInfo> getShareDetail()  throws Exception;
    /**
     * 取得 统计
     * @param type 0-今天 1-最近3天 2-最近7天
     * @return
     * @throws Exception
     */
    List<ShareReportInfo> getShareReportInfo(int type)  throws Exception;

}
