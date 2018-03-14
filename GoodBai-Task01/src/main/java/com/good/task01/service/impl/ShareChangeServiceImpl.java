package com.good.task01.service.impl;

import com.good.task01.dao.ShareChangeDaoImpl;
import com.good.task01.entity.ShareDetailEntity;
import com.good.task01.entity.ShareQuery;
import com.good.task01.service.ShareChangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Mg on 2018/3/14.
 */
@Service("shareChangeService")
public class ShareChangeServiceImpl implements ShareChangeService {

    @Resource(name="shareChangeDao")
    private ShareChangeDaoImpl shareChangeDao;

    /**
     * 取得 差异性数据
     *
     * @param query
     * @return
     */
    @Override
    public List<ShareDetailEntity> getChangeList(ShareQuery query)  throws Exception{
        return shareChangeDao.getChangeList(query);
    }

    /**
     * 修改状态
     *
     * @param query
     */
    @Override
    public void updateType(ShareQuery query)  throws Exception{
        shareChangeDao.updateDelType(query);
    }
}
