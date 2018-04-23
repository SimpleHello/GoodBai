package com.good.server.dao.share;


import com.good.server.base.DaoHelper;
import com.good.server.base.Namespace;
import com.good.server.entity.share.ShareDetailInfo;
import com.good.server.entity.share.ShareHisInfo;
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
	public List<ShareDetailInfo> getShareDetail(ShareDetailInfo info) throws Exception {
		String code = info.getCode();
		if(code!=null&&!"".equals(code)){
			return readDao.query(Namespace.SHARE_DETAIL, "getShareDetail", info);
		}else{
			ShareDetailInfo query = getShareDetailTime();
			if(query==null){
				return  null;
			}
			query.setPop(info.getPop());
			query.setPush(info.getPush());
			return readDao.query(Namespace.SHARE_DETAIL, "getShareDetail", query);
		}
	}
	/**
	 * 取得 当前最新数据
	 *
	 * @return
	 * @throws Exception
	 */
	public List<ShareDetailInfo> getShareDetailx(ShareDetailInfo info) throws Exception {
		return readDao.query(Namespace.SHARE_DETAIL, "getShareDetailList", info);
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

	/**
	 * 取得 当前最新数据 汇总
	 *
	 * @return
	 * @throws Exception
	 */
	public ShareDetailInfo getShareDetailIndex() throws Exception {
		ShareDetailInfo info = getShareDetailTime();
		if(info==null){
			return  null;
		}
		return readDao.getOne(Namespace.SHARE_DETAIL, "getShareDetailIndex", info);
	}

	/**
	 * 取得 当前最新数据 汇总
	 *
	 * @return
	 * @throws Exception
	 */
	public ShareDetailInfo getShareDetailTime() throws Exception {
		ShareDetailInfo info = new ShareDetailInfo();
		return readDao.getOne(Namespace.SHARE_DETAIL, "getShareDetailTime", info);
	}

	/**
	 * 取得 历史数据
	 *
	 * @param info 数据
	 * @return
	 * @throws Exception
	 */
	public List<ShareHisInfo> getShareHisInfo(ShareHisInfo info) throws Exception {
		return readDao.query(Namespace.SHARE_DETAIL, "getShareHisInfo", info);
	}
}
