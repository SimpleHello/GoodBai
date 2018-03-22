package com.good.server.controller.share;

import com.good.server.base.JsonResult;
import com.good.server.entity.share.ShareDetailInfo;
import com.good.server.entity.share.ShareHisInfo;
import com.good.server.entity.share.ShareReportInfo;
import com.good.server.service.share.ShareService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/share")
public class ShareController {

	@Resource(name = "shareService")
	private ShareService shareService;

	@RequestMapping("/getDetailIndex.do")
	public @ResponseBody JsonResult  getDetailIndex() throws Exception {
		try{
			ShareDetailInfo info = shareService.getShareDetailIndex();
			return new JsonResult(info);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
		}
	}

	@RequestMapping("/getDetail.do")
	public @ResponseBody JsonResult  getDetail(@RequestBody ShareDetailInfo info) throws Exception {
		try{
			List<ShareDetailInfo> list = shareService.getShareDetail(info);
			return new JsonResult(list);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
		}
	}

	@RequestMapping("/getDetailList.do")
	public @ResponseBody JsonResult  getDetailList(@RequestBody ShareDetailInfo info) throws Exception {
		try{
			List<ShareDetailInfo> list = shareService.getShareDetailx(info);
			return new JsonResult(list);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
		}
	}

	@RequestMapping("/getShareReport.do")
	public @ResponseBody JsonResult  getShareReportInfo(@RequestBody ShareReportInfo info) throws Exception {
		try{
			List<ShareReportInfo>  list = shareService.getShareReportInfo(info.getNum());
			return new JsonResult(list);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
		}
	}

	@RequestMapping("/getShareHis.do")
	public @ResponseBody JsonResult  getShareHis(@RequestBody ShareHisInfo info) throws Exception {
		try{
			List<ShareHisInfo>  list = shareService.getShareHisInfo(info);
			return new JsonResult(list);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
		}
	}
}
