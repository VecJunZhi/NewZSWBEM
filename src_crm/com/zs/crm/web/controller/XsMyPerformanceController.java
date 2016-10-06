package com.zs.crm.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.busi.utils.LogUtil;
import com.zs.common.util.DateUtil;
import com.zs.crm.entity.XsPerformanceEntity;
import com.zs.crm.service.XsCustomAllocateService;
import com.zs.crm.service.XsPerformanceService;
import com.zs.crm.web.vo.XsPerformanceVo;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;
import com.zs.rbac.utils.RbacUtils;

/**
 * �ƿ���ҵ���ʸ���ҵ��
 * @author jiarui
 */
@Controller
@RequestMapping(value="/mbem/mcrm/house/myPerformance")
public class XsMyPerformanceController {
	
		static Log log=LogUtil.getLog();
		@Autowired 
		XsPerformanceService xsPerformanceService;
		@Autowired 
		XsCustomAllocateService xsBusiCustomAllocateService;
		@Autowired
		HttpSession httpSession;
		@Autowired
		XsInfoAction xsInfoAction;

	/**
	 * ����ҵ����ҳ
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:xs:myPerformance:myRank")	
	@RequestMapping(value="/myRank")  
	public String myRank(Model model){
		Session Session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)Session.getAttribute("user");
		//log.info(user==null);
		//String ywy=user.getRealName();2016-7-27 lu*/
		//User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		String ywy = user.getRealName();
		String userGuid = user.getExtInfo("xs_userGuid");
		String startTime=DateUtil.getCurrentTime("yyyy-MM")+"-01 00:00:00";//��ǰ����ʼ����
		String endTime=DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");//��ǰʱ��
		int season=XsPerformanceController.getSeasonByMonth(Integer.parseInt(DateUtil.getCurrentTime("MM")));
		int year=Integer.parseInt(DateUtil.getCurrentTime("yyyy"));
		XsPerformanceVo seasonStartEnd=XsPerformanceController.getSeasonStartEnd(year, season);
		//��ø���Ԥ�����������¶� /����/���ͳ����Ϣ��
		XsPerformanceEntity budgetMonth=xsPerformanceService.getMy_BudgetedPerformanceByDateTime(startTime, endTime,userGuid,projGuid);
		if(budgetMonth==null){
			budgetMonth=new XsPerformanceEntity(0, "0", "0", "0");
		}
		XsPerformanceEntity budgetSeason=xsPerformanceService.getMy_BudgetedPerformanceByDateTime(seasonStartEnd.getStartTime(), seasonStartEnd.getEndTime(),userGuid,projGuid);
		if(budgetSeason==null){
			budgetSeason=new XsPerformanceEntity(0, "0", "0", "0");
		}
		XsPerformanceEntity budgetYear=xsPerformanceService.getMy_BudgetedPerformanceByDateTime(year+"-01-01 00:00:00",endTime,userGuid,projGuid);
		if(budgetYear==null){
			budgetYear=new XsPerformanceEntity(0, "0", "0", "0");
		}
		httpSession.setAttribute("my_budgetMonth", budgetMonth);
		httpSession.setAttribute("my_budgetSeason", budgetSeason);
		httpSession.setAttribute("my_budgetYear", budgetYear);
		
		//�����¶�ҵ�����
		String orderWord="salesAmount";//�������۶�����
		List<XsPerformanceEntity> list_ywy_bymoney=xsPerformanceService.getMy_YWYMonthPerformanceRanking(startTime, endTime,orderWord,ywy,projGuid);
		log.info(list_ywy_bymoney.size());
		httpSession.setAttribute("My_YWYMonthPerformanceRanking", list_ywy_bymoney);
		//�Ŷ����� ---��װ�������
		orderWord="salesAmount";//�������۶�����
		List<XsPerformanceEntity> list_bymoney=xsPerformanceService.getTeamMonthPerformanceRanking(startTime, endTime,orderWord,projGuid);
		httpSession.setAttribute("My_TeamMonthPerformanceRankingByMoney", list_bymoney);
		//�Ŷ�����---������������
		orderWord="ts";//������������
		List<XsPerformanceEntity> list_byts=xsPerformanceService.getTeamMonthPerformanceRanking(startTime, endTime,orderWord,projGuid);
		httpSession.setAttribute("My_TeamMonthPerformanceRankingByTs", list_byts);
		return "/mbem/mcrm/house/myPerformance/myRank";
	}
	
	/**
	 * ����Ԥ��༭
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/myTarget")
	public String myTarget(Model model){
		return "/mbem/mcrm/house/myPerformance/myTarget";
	}
	
	/**
	 * ���˳ɽ���ϸ
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/detailDeal")
	public String detailDeal(Model model){
		return "/mbem/mcrm/house/myPerformance/detailDeal";
	}
	
}
