package com.zs.crm.service.impl;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.crm.entity.XsCstInfo;
import com.zs.crm.entity.XsCstSearchOption;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.log.dao.ZsBusiCustomerLogDao;
import com.zs.busi.utils.DefaultResultUtil;
import com.zs.busi.utils.LogUtil;
import com.zs.crm.web.vo.XsCustomersManagerVo;
import com.zs.common.util.DateUtil;
import com.zs.common.util.ding.DingUtil;
import com.zs.crm.dao.XsCstInfoDao;
import com.zs.crm.dao.XsCustomAllocateDao;
import com.zs.crm.dao.XsfollowInfoDao;
import com.zs.crm.service.XsCustomAllocateService;
import com.zs.oa.entity.OaUserEntity;
import com.zs.rbac.entity.User;
import com.zs.rbac.utils.RbacUtils;


@Service
public class XsCustomAllocateServiceImpl implements XsCustomAllocateService{
	
	Log log=LogUtil.getLog();
	public static final String AGGENT="37907377";
	@Autowired
	XsCustomAllocateDao zsInfoDao;
	@Autowired
	ZsBusiCustomerLogDao zsLogDao;
	@Autowired
	XsCstInfoDao xsCstInfoDao;
	@Autowired
	HttpSession session;
	@Override
	public List<XsCustomersManagerEntity> getZsOverdueCustInfoDao(XsCustomersManagerVo vo) {
		return zsInfoDao.getZsOverdueCustInfoDao(vo);
		//return null;
	}

	@Override
	public List<XsCustomersManagerEntity> getZsCusInvalidDao(XsCustomersManagerVo vo) {
		return zsInfoDao.getZsCusInvalidDao(vo);
		//return null;
	}

	@Override
	public List<XsCustomersManagerEntity> getZsCustFollowingDao(XsCustomersManagerVo vo) {
		
		return zsInfoDao.getZsCustFollowingDao(vo);
	}

	@Override
	public List<XsCustomersManagerEntity> getZsCustPublicDao(XsCustomersManagerVo vo) {
		
		return zsInfoDao.getZsCustPublicDao(vo);
	}

	@Override
	public List<XsCustomersManagerEntity> getZsCustDusBinDao(XsCustomersManagerVo vo) {
		
		return zsInfoDao.getZsCustDusBinDao(vo);
	}

	@Override
	public int getZsCustCountDao(String desc,XsCustomersManagerVo vo) {
		vo.setDesc(desc);
		return zsInfoDao.getZsCustCountDao(vo);
		//return 0;
	}
	@Override
	public int getZsCustPubDupCountDao(String desc,XsCustomersManagerVo vo) {
		
		vo.setDesc(desc);
		return zsInfoDao.getZsCustPubDupCountDao(vo);
	}

	@Override
	public XsCustomersManagerEntity getCustomInfoByCusId(String cusId) {
		XsCustomersManagerVo vo=new XsCustomersManagerVo();
		vo.setCstGuid(cusId);
		return zsInfoDao.getCustomInfoByCusId(vo);
	}

	@Override
	public List<ZsBusiCustomLogTable> getCusRecordLogByCusId(ZsBusiCustomLogTable vo) {
		//zsLogDao.getCusRecordLogByCusId(cusId);
		
		return zsLogDao.getCusRecordLogByCusId(vo);
		
	}

	@Override
	public List<XsCustomersManagerEntity> getEmployeeList(String employeeNameOrtel) {
		//zsInfoDao.getEmployeeList(employeeName);
		XsCustomersManagerVo vo=new XsCustomersManagerVo();
		if(DefaultResultUtil.isNum(employeeNameOrtel)){
			vo.setMobileTel(employeeNameOrtel);
		}else if(!DefaultResultUtil.isNum(employeeNameOrtel) && !"".equals(employeeNameOrtel)){
			vo.setUserName(employeeNameOrtel);
		}
		return zsInfoDao.getZsEmployeeList(vo);
	}
	@Override
	public XsCustomersManagerEntity getZygwByoppGUID(String  oppGUID) {
		// TODO Auto-generated method stub
		XsCustomersManagerVo vo=new XsCustomersManagerVo();
		vo.setOppGUID(oppGUID);
		XsCustomersManagerEntity entity=zsInfoDao.getZygwByoppGUID(vo);
		return entity;
	}

