package com.zs.crm.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.busi.utils.LogUtil;
import com.zs.common.util.CommonUtil;
import com.zs.crm.entity.XsSaleStatisticsResultVo;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.service.XsSaleStatisticsService;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.XsTeamGroupService;

@Controller
@RequestMapping(value="/wbem/houses/analysis")
public class XsSaleStatisticsController {
	private Log log=LogUtil.getLog();
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	@Autowired
	XsSaleStatisticsService xsSaleStatisticsService;
	
	@RequiresPermissions(value="page:financialManage:saleStatistics:index")
	@RequestMapping(value="/houseCaseReport")
	public String houseCaseReport(Model model){
		//List<XsTeamGroupEntity> projectList = new ArrayList<XsTeamGroupEntity>();
		XsSaleStatisticsResultVo roomStatisticsInfo = new XsSaleStatisticsResultVo();
		XsSaleStatisticsResultVo carportStatisticsInfo = new XsSaleStatisticsResultVo();
		XsSaleStatisticsResultVo unPaymentInfo = new XsSaleStatisticsResultVo();
		XsSaleStatisticsResultVo unSignUpInfo = new XsSaleStatisticsResultVo();
		XsSaleStatisticsResultVo unLendingInfo = new XsSaleStatisticsResultVo();
		
		/*projectList = xsTeamGroupService.getProjectItem();
		String projGuid = projectList.get(0).getId();*/
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		String amount="";
		log.info("projGuid="+projGuid);
		roomStatisticsInfo = xsSaleStatisticsService.getRoomStatisticsInfo(projGuid, "room");
		if(roomStatisticsInfo == null) {//解决没有数据出现的异常
			roomStatisticsInfo = new XsSaleStatisticsResultVo();
		}
		amount = CommonUtil.formatTosepara(Double.parseDouble(roomStatisticsInfo.getQkAmount()));
		roomStatisticsInfo.setQkAmount(amount);
		carportStatisticsInfo = xsSaleStatisticsService.getRoomStatisticsInfo(projGuid, "carport");
		if(carportStatisticsInfo == null) {//解决没有数据出现的异常
			carportStatisticsInfo = new XsSaleStatisticsResultVo();
		}
		amount = CommonUtil.formatTosepara(Double.parseDouble(carportStatisticsInfo.getTotalAmount()));
		carportStatisticsInfo.setTotalAmount(amount);
		amount = CommonUtil.formatTosepara(Double.parseDouble(carportStatisticsInfo.getQkAmount()));
		carportStatisticsInfo.setQkAmount(amount);
		
		unPaymentInfo = xsSaleStatisticsService.getUnPaymentInfo(projGuid);
		amount = CommonUtil.formatTosepara(Double.parseDouble(unPaymentInfo.getUnPaymentAmount()));
		unPaymentInfo.setUnPaymentAmount(amount);
		unSignUpInfo = xsSaleStatisticsService.getUnSignUpInfo(projGuid);
		amount = CommonUtil.formatTosepara(Double.parseDouble(unSignUpInfo.getUnSignUpAmount()));
		unSignUpInfo.setUnSignUpAmount(amount);
		unLendingInfo = xsSaleStatisticsService.getUnLendingInfo(projGuid,"1");//1表示只查未放款的所有tradeguid
		amount = CommonUtil.formatTosepara(Double.parseDouble(unLendingInfo.getUnLendingAmount()));//转换格式（千位分隔符）
		unLendingInfo.setUnLendingAmount(amount);//
		
		//model.addAttribute("projectList", projectList);
		model.addAttribute("roomStatistics", roomStatisticsInfo);//住宅统计
		model.addAttribute("carportStatistics", carportStatisticsInfo);//车位统计
		model.addAttribute("unPaymentInfo", unPaymentInfo);
		model.addAttribute("unSignUpInfo", unSignUpInfo);
		model.addAttribute("unLendingInfo", unLendingInfo);
		return "/wbem/houses/analysis/houseCaseReport";
	}
}
