package com.good.task01.service;

import com.good.task01.entity.ShareDetailEntity;
import com.good.task01.entity.ShareQuery;

import java.util.List;

/**
 * Created by Mg on 2018/3/14.
 */
public interface ShareChangeService {
    /**
     * 取得 差异性数据
     * @param query
     * @return
     */
     List<ShareDetailEntity> getChangeList(ShareQuery query)  throws Exception;

    /**
     * 修改状态
     * @param query
     */
    void updateType(ShareQuery query)  throws Exception;

}