	@Override
	public int allocateCustomers(String userGuid2,String employeeId,String cusId,String proId,String oppGUID) {
		XsCustomersManagerVo vo=new XsCustomersManagerVo();
		String beforeName="";
		String afterName="";
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		String operator = user.getRealName();
		vo.setBuGUID("42A0C9C9-51EA-E411-BAAF-FCAA145C42F2");
		vo.setProjGUID(proId);
		vo.setUserGUID2(userGuid2);
		vo.setModifyby(operator);
		vo.setOppGUID(oppGUID);
		if(!"".equals(employeeId)){
			vo.setUserGuid(employeeId);
		}
		if(!"".equals(cusId)){
			vo.setCstGuid(cusId);
		}
		XsCustomersManagerEntity beflist=getZygwByoppGUID(oppGUID);
		beforeName=beflist.getZygw();
		int k=0;
		k=zsInfoDao.allocateCustomers(vo);//客户分配
		if(k !=0){
			XsCustomersManagerEntity after=getZygwByoppGUID(oppGUID);
			afterName=after.getZygw();
			//发送消息
			XsCstSearchOption bean = new XsCstSearchOption();
			bean.setCstGuid(cusId);
			bean.setOppGUID(oppGUID);
			List<XsCstInfo> cstList=xsCstInfoDao.getXsCstInfoByCstGuidDao(bean);
			XsCstInfo cst=cstList.get(0);
			String cstName=cst.getXsCst().getCstName();
			String mobileTel=cst.getXsCst().getMobileTel();
			try {
				String str0 = operator+"手动将";
				String str1=beforeName+"的客户：";
				String str2=cstName+",电话(";
				String str3=mobileTel;//
				String str4=")分配给"+afterName;
				log.info(cstName.length());
				log.info(cstName.length()%2);
				if(operator.length()%2==1){//奇数
					str0 = operator+"手动将";
				}else{//偶数
					str0 = operator+"：手动将";
				}
				if(beforeName.length()%2==1){//奇数
					str1=beforeName+"的客户";
				}else{//偶数
					str1=beforeName+"的客户：";
				}
				if(cstName.length()%2==1){//奇数
					str2=cstName+"，电话(";
				}else{//偶数
					str2=cstName+",电话(";
				}
				if(afterName.length()%2==1){//奇数
					str4=")分配给"+afterName;
				}else{//偶数
					str4=")分配给："+afterName;
				}
				byte[] bytes0 = str0.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
	            str0 = new String(bytes0, "GBK");//to GBK string  
	            byte[] bytes1 = str1.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
	            str1 = new String(bytes1, "GBK");//to GBK string 
	            byte[] bytes2 = str2.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
	            str2 = new String(bytes2, "GBK");//to GBK string 
	            byte[] bytes3 = str3.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
	            str3 = new String(bytes3, "GBK");//to GBK string 
	            byte[] bytes4 = str4.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
	            str4 = new String(bytes4, "GBK");
				//DingUtil.sendTextMessage(str0+str1+str2+str3+str4, "149", "", "37907377");//正式
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//
			ZsBusiCustomLogTable table=new ZsBusiCustomLogTable(cusId, "2", DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), operator, "从"+beforeName+"分配给"+afterName, "手动分配",proId,"xs",beforeName);
			insertZsCusChangeLog(table);
		}
		return k;
	}

