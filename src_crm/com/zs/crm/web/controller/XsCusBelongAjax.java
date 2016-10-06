package com.zs.crm.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.busi.ajax.ZsCustomBelongingAjaxVo;
import com.zs.busi.entity.ZsDictionaryTableVo;
import com.zs.busi.service.ZsInfoService;
import com.zs.crm.entity.XsCstBelongToAjax;
import com.zs.crm.entity.XsCstInfo;
import com.zs.crm.entity.XsCstSearchOption;
import com.zs.crm.service.XsCstInfoService;
import com.zs.rbac.entity.User;
import com.zs.rbac.utils.RbacUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/mbem/mcrm/house/customer")
public class XsCusBelongAjax {
	
	//private static final String PROJGUID = "8FFF2136-61EA-E411-BAAF-FCAA145C42F2";//御泽嘉园项目id
	private String data;
	private JSONObject result;
	private Log log=LogFactory.getLog(XsCusBelongAjax.class);
	@Autowired
	XsCstInfoService xsCstInfoService;
	@Autowired
	HttpSession httpSession;
	
	
	@RequestMapping(value="/getXsCstBelongTo")
	@ResponseBody
	public XsCstBelongToAjax getXsCstBelongTo(@RequestParam(value="telVal",required=true) String telVal){
		
	    log.info("ajax:"+telVal);
		XsCstSearchOption searchInfo = new XsCstSearchOption();
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		//String employee = "张雅竹";
	    String projGuid = user.getExtInfo("projGuid");
		searchInfo.setStartIndex(0);
		searchInfo.setLength(1);
		//searchInfo.setZygw(employee);
		searchInfo.setMobileTel(telVal);
		searchInfo.setSortWay("1");
		searchInfo.setIsAll("1");
		searchInfo.setProjGUID(projGuid);
	    List<XsCstInfo> belongInfo=xsCstInfoService.getXsCstListByEmployee(searchInfo);
	    XsCstBelongToAjax ajaxVo;
	    List<XsCstBelongToAjax> cstBelong = new ArrayList<XsCstBelongToAjax>();
	    if(belongInfo.size() == 0) {
			log.info("新客户");
			ajaxVo = new XsCstBelongToAjax("0", "/mbem/mcrm/house/customer/addCustom.action?newTel="+telVal);
			cstBelong.add(ajaxVo);
			//httpSession.setAttribute("xsTel", telVal);//2016.3.1
		}
	    else if(belongInfo.size() > 0) {
	    	String zygw = belongInfo.get(0).getXsOpp().getZygw();
			String curUser=user.getUsername();
	    	if(zygw.equals("公共客户")) {
	    		log.info("公共客户");//是否需要区分是否可以直接跟进
	    		String selectName=xsCstInfoService.checkCusBeFollowedByEmployee();
	    		if(selectName.equals("1")){
	    			ajaxVo=new XsCstBelongToAjax("3",belongInfo.get(0).getXsCst().getCstName(), "0", "1", belongInfo.get(0).getXsOpp().getStatus(), belongInfo.get(0).getXsCst().getCreatedOn(), "user_name", belongInfo.get(0).getXsCst().getCstGuid(), belongInfo.get(0).getXsOpp().getOppGuid());
	    		}else {
	    			ajaxVo=new XsCstBelongToAjax("3",belongInfo.get(0).getXsCst().getCstName(), "1", "1");//不可直接跟进
	    		}
				cstBelong.add(ajaxVo);
	    	}
	    	else if(zygw.equals("垃圾箱客户")){
	    		ajaxVo=new XsCstBelongToAjax("4",belongInfo.get(0).getXsCst().getCstName());//不可直接跟进
	    		cstBelong.add(ajaxVo);
	    	}
	    	else if(zygw.equals(curUser)) {//之后用登录的置业顾问替换
	    		log.info("自己的客户");
	    		ajaxVo = new XsCstBelongToAjax("1", "/mbem/mcrm/house/customer/getXsCstAndFollowInfoByCstGuid.action?cstGuid="+belongInfo.get(0).getXsCst().getCstGuid()+"&oppGuid="+belongInfo.get(0).getXsOpp().getOppGuid());
	    		cstBelong.add(ajaxVo);
	    	}
	    	else if(!zygw.equals(curUser) && zygw !=null && !"".equals(zygw)){
				log.info("别人的客户 "+zygw);
				//ajaxVo=new ZsCustomBelongingAjaxVo(cst_type, cst_name, is_show_saler_name);//其它置业顾问的客户，但不显示置业顾问
				ajaxVo=new XsCstBelongToAjax("2", belongInfo.get(0).getXsCst().getCstName(), "1",belongInfo.get(0).getXsOpp().getStatus() , belongInfo.get(0).getXsOpp().getCreatedOn(), zygw,belongInfo.get(0).getEmployee().getMobilePhone()); 
				cstBelong.add(ajaxVo);
			}
	    }
	    log.info(cstBelong.size());
		return cstBelong.get(0);
		
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	

}




