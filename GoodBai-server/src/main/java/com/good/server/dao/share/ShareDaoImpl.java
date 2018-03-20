package com.good.server.dao.share;


import com.good.server.base.DaoHelper;
import com.good.server.base.Namespace;
import com.good.server.entity.share.ShareDetailInfo;
import com.good.server.entity.share.ShareReportInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("shareDao")
public class ShareDaoImpl {

	@Resource(name = "readDaoHelper")
	private DaoHelper readDao;

	/**
	 * 取得 当前最新数据
	 *
	 * @return
	 * @throws Exception
	 */
	public List<ShareDetailInfo> getShareDetail() throws Exception {
		return readDao.query(Namespace.SHARE_DETAIL, "getShareDetail", null);
	}

	/**
	 * 取得 统计
	 *
	 * @param info 查询类
	 * @return
	 * @throws Exception
	 */
	public List<ShareReportInfo> getShareReportInfo(ShareReportInfo info) throws Exception {

		return readDao.query(Namespace.SHARE_DETAIL, "getShareReportInfo", info);
	}
}
