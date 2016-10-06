package com.zs.crm.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zs.busi.entity.CountRoomVo;
import com.zs.busi.entity.CountTelAccessVo;
import com.zs.busi.entity.XsMonthCountInfoVo;
import com.zs.crm.service.XsInfoService;

@Controller
public class XsInfoAction {
	Log log=LogFactory.getLog(XsInfoAction.class);
	
	@Autowired
	XsInfoService service;
	@Autowired
	HttpServletRequest request;
	
	public String getCountTelAccess(HttpSession httpSession){
		
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-01");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today= new Date();
		String startDate=format.format(today);
		//
		//Calendar cal=Calendar.getInstance();
		//int maxday=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-"+maxday);
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String endDate=format2.format(today);
		log.info("DATA "+startDate+" "+endDate);
		try {
			CountTelAccessVo dto=service.getCountTelAccess(startDate, endDate);
			if(dto !=null){
				httpSession.setAttribute("cusAccess", dto.getCusAccess()==null?0:dto.getCusAccess());
				httpSession.setAttribute("cusTel", dto.getCusTel()==null?0:dto.getCusTel());
				httpSession.setAttribute("toInterView", dto.getToInterView()==null?0:dto.getToInterView());
				httpSession.setAttribute("toTel", dto.getToTel()==null?0:dto.getToTel());		
			}
			else{
				log.info("null");
				httpSession.setAttribute("cusAccess", 0);
				httpSession.setAttribute("cusTel", 0);
				httpSession.setAttribute("toInterView", 0);
				httpSession.setAttribute("toTel", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String getCountRoom(HttpSession httpSession){
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-01");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today= new Date();
		String startDate=format.format(today);
		//String startDate="2015-01-01";
		//Calendar cal=Calendar.getInstance();
		//int maxday=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-"+maxday);
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String endDate=format2.format(today);
		
		log.info("DATA "+startDate+" "+endDate);
		try {
			//yu ze 项目id
			String yzproJid="8FFF2136-61EA-E411-BAAF-FCAA145C42F2";
			CountRoomVo dto=service.getCountRoom(startDate, endDate, yzproJid);
			//log.info(dto.getRoomDeal());
			if(dto !=null){
				httpSession.setAttribute("yzroomDeal",dto.getRoomDeal()==null?0:dto.getRoomDeal() );
				httpSession.setAttribute("yzroomCheckOut",dto.getRoomCheckOut()==null?0:dto.getRoomCheckOut() );
				httpSession.setAttribute("yzcarDeal",dto.getCarDeal()==null?0:dto.getCarDeal() );
				httpSession.setAttribute("yzcarCheckOut",dto.getCarCheckOut()==null?0:dto.getCarCheckOut());
				httpSession.setAttribute("yzcellarDeal",dto.getCellarDeal()==null?0:dto.getCellarDeal());
				httpSession.setAttribute("yzcellarCheckOut",dto.getCellarCheckOut()==null?0:dto.getCellarCheckOut());
			}
			else {
				httpSession.setAttribute("yzroomDeal",0 );
				httpSession.setAttribute("yzroomCheckOut",0 );
				httpSession.setAttribute("yzcarDeal",0);
				httpSession.setAttribute("yzcarCheckOut",0);
				httpSession.setAttribute("yzcellarDeal",0);
				httpSession.setAttribute("yzcellarCheckOut",0);
			}
			//shiji佳缘项目id
			String sjproJid="6EE98A03-D6F9-E411-A3DE-44A8420F6F0A";
			CountRoomVo vo=service.getCountRoom(startDate, endDate, sjproJid);
			if(vo !=null){
				httpSession.setAttribute("sjroomDeal",vo.getRoomDeal() );
				httpSession.setAttribute("sjroomCheckOut",vo.getRoomCheckOut() );
				httpSession.setAttribute("sjcarDeal",vo.getCarDeal() );
				httpSession.setAttribute("sjcarCheckOut",vo.getCarCheckOut());
				httpSession.setAttribute("sjcellarDeal",vo.getCellarDeal());
				httpSession.setAttribute("sjcellarCheckOut",vo.getCellarCheckOut());
			}
			else {
				httpSession.setAttribute("sjroomDeal",0);
				httpSession.setAttribute("sjroomCheckOut",0);
				httpSession.setAttribute("sjcarDeal",0);
				httpSession.setAttribute("sjcarCheckOut",0);
				httpSession.setAttribute("sjcellarDeal",0);
				httpSession.setAttribute("sjcellarCheckOut",0);
			}
			System.out.println(httpSession.getAttribute("sjroomDeal"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	public String getMonthCountMoneyNumber() throws Exception{
		List<XsMonthCountInfoVo> list=service.getMonthCountMoneyTnumber();
		Date date= new Date();
		int month=date.getMonth()+1;
		if(list.size()>0){
			XsMonthCountInfoVo vo=list.get(0);
			request.getSession().setAttribute("currentMonth",month);
			request.getSession().setAttribute("totalMoney",vo.getTotalMoney() );
			request.getSession().setAttribute("totalNumber",vo.getTotalNumber() );
		}
		return "success";
	}

	public String createCulstom(){
		log.info("success");
		return "success";
	}
	
	

}
