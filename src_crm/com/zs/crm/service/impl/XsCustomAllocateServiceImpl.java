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
		k=zsInfoDao.allocateCustomers(vo);//�ͻ�����
		if(k !=0){
			XsCustomersManagerEntity after=getZygwByoppGUID(oppGUID);
			afterName=after.getZygw();
			//������Ϣ
			XsCstSearchOption bean = new XsCstSearchOption();
			bean.setCstGuid(cusId);
			bean.setOppGUID(oppGUID);
			List<XsCstInfo> cstList=xsCstInfoDao.getXsCstInfoByCstGuidDao(bean);
			XsCstInfo cst=cstList.get(0);
			String cstName=cst.getXsCst().getCstName();
			String mobileTel=cst.getXsCst().getMobileTel();
			try {
				String str0 = operator+"�ֶ���";
				String str1=beforeName+"�Ŀͻ���";
				String str2=cstName+",�绰(";
				String str3=mobileTel;//
				String str4=")�����"+afterName;
				log.info(cstName.length());
				log.info(cstName.length()%2);
				if(operator.length()%2==1){//����
					str0 = operator+"�ֶ���";
				}else{//ż��
					str0 = operator+"���ֶ���";
				}
				if(beforeName.length()%2==1){//����
					str1=beforeName+"�Ŀͻ�";
				}else{//ż��
					str1=beforeName+"�Ŀͻ���";
				}
				if(cstName.length()%2==1){//����
					str2=cstName+"���绰(";
				}else{//ż��
					str2=cstName+",�绰(";
				}
				if(afterName.length()%2==1){//����
					str4=")�����"+afterName;
				}else{//ż��
					str4=")�������"+afterName;
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
				//DingUtil.sendTextMessage(str0+str1+str2+str3+str4, "149", "", "37907377");//��ʽ
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//
			ZsBusiCustomLogTable table=new ZsBusiCustomLogTable(cusId, "2", DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), operator, "��"+beforeName+"�����"+afterName, "�ֶ�����",proId,"xs",beforeName);
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
		int uid=map.get("��־��").uid;
		int k=0;
		if(!"�����ͻ�".equals(beforeName)){
			if (!"������ͻ�".equals(beforeName)){
				vo.setOldZygw(beforeName);//����ԭ��ҵ���ʣ�������ͻ��Ƿ����⴦��
				vo.setOldUserGuid(beflist.getUserGuid());
			}
			vo.setOppGUID(oppguid);
			k=zsInfoDao.reBackCustomers(vo);
			if(fromwhere.indexOf("�Զ�") != -1){//���ڻ���Ч�Զ�����
				operator = "ϵͳ�Զ�";
				content = "��"+beforeName+"�Զ����ճɹ����ͻ�";
				//�Զ����յķ��Ͷ����������ѣ�
				String str1="ϵͳ�Զ���������"+cstName+",�绰(";
				String str2=mobileTel;//
				String str3=")��ҵ����Ϊ"+beforeName+"�Ŀͻ����ճɹ����ͻ�";
				String str4="ԭ����:"+reason;
				if(cstName.length()%2==1){//����
					str1="ϵͳ�Զ�������"+cstName+",�绰(";
				}else{//ż��
					str1="ϵͳ�Զ���������"+cstName+",�绰(";
				}
				if(beforeName.length()%2==1){//����
					str3=")��ҵ����Ϊ"+beforeName+"�Ŀͻ����ճɹ����ͻ�";
				}else{//ż��
					str3=")����ҵ����Ϊ"+beforeName+"�Ŀͻ����ճɹ����ͻ�";
				}
				if(fromwhere.length()%2==1){//����
					str4="ԭ����"+fromwhere;
				}else{//ż��
					str4="ԭ���ǣ�"+fromwhere;
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
					//DingUtil.sendTextMessage(str1+str2+str3+str4, "149", "", "37907377");//��ʽ
					log.info("over");
				} catch (Exception e) {
					log.info(e);
				}
	           
			}else {//�ֶ�����
				User user = (User)RbacUtils.subject().getSession().getAttribute("user");
				operator = user.getRealName();
				if("".equals(reason)){	
					content = "��"+beforeName+"���ճɹ����ͻ�";
				}else{
					content = reason+",��"+beforeName+"���ճɹ����ͻ�";
				}
				try {
					String str0 = operator+"���ֶ���";
					String str1="����:"+cstName+",�绰(";
					String str2=mobileTel;//
					String str3=")��ҵ����"+beforeName+"���ճɹ����ͻ�";
					String str4="ԭ����"+reason;
					log.info(cstName.length());
					log.info(cstName.length()%2);
					if(operator.length()%2==1){//����
						 str0 = operator+"�ֶ���";
					}else{//ż��
						 str0 = operator+"���ֶ���";
					}
					if(cstName.length()%2==1){//����
						str1="������"+cstName+",�绰(";
					}else{//ż��
						str1="����:"+cstName+",�绰(";
					}
					if(beforeName.length()%2==1){//����
						str3=")��ҵ���ʣ�"+beforeName+"�Ŀͻ����ճɹ����ͻ�";
					}else{//ż��
						str3=")��ҵ����"+beforeName+"�Ŀͻ����ճɹ����ͻ�";
					}
					if(reason.contains("��")){
						reason=reason.substring(0, reason.length()-1).trim();
					}
					if(reason.length()%2==1){//����
						str4="ԭ����"+reason;
					}else{//ż��
						str4="ԭ���ǣ�"+reason;
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
					//DingUtil.sendTextMessage(str0+str1+str2+str3+str4, "149", "", "37907377");//��ʽ
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} 
			}
			if(k !=0){
				ZsBusiCustomLogTable table=new ZsBusiCustomLogTable(cusId, "3", DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), operator, content, fromwhere,proId,"xs",beforeName);
				insertZsCusChangeLog(table);
			}
		}else{
			log.info("�����ͻ���������ͻ����ܻ���");
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
		/*List<XsCustomersManagerEntity> beflist=zsInfoDao.getZsEmployeeList(vo);//���������
		if(beflist.size()>0){
			 beforeName=beflist.get(0).getUserName();
		}else if(beflist.size()==0){
			beforeName="�����ͻ�";
		}*/
		beforeName="�����ͻ�";//ֻ�й����ͻ������Ƶ�������
		k=zsInfoDao.dusbinCustomers(vo);
		if(fromwhere.indexOf("�Զ�") != -1) {
			operator = "ϵͳ�Զ�";
			content = "�ӹ����ͻ��Ƶ�������";
		}else {
			User user = (User)RbacUtils.subject().getSession().getAttribute("user");
			operator = user.getRealName();
			content = "�ӹ����ͻ��Ƶ�������";
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
			ZsBusiCustomLogTable table=new ZsBusiCustomLogTable(xsCustomersManagerEntity.getCstGuid(), "4", DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), "�Զ��޸�", "��"+xsCustomersManagerEntity.getStatus()+"�޸�Ϊ"+xsCustomersManagerEntity.getRoomStatus(), "�Զ��޸�","8FFF2136-61EA-E411-BAAF-FCAA145C42F2","xs","�Զ��޸�");
			insertZsCusChangeLog(table);
		}
		int i=zsInfoDao.autoUpdateOppStatus(vo);
		log.info("auto update oppStasut ok and i is: "+i);
		return i;
	}
}
