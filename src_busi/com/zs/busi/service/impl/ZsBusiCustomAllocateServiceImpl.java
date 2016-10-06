package com.zs.busi.service.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.busi.dao.ZsBusiCustomAllocateDao;
import com.zs.busi.entity.ZsBusiCustomAllocateEntity;
import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.log.dao.ZsBusiCustomerLogDao;
import com.zs.busi.service.ZsBusiCustomAllocateService;
import com.zs.busi.utils.DefaultResultUtil;
import com.zs.busi.utils.LogUtil;
import com.zs.busi.web.vo.ZsBusiCustomAllocateVo;
import com.zs.common.util.DateUtil;
import com.zs.common.util.ding.DingUtil;
import com.zs.oa.entity.OaUserEntity;
import com.zs.rbac.entity.User;
import com.zs.rbac.utils.RbacUtils;


@Service
public class ZsBusiCustomAllocateServiceImpl implements ZsBusiCustomAllocateService{
	
	Log log=LogUtil.getLog();
	
	@Autowired
	ZsBusiCustomAllocateDao zsInfoDao;
	@Autowired
	ZsBusiCustomerLogDao zsLogDao;

	@Override
	public List<ZsBusiCustomAllocateEntity> getZsOverdueCustInfoDao(ZsBusiCustomAllocateVo vo) {
		return zsInfoDao.getZsOverdueCustInfoDao(vo);
		//return null;
	}

	@Override
	public List<ZsBusiCustomAllocateEntity> getZsCusInvalidDao(ZsBusiCustomAllocateVo vo) {
		return zsInfoDao.getZsCusInvalidDao(vo);
		//return null;
	}

	@Override
	public List<ZsBusiCustomAllocateEntity> getZsCustFollowingDao(ZsBusiCustomAllocateVo vo) {
		
		return zsInfoDao.getZsCustFollowingDao(vo);
	}
	@Override
	public List<ZsBusiCustomAllocateEntity> getZsCustFollowingDao_withoutPage(ZsBusiCustomAllocateVo vo) {
		
		return zsInfoDao.getZsCustFollowingDao_withoutPage(vo);
	}

	@Override
	public List<ZsBusiCustomAllocateVo> getZsCustPublicDao(ZsBusiCustomAllocateVo vo) {
		
		return zsInfoDao.getZsCustPublicDao(vo);
	}

	@Override
	public List<ZsBusiCustomAllocateVo> getZsCustDusBinDao(ZsBusiCustomAllocateVo vo) {
		
		return zsInfoDao.getZsCustDusBinDao(vo);
	}

	@Override
	public int getZsCustCountDao(String desc,ZsBusiCustomAllocateVo vo) {
		vo.setDesc(desc);
		return zsInfoDao.getZsCustCountDao(vo);
		//return 0;
	}
	@Override
	public int getZsCustPubDupCountDao(String desc,ZsBusiCustomAllocateVo vo) {
		
		vo.setDesc(desc);
		return zsInfoDao.getZsCustPubDupCountDao(vo);
	}

	@Override
	public ZsCustomTableVo getCustomInfoByCusId(String cusId) {
		ZsInfoVo vo=new ZsInfoVo();
		vo.setCusId(cusId);
		return zsInfoDao.getCustomInfoByCusId(vo);
	}

	@Override
	public List<ZsBusiCustomLogTable> getCusRecordLogByCusId(ZsBusiCustomLogTable option) {
		//zsLogDao.getCusRecordLogByCusId(cusId);
		return zsLogDao.getCusRecordLogByCusId(option);
		
	}

		@Override
	public List<User> getEmployeeList(String employeeNameOrtel) {
		ZsInfoVo vo=new ZsInfoVo();
		if(DefaultResultUtil.isNum(employeeNameOrtel)){
			vo.setTel(employeeNameOrtel);
		}else if(!DefaultResultUtil.isNum(employeeNameOrtel) && !"".equals(employeeNameOrtel)){
			vo.setEmployeeName(employeeNameOrtel);
		}
		return zsInfoDao.getZsEmployeeList(vo);
	}
		public List<User> getEmployeeList(ZsInfoVo vo) {
			
			return zsInfoDao.getZsEmployeeList(vo);
		}


