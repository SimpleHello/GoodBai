package com.good.task01.dao;

import com.good.task01.base.DaoHelper;
import com.good.task01.base.Namespace;
import com.good.task01.entity.ShareDetailEntity;
import com.good.task01.entity.ShareQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Mg on 2018/3/14.
 */
@Repository("shareChangeDao")
public class ShareChangeDaoImpl {

    @Resource(name = "readDaoHelper")
    private DaoHelper readDao;

    public List<ShareDetailEntity> getChangeList(ShareQuery query) throws Exception {
        return readDao.query(Namespace.SHARE_DETAIL, "getChangeList", query);
    }

    public void updateDelType(ShareQuery query) throws Exception {
        if(query!=null && query.getIds()!=null&&query.getIds().size()>0){
            readDao.update(Namespace.SHARE_DETAIL, "updateDelType", query);
        }
    }

    public void updateAddType(ShareQuery query) throws Exception {
        if(query!=null && query.getIds()!=null&&query.getIds().size()>0){
            readDao.update(Namespace.SHARE_DETAIL, "updateAddType", query);
        }
    }

    public Integer getEndDetail(ShareQuery query) throws Exception {
        return readDao.count(Namespace.SHARE_DETAIL, "getEndDetail", query);
    }

}