	@Override
	public int reBackCustomers(String cusId,String cstName,String mobileTel,String reason,String fromwhere,String proId,String oppguid,Map<String, OaUserEntity> map) {
		XsCustomersManagerVo vo=new XsCustomersManagerVo();
		
		String operator;
		String content;
		String beforeName;
		if(!"".equals(cusId)){
			vo.setCstGuid(cusId);
		}
		XsCustomersManagerEntity beflist=getZygwByoppGUID(oppguid);
		log.info("bfflist size : "+beflist.getZygw());
		beforeName=beflist.getZygw();
		int uid=map.get("闫志军").uid;
		int k=0;
		if(!"公共客户".equals(beforeName)){
			if (!"垃圾箱客户".equals(beforeName)){
				vo.setOldZygw(beforeName);//设置原置业顾问，垃圾箱客户是否特殊处理
				vo.setOldUserGuid(beflist.getUserGuid());
			}
			vo.setOppGUID(oppguid);
			k=zsInfoDao.reBackCustomers(vo);
			if(fromwhere.indexOf("自动") != -1){//逾期或无效自动回收
				operator = "系统自动";
				content = "从"+beforeName+"自动回收成公共客户";
				//自动回收的发送钉钉回收提醒：
				String str1="系统自动将姓名："+cstName+",电话(";
				String str2=mobileTel;//
				String str3=")置业顾问为"+beforeName+"的客户回收成公共客户";
				String str4="原因是:"+reason;
				if(cstName.length()%2==1){//奇数
					str1="系统自动将姓名"+cstName+",电话(";
				}else{//偶数
					str1="系统自动将姓名："+cstName+",电话(";
				}
				if(beforeName.length()%2==1){//奇数
					str3=")置业顾问为"+beforeName+"的客户回收成公共客户";
				}else{//偶数
					str3=")。置业顾问为"+beforeName+"的客户回收成公共客户";
				}
				if(fromwhere.length()%2==1){//奇数
					str4="原因是"+fromwhere;
				}else{//偶数
					str4="原因是："+fromwhere;
				}
				try{
					byte[] bytes1 = str1.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
		            str1 = new String(bytes1, "GBK");//to GBK string 
		            byte[] bytes2 = str2.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
		            str2 = new String(bytes2, "GBK");//to GBK string 
		            byte[] bytes3 = str3.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
		            str3 = new String(bytes3, "GBK");//to GBK string 
		            byte[] bytes4 = str4.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
		            str4 = new String(bytes4, "GBK");
					//DingUtil.sendTextMessage(str1+str2+str3+str4, "149", "", "37907377");//正式
					log.info("over");
				} catch (Exception e) {
					log.info(e);
				}
	           
			}else {//手动回收
				User user = (User)RbacUtils.subject().getSession().getAttribute("user");
				operator = user.getRealName();
				if("".equals(reason)){	
					content = "从"+beforeName+"回收成公共客户";
				}else{
					content = reason+",从"+beforeName+"回收成公共客户";
				}
				try {
					String str0 = operator+"：手动将";
					String str1="姓名:"+cstName+",电话(";
					String str2=mobileTel;//
					String str3=")置业顾问"+beforeName+"回收成公共客户";
					String str4="原因是"+reason;
					log.info(cstName.length());
					log.info(cstName.length()%2);
					if(operator.length()%2==1){//奇数
						 str0 = operator+"手动将";
					}else{//偶数
						 str0 = operator+"：手动将";
					}
					if(cstName.length()%2==1){//奇数
						str1="姓名："+cstName+",电话(";
					}else{//偶数
						str1="姓名:"+cstName+",电话(";
					}
					if(beforeName.length()%2==1){//奇数
						str3=")置业顾问："+beforeName+"的客户回收成公共客户";
					}else{//偶数
						str3=")置业顾问"+beforeName+"的客户回收成公共客户";
					}
					if(reason.contains("。")){
						reason=reason.substring(0, reason.length()-1).trim();
					}
					if(reason.length()%2==1){//奇数
						str4="原因是"+reason;
					}else{//偶数
						str4="原因是："+reason;
					}
					byte[] bytes0 = str0.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
		            str0 = new String(bytes0, "GBK");//to GBK string  
		            byte[] bytes1 = str1.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
		            str1 = new String(bytes1, "GBK");//to GBK string 
		            byte[] bytes2 = str2.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
		            str2 = new String(bytes2, "GBK");//to GBK string 
		            byte[] bytes3 = str3.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
		            str3 = new String(bytes3, "GBK");//to GBK string 
		            byte[] bytes4 = str4.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
		            str4 = new String(bytes4, "GBK");
					//DingUtil.sendTextMessage(str0+str1+str2+str3+str4, "149", "", "37907377");//正式
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} 
			}
			if(k !=0){
				ZsBusiCustomLogTable table=new ZsBusiCustomLogTable(cusId, "3", DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), operator, content, fromwhere,proId,"xs",beforeName);
				insertZsCusChangeLog(table);
			}
		}else{
			log.info("公共客户和垃圾箱客户不能回收");
		}
		