	@Override
	public int allocateCustomers(String employeeId,String cusId,String proId) {
		log.info(employeeId+" "+cusId);
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		String operator = user.getRealName();
		ZsInfoVo vo=new ZsInfoVo();
		String beforeName="";
		String afterName="";
		if(!"".equals(employeeId)){
			vo.setEmployeeId(employeeId);
		}
		if(!"".equals(cusId)){
			vo.setCusId(cusId);
		}
		ZsInfoVo beforvo=new ZsInfoVo();
		beforvo.setCusId(cusId);
		List<User> beflist=zsInfoDao.getZsEmployeeList(beforvo);
		log.info(beflist.size());
		int k=0;
		if(beflist.size()>0){
			 beforeName=beflist.get(0).getUsername();
		}else if(beflist.size()==0){
			beforeName="�����ͻ�";
		}
		k=zsInfoDao.allocateCustomers(vo);//�ͻ�����
		if(k !=0){
			
			ZsInfoVo aftervo=new ZsInfoVo();
			aftervo.setEmployeeId(employeeId);
			List<User> afterlist=zsInfoDao.getZsEmployeeList(aftervo);
			if(afterlist.size()>0){
				 afterName=afterlist.get(0).getUsername();
			}
			log.info(afterName);
			ZsBusiCustomLogTable table=new ZsBusiCustomLogTable(cusId, "2", DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), operator, "��"+beforeName+"�����"+afterName, "�ֶ�����",proId,"zs",beforeName);
			insertZsCusChangeLog(table);
		}
		return k;
	}

	@Override
	public int reBackCustomers(String cusId,String cstName,String tel,String reason,String fromwhere,String proId,Map<String, OaUserEntity> map) {
		
		ZsInfoVo vo=new ZsInfoVo();
		String operator;
		String beforeName="";
		String content="";
		int k=0;
		if(!"".equals(cusId)){
			vo.setCusId(cusId);
		}
		ZsInfoVo beforvo=new ZsInfoVo();
		beforvo.setCusId(cusId);
		List<User> beflist=zsInfoDao.getZsEmployeeList(beforvo);//���������
		ZsBusiCustomAllocateEntity entity=getUserGuidBycstId(beforvo);
		String userGuid=entity.getUserGuid();
		log.info(userGuid);
		if(beflist.size()>0){//�����еĻ���
			log.info("a ���");
			 beforeName=beflist.get(0).getUsername();
			k=zsInfoDao.reBackCustomers(vo);
		}else if(beflist.size()==0 && "-11".equals(userGuid)){//������Ļ���
			log.info("b ���");
			beforeName="������ͻ�";
			k=zsInfoDao.reBackCustomers(vo);
		}
		int uid=map.get("��־��").uid;
		if(fromwhere.indexOf("�Զ�") != -1) {
			operator = "ϵͳ�Զ�";
			content = reason+",��"+beforeName+"�Զ����ճɹ����ͻ�";
			String str1="ϵͳ�Զ���������"+cstName+",�绰(";
			String str2=tel;//
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
		}else {
			User user = (User)RbacUtils.subject().getSession().getAttribute("user");
			operator = user.getRealName();
			content ="".equals(reason)?"��"+beforeName+"���ճɹ����ͻ�":reason+",��"+beforeName+"���ճɹ����ͻ�";
			try {
				String str0 = operator+"���ֶ���";
				String str1="����:"+cstName+",�绰(";
				String str2=tel;//
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
			ZsBusiCustomLogTable table=new ZsBusiCustomLogTable(cusId, "3", DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), operator, content, fromwhere,proId,"zs",beforeName);
			insertZsCusChangeLog(table);
		}
		return k;
		
	}

	@Override
	public int dusbinCustomers(String employeeId,String cusId,String fromwhere,String proId) {
		ZsInfoVo vo=new ZsInfoVo();
		int k=0;
		if(!"".equals(employeeId)){
			vo.setEmployeeId(employeeId);
			k=zsInfoDao.dusbinCustomers(vo);
		}
		if(!"".equals(cusId)){
			vo.setCusId(cusId);
			String operator;
			String beforeName="";
			String content="";
			String reason="";
			List<User> beflist=zsInfoDao.getZsEmployeeList(vo);//���������
			if(beflist.size()>0){
				 beforeName=beflist.get(0).getUsername();
			}else if(beflist.size()==0){
				beforeName="�����ͻ�";
			}
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
				ZsBusiCustomLogTable table=new ZsBusiCustomLogTable(cusId, "3", DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), operator, content, fromwhere,proId,"zs",beforeName);
				insertZsCusChangeLog(table);
			}
		}
		return k;
	}

	@Override
	public int insertZsCusChangeLog(ZsBusiCustomLogTable vo) {
		return zsLogDao.insertZsCusChangeLog(vo);
	}
	
	@Override
	public int dusbinCustomers(String cusId) {
		ZsInfoVo vo=new ZsInfoVo();
		vo.setCusId(cusId);
		int _res=zsInfoDao.dusbinCustomers(vo);
		return _res;
	}

	@Override
	public List<ZsBusiCustomAllocateEntity> autoRebackOverCustomer(ZsInfoVo option) {
		return zsInfoDao.autoRebackOverCustomer(option);
	}

	@Override
	public List<ZsBusiCustomAllocateEntity> autoRebackInvalidCustomer(ZsInfoVo option) {
		return zsInfoDao.autoRebackInvalidCustomer(option);
	}

	@Override
	public ZsBusiCustomLogTable getCusReBackInfoByCusId(String cusId,String logType) {
		ZsInfoVo vo=new ZsInfoVo();
		vo.setCusId(cusId);
		vo.setLogType(logType);
		return zsLogDao.getCusReBackInfoByCusId(vo);
	}

	@Override
	public int getCusReBackCountByCusId(String cusId,String logType) {
		ZsInfoVo vo=new ZsInfoVo();
		vo.setCusId(cusId);
		vo.setLogType(logType);
		return zsLogDao.getCusReBackCountByCusId(vo);
	}

	@Override
	public ZsBusiCustomAllocateEntity getUserGuidBycstId(ZsInfoVo option) {
		ZsBusiCustomAllocateEntity entity=zsInfoDao.getUserGuidBycstId(option);
		return entity;
	}
	@Override
	public void transTest(){	
		int i=0;
		ZsBusiCustomLogTable table=new ZsBusiCustomLogTable("110", "3", DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), "test", "�������"+i, "�����������","110110110","zs","yzj");
		insertZsCusChangeLog(table);
		log.info("one is ok");
		int k=1/0;
		insertZsCusChangeLog(table);
		log.info("it is ok");
	}

}