		return k;
		
	}

	@Override
	public int dusbinCustomers(String employeeId,String cusId) {
		XsCustomersManagerVo vo=new XsCustomersManagerVo();
		if(!"".equals(employeeId)){
			vo.setUserGuid(employeeId);
		}
		if(!"".equals(cusId)){
			vo.setCstGuid(cusId);
		}
		int k=zsInfoDao.dusbinCustomers(vo);
		return k;
		
	}

	@Override
	public int insertZsCusChangeLog(ZsBusiCustomLogTable vo) {
		return zsLogDao.insertZsCusChangeLog(vo);
	}
	
	@Override
	public int dusbinCustomers(String cusId,String proId,String fromwhere,String oppguid) {
		XsCustomersManagerVo vo=new XsCustomersManagerVo();
		vo.setCstGuid(cusId);
		vo.setProjGUID(proId);
		vo.setOppGUID(oppguid);
		String beforeName="";
		int k=0;
		String operator;
		String content="";
		int _res=zsInfoDao.dusbinCustomers(vo);
		/*List<XsCustomersManagerEntity> beflist=zsInfoDao.getZsEmployeeList(vo);//从哪里回收
		if(beflist.size()>0){
			 beforeName=beflist.get(0).getUserName();
		}else if(beflist.size()==0){
			beforeName="公共客户";
		}*/
		beforeName="公共客户";//只有公共客户可以移到垃圾箱
		k=zsInfoDao.dusbinCustomers(vo);
		if(fromwhere.indexOf("自动") != -1) {
			operator = "系统自动";
			content = "从公共客户移到垃圾箱";
		}else {
			User user = (User)RbacUtils.subject().getSession().getAttribute("user");
			operator = user.getRealName();
			content = "从公共客户移到垃圾箱";
		}
		if(k !=0){
			ZsBusiCustomLogTable table=new ZsBusiCustomLogTable(cusId, "3", DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), operator, content,fromwhere,proId,"xs",beforeName);
			insertZsCusChangeLog(table);
		}
		return _res;
	}

	@Override
	public List<XsCustomersManagerEntity> autoRebackOverCustomer(XsCustomersManagerVo option) {
		log.info("efe");
		return zsInfoDao.autoRebackOverCustomer(option);
	}

	@Override
	public List<XsCustomersManagerEntity> autoRebackInvalidCustomer(XsCustomersManagerVo option) {
		log.info("efef" +option.getProjGUID());
		return zsInfoDao.autoRebackInvalidCustomer(option);
	}

	@Override
	public List<XsCustomersManagerEntity> getZsOverdueCustCountByYwy() {
		// TODO Auto-generated method stub
		return zsInfoDao.getZsOverdueCustCountByYwy();
	}

	@Override
	public List<XsCustomersManagerEntity> getZsCusInvalidCustCountByYwy() {
		// TODO Auto-generated method stub
		return zsInfoDao.getZsCusInvalidCustCountByYwy();
	}

	@Override
	public List<XsCustomersManagerEntity> getZsCustFollowingCustCountByYwy() {
		// TODO Auto-generated method stub
		return zsInfoDao.getZsCustFollowingCustCountByYwy();
	}

	@Override
	public int updateXsBasicInfo(XsCustomersManagerVo vo) {
		// TODO Auto-generated method stub
		
		return zsInfoDao.updateXsBasicInfo(vo);
	}

	@Override
	public int getCusReBackCountByCusId(String cusId, String logType) {
		// TODO Auto-generated method stub
		ZsInfoVo vo=new ZsInfoVo();
		vo.setCusId(cusId);
		vo.setLogType(logType);
		return zsLogDao.getCusReBackCountByCusId(vo);
	}

	@Override
	public ZsBusiCustomLogTable getCusReBackInfoByCusId(String cusId,String logType) {
		// TODO Auto-generated method stub
		ZsInfoVo vo=new ZsInfoVo();
		vo.setCusId(cusId);
		vo.setLogType(logType);
		return zsLogDao.getCusReBackInfoByCusId(vo);
	}

	@Override
	public int autoUpdateOppStatus(XsCustomersManagerVo vo) {
		List<XsCustomersManagerEntity> list=zsInfoDao.selectAutoUpdateOppStatus(vo);
		for (XsCustomersManagerEntity xsCustomersManagerEntity : list) {
			ZsBusiCustomLogTable table=new ZsBusiCustomLogTable(xsCustomersManagerEntity.getCstGuid(), "4", DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), "自动修改", "从"+xsCustomersManagerEntity.getStatus()+"修改为"+xsCustomersManagerEntity.getRoomStatus(), "自动修改","8FFF2136-61EA-E411-BAAF-FCAA145C42F2","xs","自动修改");
			insertZsCusChangeLog(table);
		}
		int i=zsInfoDao.autoUpdateOppStatus(vo);
		log.info("auto update oppStasut ok and i is: "+i);
		return i;
	}
}
