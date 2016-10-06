package com.zs.busi.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.busi.ajax.ZsCustomBelongingAjaxVo;
import com.zs.busi.dao.ZsInfoDao;
import com.zs.busi.entity.ZsCustomFollowRecordTableVo;
import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsDictionaryTableVo;
import com.zs.busi.entity.ZsEmployeeTableVo;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.entity.ZsIntentionTableVo;
import com.zs.busi.service.ZsInfoService;
import com.zs.busi.utils.LogUtil;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;

@Service
public class ZsInfoServiceImpl implements ZsInfoService   {

	Log log=LogUtil.getLog();
	String cusId=null;
	@Autowired
	ZsInfoDao zsInfoDao;
	@Autowired
	HttpSession httpSession;
	@Autowired
	HttpServletRequest httpServletRequest;

	@Override
	public String insertZsBasicInfoDao(ZsInfoVo bean) {
		int _int=zsInfoDao.insertZsBasicInfoDao(bean);
		log.info("id1 "+_int);
		//����ͨ�����ַ�ʽ�õ�id��
		String id=bean.getId();
		log.info("id2 "+id);
		if(id!=null && !"".equals(id)){
			ZsInfoVo vo=new ZsInfoVo();
			vo.setId(id);
			List<ZsCustomTableVo> list=zsInfoDao.getZsBasicInfoDao(vo);//���ݿͻ�id��ÿͻ�����ϸ���ϣ�������Ҫ��ȡ�ͻ�id--cusId.
			if(list.size()>0){
				String cusId=list.get(0).getCusId();
				User user=(User) RBACSubject.getSecurityUtils().getSession().getAttribute("user");
				
				String employeeId=user.getUserID()+"";
				String projGuid = user.getExtInfo("projGuid");//"8FFF2136-61EA-E411-BAAF-FCAA145C42F2";//�����԰��Ŀid
				if(employeeId != null && !"".equals(employeeId)){
					String res=insertIntentionCondition(cusId, employeeId, bean.getIntentionCreateDate(),bean.getIntentionModifyDate(),projGuid);//�󶨿ͻ�����ҵ���ʵĶ�Ӧ��ϵ
					if("succ".equals(res)){
						if(!"".equals(bean.getFollowWay()) || !"".equals(bean.getFollowInfo())){
							String resu=insertZsNewCusFollowInfoDao(cusId,employeeId,bean.getFollowDate(), bean.getFollowWay(), bean.getFollowInfo());//�����ͻ���һ��������¼
							if("succ".equals(resu)){
								ZsInfoVo invo=new ZsInfoVo();
								getZsBasicInfoByEmployee(invo);//��������б�
							}
						}
					}
				}
			}
		}
		return "success";
	}
	@Override
	public String insertZsBasicInfoDao(ZsInfoVo bean,String employeeId) {
		int _int=zsInfoDao.insertZsBasicInfoDao(bean);
		log.info("id1 "+_int);
		//����ͨ�����ַ�ʽ�õ�id��
		String id=bean.getId();
		log.info("id2 "+id);
		if(id!=null && !"".equals(id)){
			ZsInfoVo vo=new ZsInfoVo();
			vo.setId(id);
			List<ZsCustomTableVo> list=zsInfoDao.getZsBasicInfoDao(vo);//���ݿͻ�id��ÿͻ�����ϸ���ϣ�������Ҫ��ȡ�ͻ�id--cusId.
			if(list.size()>0){
				String cusId=list.get(0).getCusId();
				/*User user=(User) RBACSubject.getSecurityUtils().getSession().getAttribute("user");
				String employeeId=user.getUserID()+"";//"20";//������
*/	
				log.info("uid "+employeeId);
				//String progGuid = "8FFF2136-61EA-E411-BAAF-FCAA145C42F2";//�����԰��Ŀid
				if(employeeId != null && !"".equals(employeeId)){
					String res=insertIntentionCondition(cusId, employeeId, bean.getIntentionCreateDate(),bean.getIntentionModifyDate(),bean.getProgGuid());//�󶨿ͻ�����ҵ���ʵĶ�Ӧ��ϵ
					if("succ".equals(res)){
						if(!"".equals(bean.getFollowWay()) || !"".equals(bean.getFollowInfo())){
							String resu=insertZsNewCusFollowInfoDao(cusId,employeeId,bean.getFollowDate(), bean.getFollowWay(), bean.getFollowInfo());//�����ͻ���һ��������¼
							if("succ".equals(resu)){
								ZsInfoVo invo=new ZsInfoVo();
								getZsBasicInfoByEmployee(invo);//��������б�
							}
						}
					}
				}
			}
		}
		return "success";
	}

	@Override
	public String updateZsBasicInfoDao(ZsInfoVo bean) {
		cusId=(String) httpSession.getAttribute("currentCustomID");
		int k=0;
		if(cusId !=null && !"".equals(cusId)){
			bean.setCusId(cusId);
			 k=zsInfoDao.updateZsBasicInfoDao(bean);
			getZsFollowInfoByCustomID();
		}else{
			log.info("canot get cusid");
		}
		return k+"";//�ͻ��б�	
	}
	public String updateZsBasicInfo(ZsInfoVo bean) {
		
		int k=0;
		k=zsInfoDao.updateZsBasicInfoDao(bean);
		
		return k+"";//�ͻ��б�
	}

	@Override
	public String insertZsFollowInfoDao(ZsInfoVo bean) {
		zsInfoDao.insertZsFollowInfoDao(bean);
		return null;
	}

	@Override
	public List<ZsCustomTableVo> getZsBasicInfoDao(ZsInfoVo bean) {
		List<ZsCustomTableVo> list=zsInfoDao.getZsBasicInfoDao(bean);
		System.out.println("lj"+list.size());
		for (ZsCustomTableVo zsInfoVo : list) {
			System.out.println(zsInfoVo.getId()+" "+zsInfoVo.getAddr());
		}
		return list;
		
	}

	@Override
	public List<ZsCustomFollowRecordTableVo> getZsFollowInfoDao(ZsInfoVo bean) {
		List<ZsCustomFollowRecordTableVo> list=zsInfoDao.getZsFollowInfoDao(bean);
		System.out.println("lj"+list.size());
		for (ZsCustomFollowRecordTableVo zsInfoVo : list) {
			System.out.println(zsInfoVo.getId()+" ");
		}
		return list;
		
	}

	@Override
	public String getCusInfoCompleteDegree(ZsInfoVo bean) {
		return null;
	}

	@Override
	public String getCusFollowStatus(ZsInfoVo bean) {
		return null;
	}

	@Override
	public List<ZsInfoVo> getFollowRecall(ZsInfoVo bean) {
		return null;
	}

	@Override
	public  List<ZsEmployeeTableVo> getZsEmployeeList(ZsInfoVo bean) {
		List<ZsEmployeeTableVo> list=zsInfoDao.getZsEmployeeList(bean);
		System.out.println("lj"+list.size());
		for (ZsEmployeeTableVo zsInfoVo : list) {
			System.out.println(zsInfoVo.getId()+" ");
		}
		return list ;
		
	}

	@Override
	public String insertZsEmployee(ZsInfoVo bean) {
		String id=zsInfoDao.insertZsEmployee(bean);
		//���Եõ�id��
		id=bean.getId();
		System.out.println("id "+id);
		return "success";
	}

	@Override
	public String updateZsEmployee(ZsInfoVo bean) {
		String id=zsInfoDao.updateZsEmployee(bean);
		id=bean.getId();
		System.out.println("id "+id);
		return "success";
	}
	public String getCustomId(ZsInfoVo bean){
		String cusId=bean.getCusId();
		System.out.println("idid get the id  "+cusId);
		return cusId;
		
	}
	
	public Map<String,Object> getDroplist(String name,int i) {
		List<ZsDictionaryTableVo> list = zsInfoDao.getDroplist(name);
		Map<String,Object> map = new HashMap<String,Object>();
		if(name.equals("�����Ʒ"))
			name = "��������";
		String select = list.get(0).getProperty();
		if(select.equals("0")) {
			select = "radio";
			map.put("name", name);
		}
		else if(select.equals("1")) {
			select = "checkbox";
			map.put("name", name+"(�ɶ�ѡ)");
		}	
		map.put("select",select);
		map.put("value", list);
		map.put("href", "select"+i);
		map.put("id","idselect"+i);
		return map;
	}
	
	
	/**
	 * ������е��������ѡ��ֵ
	 */
	@Override
	public List<Object> getAllDroplist(String addOrEdit) {
		List<Object> droplist = new ArrayList<Object>();
		Map<String,Object> age = getDroplist("�����",1);
		Map<String,Object> cognitive = getDroplist("��֪;��",2);
		Map<String,Object> use = getDroplist("������;",3);
		Map<String,Object> area = getDroplist("�������",4);
		Map<String,Object> factor = getDroplist("��ע����",5);
		Map<String,Object> workArea = getDroplist("��������",6);
		Map<String,Object> liveArea = getDroplist("��ס����",7);
		Map<String,Object> workType = getDroplist("������ҵ",8);
		Map<String,Object> competitor = getDroplist("��������",9);
		Map<String,Object> price = getDroplist("����۸�",10);
		Map<String,Object> product = getDroplist("�����Ʒ",11);
		Map<String,Object> room = getDroplist("������",12);
		Map<String,Object> familyInfo = getDroplist("��ͥ�ṹ",13);
		Map<String,Object> invesInfo = getDroplist("����ҵ̬",14);
		
		
		/*List<ZsDictionaryTableVo>  cognitiveList =zsInfoDao.getDroplist("��֪;��");
		List<ZsDictionaryTableVo>  outExhibitList =zsInfoDao.getDroplist("��չ");
		List<ZsDictionaryTableVo>  netList =zsInfoDao.getDroplist("����");
		List<ZsDictionaryTableVo>  adList =zsInfoDao.getDroplist("������");
		List<ZsDictionaryTableVo>  exhibitionList =zsInfoDao.getDroplist("չ��/�");
		List<ZsDictionaryTableVo>  paperList =zsInfoDao.getDroplist("��ֽ");
		List<ZsDictionaryTableVo>  tvList =zsInfoDao.getDroplist("����");
		List<ZsDictionaryTableVo>  bankList =zsInfoDao.getDroplist("���л");
		List<ZsDictionaryTableVo>  introduceList =zsInfoDao.getDroplist("����");
		List<ZsDictionaryTableVo>  useList =zsInfoDao.getDroplist("������;");
		List<ZsDictionaryTableVo>  areaList =zsInfoDao.getDroplist("�������");
		List<ZsDictionaryTableVo>  factorList =zsInfoDao.getDroplist("��ע����");
		List<ZsDictionaryTableVo>  workAreaList =zsInfoDao.getDroplist("��������");
		List<ZsDictionaryTableVo>  liveAreaList =zsInfoDao.getDroplist("��ס����");
		List<ZsDictionaryTableVo>  workTypeList =zsInfoDao.getDroplist("������ҵ");
		List<ZsDictionaryTableVo>  competitorList =zsInfoDao.getDroplist("��������");
		List<ZsDictionaryTableVo>  priceList =zsInfoDao.getDroplist("����۸�");
		List<ZsDictionaryTableVo>  productList =zsInfoDao.getDroplist("�����Ʒ");
		List<ZsDictionaryTableVo>  roomList =zsInfoDao.getDroplist("������");
		List<ZsDictionaryTableVo>  familyInfoList =zsInfoDao.getDroplist("��ͥ�ṹ");
		List<ZsDictionaryTableVo>  invesInfoList =zsInfoDao.getDroplist("����ҵ̬");*/
		List<ZsDictionaryTableVo>  followTypeList =zsInfoDao.getDroplist("������ʽ");
		List<ZsDictionaryTableVo>  intentionLevelList =zsInfoDao.getDroplist("���򼶱�");
		//List<ZsIntentionTableVo> followIntentionLevelList=zsInfoDao.getIntentionLevel();//�ͻ�����
		log.info("�ͻ�����");
		droplist.add(age);
		droplist.add(cognitive);
		droplist.add(use);
		droplist.add(area);
		droplist.add(factor);
		droplist.add(workArea);
		droplist.add(liveArea);
		droplist.add(workType);
		droplist.add(competitor);
		droplist.add(price);
		droplist.add(product);
		droplist.add(room);
		droplist.add(familyInfo);
		droplist.add(invesInfo);
		if(addOrEdit.equals("add")) {
			log.info("add");
			Map<String,Object> followType = getDroplist("������ʽ",15);
			//List<ZsIntentionTableVo> intentionLevelList=zsInfoDao.getIntentionLevel();//�ͻ�����
			Map<String,Object> intentionLevel  = new HashMap<String,Object>();
			intentionLevel.put("value", intentionLevelList);
			intentionLevel.put("select", "radio");
			intentionLevel.put("href", "select16");
			intentionLevel.put("id", "idselect16");
			intentionLevel.put("name", "���򼶱�");
			droplist.add(followType);
			droplist.add(intentionLevel);
		}
		getAutoGenNextFollowDate();//����´θ�������
		//return null;
		httpSession.setAttribute("intentionLevelList", intentionLevelList);
		httpSession.setAttribute("followTypeList", followTypeList);
		return droplist;
	}
	
	/**
	 * �����ҵ�����������ڿͻ��б� 
	 */
	@Override
	public List<ZsCustomTableVo> getZsBasicInfoByEmployee(ZsInfoVo vo) {
		
		ZsInfoVo bean=new ZsInfoVo();
		String employee=(String) httpSession.getAttribute("username");
		if(employee!=null && !"".equals(employee)){
			bean.setEmployeeName(employee);//��ȡ��ҵ�����������еĿͻ�
			String cusnameOrTel=vo.getCusNameOrTel();
			log.info(cusnameOrTel==null);
			if(cusnameOrTel !=null && !"".equals(cusnameOrTel)){//���ݿͻ����ƺ͵绰ģ����ѯ
				boolean isNum = cusnameOrTel.matches("[0-9]+");
				if(isNum){
					bean.setTel(cusnameOrTel);
				}else{
					bean.setCusName(cusnameOrTel);
				}
			}
			String intentionType=vo.getIntentionType();
			String customStatus=vo.getCustomStatus();
			String age=vo.getAge();
			String followWay=vo.getFollowWay();
			String cognitiveWay=vo.getFirstVisWay();
			String investmentInfo=vo.getInvestmentInfo();
			String purposeItem=vo.getPurposeItem();
			if(intentionType !=null && !"".equals(intentionType)){//�߼���ѯ
				bean.setIntentionType(intentionType);
			}
			if(customStatus !=null && !"".equals(customStatus)){
				bean.setCustomStatus(customStatus);
			}
			if(intentionType !=null && !"".equals(intentionType)){
				bean.setIntentionType(intentionType);
			}
			if(age !=null && !"".equals(age)){
				bean.setAge(age);
			}
			if(followWay !=null && !"".equals(followWay)){
				bean.setFollowWay(followWay);
			}
			if(cognitiveWay !=null && !"".equals(cognitiveWay)){
				bean.setFirstVisWay(cognitiveWay);
			}
			if(investmentInfo !=null && !"".equals(investmentInfo)){
				bean.setInvestmentInfo(investmentInfo);
			}
			if(purposeItem !=null && !"".equals(purposeItem)){
				bean.setPurposeItem(purposeItem);
			}
			List<ZsCustomTableVo> list=zsInfoDao.getZsBasicInfoDao(bean);
			ZsInfoVo zsinfoVo;
			List<ZsInfoVo> customList=new ArrayList<ZsInfoVo>();
			for (ZsCustomTableVo zsCustomTableVo : list) {
				zsinfoVo=zsInfoDao.getZsBasicInfoAndLastFollowingByCusId(new ZsInfoVo(zsCustomTableVo.getCusId()));
				/*����жϿͻ�״̬
				 * if(zsinfoVo.getFollowInfo()!=null){
					if(zsinfoVo.getFollowInfo().contains("����")){
						zsinfoVo.setCustomStatus("����");
					}else if(zsinfoVo.getFollowInfo().contains("�ط�")){
						zsinfoVo.setCustomStatus("�ط�");
					}
				}*/
				customList.add(zsinfoVo);
			}
			httpSession.setAttribute("customList", customList);
			httpSession.setAttribute("customListCount", customList.size());
			log.info(list.size());
		}
		return null;
	}
	
	/**
	 * ��ʱû��
	 * �����ҵ�����������ڿͻ��б� �󣬸��ݿͻ������ƻ�绰������н�һ����ѯ
	 */
	@Override
	public List<ZsCustomTableVo> getZsBasicInfoByEmployeeAndCusNameOrTel(String cusNameOrTel) {
		
		ZsInfoVo bean=new ZsInfoVo();
		bean.setEmployeeName((String) httpSession.getAttribute("username"));
		   
		boolean isNum = cusNameOrTel.matches("[0-9]+");
		if(isNum){
			bean.setTel(cusNameOrTel);
		}else{
			bean.setCusName(cusNameOrTel);
		}
		
		List<ZsCustomTableVo> list=zsInfoDao.getZsBasicInfoDao(bean);
		ZsInfoVo zsinfoVo;
		List<ZsInfoVo> customList=new ArrayList<ZsInfoVo>();
		for (ZsCustomTableVo zsCustomTableVo : list) {
			zsinfoVo=zsInfoDao.getZsBasicInfoAndLastFollowingByCusId(new ZsInfoVo(zsCustomTableVo.getCusId()));
			if(zsinfoVo.getFollowInfo().contains("����")){
				zsinfoVo.setCustomStatus("����");
			}else if(zsinfoVo.getFollowInfo().contains("�ط�")){
				zsinfoVo.setCustomStatus("�ط�");
			}
			customList.add(zsinfoVo);
			
		}
		httpSession.setAttribute("customList", customList);
		httpSession.setAttribute("customListCount", customList.size());
		log.info(list.size());
		return null;
	}
	
	/**
	 * ��ʱû��
	 * �����ҵ�����������ڿͻ��б� �󣬸��ݿͻ��������Ϣ�߼���ѯ
	 */
	@Override
	public List<ZsCustomTableVo> getZsBasicInfoByEmployeeAndCusMoreInfo(
			String intentionType, String customStatus, String age,
			String followWay, String cognitiveWay, String investmentInfo,
			String purposeItem) {
		
		ZsInfoVo bean=new ZsInfoVo();
		String employee=(String) httpSession.getAttribute("username");
		if(employee!=null && !"".equals(employee)){
			bean.setEmployeeName(employee);
			
		}
		
		return null;
	}
	
	
	@Override
	public List<ZsCustomTableVo> getZsBasicInfoByCusID() {
		return null;
		
	}
	
	/**
	 * ���ݿͻ�id��øÿͻ��ĸ�����¼�͸ÿͻ�����ϸ���ϡ�
	 * �����浱ǰ�ͻ���id��   currentCustomID
	 */
	@Override
	public List<ZsCustomFollowRecordTableVo> getZsFollowInfoByCustomID() {
		
		ZsInfoVo bean=new ZsInfoVo();
		cusId=httpServletRequest.getParameter("cusId");
		if(cusId != null && !"".equals(cusId)){
			//httpSession.setAttribute("currentCustomID", cusId);//���ÿͻ�id������httpSession��
			RBACSubject.getSecurityUtils().getSession().setAttribute("currentCustomID", cusId);
		}
		if(cusId==null || "".equals(cusId)){
			//cusId=(String) httpSession.getAttribute("currentCustomID");//ȷ��cusid��ֵ
			cusId = (String)RBACSubject.getSecurityUtils().getSession().getAttribute("currentCustomID");
		}
		log.info("the id "+cusId);
		bean.setCusId(cusId);
		List<ZsCustomFollowRecordTableVo>followByCusIdlist=zsInfoDao.getZsFollowInfoDao(bean);
		log.info("list size "+followByCusIdlist.size());
		int i=0;
		if(followByCusIdlist.size()>0){
			for (ZsCustomFollowRecordTableVo zsCustomFollowRecordTableVo : followByCusIdlist) {//�ҵ����ô���
				if(zsCustomFollowRecordTableVo.getFollowWay()!=null && zsCustomFollowRecordTableVo.getFollowWay().contains((CharSequence)"����")){
					i++;
				}
			}
		}
		log.info(i);
		List<ZsCustomTableVo> cusBasicByCusIdlist=zsInfoDao.getZsBasicInfoDao(bean);
		log.info("list "+followByCusIdlist.size()+" "+cusBasicByCusIdlist.size());
		httpSession.setAttribute("followByCusIdlist", followByCusIdlist);
		httpSession.setAttribute("cusBasicByCusIdlist", cusBasicByCusIdlist);
		ZsCustomFollowRecordTableVo vo= zsInfoDao.getFirstAndNextFollowDateByCusId(bean);//��ȡ�ͻ��Ĵ������ں��´θ�������
		httpSession.setAttribute("cusTomFirstDate", vo.getFollowDate());
		httpSession.setAttribute("cusTomNextFollowDate", vo.getNextFollowDay());
		httpSession.setAttribute("cusTomComeView", Integer.toString(i));
		//getAllDroplist();//������е�������-----�ĵ�����༭����ʱ��ȥ��ȡdroplist
		return null;
	}
	
	/**
	 * �����Ͽͻ��ĸ�����¼��ͬʱ�޸Ŀͻ����пͻ����´θ���ʱ����������
	 */
	@Override
	public List<ZsCustomFollowRecordTableVo> insertZsOldCusFollowInfoDao(String employeeName,String followDate,String followWay,String followInfo,String intentionType,String nextFollowDate) {
		
		ZsInfoVo bean=new ZsInfoVo();
		System.out.println("employeeName:"+employeeName);
		//String cusId=(String) httpSession.getAttribute("currentCustomID");
		String cusId="";
		cusId=(String) httpSession.getAttribute("currentCustomID");
		if(cusId ==null || "".equals(cusId) || "null".equals(cusId)){
			cusId =(String) httpSession.getAttribute("cst_id");
		}
		//employeeName=(String) httpSession.getAttribute("username");
		User user = (User)httpSession.getAttribute("user");
		String employeeId = user.getUserID()+"";//"20";//����
		String []status = {"��ѯ","����","����"};
		log.info("durre "+cusId+" "+employeeName+" "+intentionType+" "+nextFollowDate);
		if(!"".equals(employeeId) && employeeId !=null){
			if(cusId !=null && !"".equals(cusId)){
					bean.setCusId(cusId);
					List<ZsCustomTableVo> custList = zsInfoDao.getZsBasicInfoDao(bean);
					ZsCustomTableVo custInfo = custList.get(0);
					String customStatus = custInfo.getCustomStatus();
					int i=0,j=0;
					log.info("customStatus:"+customStatus);
					if(customStatus!=null && !customStatus.equals("")) {	
						for(i=0; i<status.length; i++) {
							if(status[i].equals(customStatus))
								break;
						}
					}
					String followStatus="";
					if(followWay.equals("����") || followWay.equals("ȥ��"))
						followStatus = "��ѯ";
					else
						followStatus = followWay;
					
					for(j=0; j<status.length; j++) {
						if(status[j].equals(followStatus))
							break;
					}
					log.info("i:"+i+"   j:"+j);
					if(j >= i)
						bean.setCustomStatus(followStatus);
					bean.setEmployeeId(employeeId);
					bean.setFollowDate(followDate);
					bean.setFollowWay(followWay);
					bean.setFollowInfo(followInfo);
					zsInfoDao.insertZsFollowInfoDao(bean);
					//���Ŀͻ������������´θ���ʱ��
					if((intentionType !=null && !"".equals(intentionType)) || (nextFollowDate !=null && !"".equals(nextFollowDate))){
						bean.setIntentionType(intentionType);
						bean.setNextFollowDate(nextFollowDate);//ÿ����һ�����´θ�������������
						bean.setLastFollowDate(followDate);
						zsInfoDao.updateZsBasicInfoDao(bean);
					}
				}
			
		}
		return null;
	}
	
	/**
	 * �����¿ͻ��ĸ�����¼ --�¿ͻ��ĵ�һ�θ�����¼
	 */
	@Override
	public String insertZsNewCusFollowInfoDao(String cusId,
			String employeeId, String followDate, String followWay,
			String followInfo) {
		ZsInfoVo bean=new ZsInfoVo();
		bean.setCusId(cusId);
		bean.setEmployeeId(employeeId);
		bean.setFollowDate(followDate);
		bean.setFollowWay(followWay);
		bean.setFollowInfo(followInfo);
		
		int _result=zsInfoDao.insertZsFollowInfoDao(bean);
		if(_result !=-1){
			return "succ";
		}else{
			return "bad";
		}
		
	}
	
	
	/**
	 * ��ÿͻ��������� 
	 *  --������
	 *  --����δ����  ��ǰ����-�ͻ��´θ������� >3
	 *  --����������δ����   ��ǰ���� -�ͻ��´θ������� <3
	 */
	@Override
	public Map<String, String> getFollowRemind() {
		log.info("remaind");
		ZsInfoVo bean=new ZsInfoVo();
		//��һ�� ��ø���ҵ�������µ����еĿͻ�
		bean.setEmployeeName((String) httpSession.getAttribute("username"));
		log.info(httpSession.getAttribute("username"));
		ZsInfoVo zsinfoVo;
		List<ZsCustomTableVo> list=zsInfoDao.getZsBasicInfoDao(bean);
//		for (ZsCustomTableVo zsCustomTableVo : list) {
//			log.info(zsCustomTableVo.getCusId());
//		}
		List<ZsInfoVo> preReCalllist=new ArrayList<ZsInfoVo>();
		List<ZsInfoVo> overTimelist=new ArrayList<ZsInfoVo>();
		List<ZsInfoVo> overThreeTimeList=new ArrayList<ZsInfoVo>();
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar cal = Calendar.getInstance();// ȡ��ǰ���ڡ�
			Date currentTime=format.parse(format.format(cal.getTime()));
			//int days;
			for (ZsCustomTableVo zsCustomTableVo : list) {
				Date followdDate=format.parse(zsCustomTableVo.getNextFollowDate());
				long dateDiff=(currentTime.getTime() - followdDate.getTime())/(24 * 60 * 60 * 1000);
				//log.info("cha "+dateDiff);
				/*days = (int) Math.abs((currentTime.getTime() - followdDate.getTime())
						 / (24 * 60 * 60 * 1000));*/
				
				//log.info(" days "+dateDiff+" currtime "+currentTime.getTime()+" FOL "+followdDate.getTime());
				if(dateDiff==0){
					System.out.println("0������");
					zsinfoVo=zsInfoDao.getZsBasicInfoAndLastFollowingByCusId(new ZsInfoVo(zsCustomTableVo.getCusId()));
					preReCalllist.add(zsinfoVo);
				}if(dateDiff<=3 && dateDiff>0){
					System.out.println("����3����");
					zsinfoVo=zsInfoDao.getZsBasicInfoAndLastFollowingByCusId(new ZsInfoVo(zsCustomTableVo.getCusId()));
					
					//log.info(zsinfoVo.getNextFollowDate());
					overThreeTimeList.add(zsinfoVo);
				}if(dateDiff>3){
					//System.out.println("4 ����δ����");
					zsinfoVo=zsInfoDao.getZsBasicInfoAndLastFollowingByCusId(new ZsInfoVo(zsCustomTableVo.getCusId()));
					
					/*try {
						zsinfoVo.getCusId();
					} catch (NullPointerException e) {
						zsinfoVo.setCusId(zsCustomTableVo.getCusId());
						zsinfoVo.setCusName(zsCustomTableVo.getCusName());
					}*/
					//log.info(zsinfoVo==null);
					overTimelist.add(zsinfoVo);
				}
			}
			httpSession.setAttribute("preReCalllist", preReCalllist);
			httpSession.setAttribute("overTimelist", overTimelist);
			httpSession.setAttribute("overThreeTimeList", overThreeTimeList);
			httpSession.setAttribute("preReCallCount", preReCalllist.size());
			httpSession.setAttribute("overTimeCount", overTimelist.size());
			httpSession.setAttribute("overThreeTimeCount", overThreeTimeList.size());
			log.info(preReCalllist.size()+" "+overTimelist.size()+" "+overThreeTimeList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		log.info("������  "+list.size());
		return null;
	}

	@Override
	public List<ZsCustomBelongingAjaxVo> getZsCustomBelonging(String tel) {
		ZsInfoVo bean=new ZsInfoVo();
		ZsCustomBelongingAjaxVo ajaxVo;
		bean.setTel(tel);
		List<ZsCustomTableVo> list=zsInfoDao.getZsBasicInfoDao(bean);
		List<ZsCustomBelongingAjaxVo> ajaxList=new ArrayList<ZsCustomBelongingAjaxVo>();
		if(list.size()==0){
			System.out.println("new custom");
			ajaxVo=new ZsCustomBelongingAjaxVo("0", "/mbem/mcrm/business/addCustom.action");
			ajaxList.add(ajaxVo);
			httpSession.setAttribute("tel", tel);//Add 2016.3.1
		}else if(list.size()>0){
			System.out.println("old custom");
			ZsCustomTableVo zsCustom=list.get(0);
			//�Ƿ�Ϊ�����ͻ�������ǣ��Ƿ����ֱ�Ӹ���
			bean.setCusId(list.get(0).getCusId());
			log.info("id "+list.get(0).getCusId());
			ZsEmployeeTableVo vo=zsInfoDao.checkCusPublicOrInvalid(bean);
			log.info("emid "+vo.getEmployeeId());
			if(vo.getEmployeeId().equals("-10")){//�����ͻ�
				ZsDictionaryTableVo selectName=zsInfoDao.checkCusBeFollowedByEmployee(bean);
				if(selectName.getSelectName().equals("1")){
					log.info("�����ͻ�����ֱ�Ӹ���");
					ajaxVo=new ZsCustomBelongingAjaxVo("3", zsCustom.getCusName(), "0", "1", zsCustom.getFollowWay(), "createdOn", "user_name", zsCustom.getCusId(), "opp_id");
					ajaxList.add(ajaxVo);
				}else if(selectName.getSelectName().equals("0")){
					log.info("�����ͻ�������ֱ�Ӹ���");
					ajaxVo=new ZsCustomBelongingAjaxVo("3",zsCustom.getCusName(), "0", "1");
					ajaxList.add(ajaxVo);
				}
			}else if(vo.getEmployeeId().equals("-11")){//��Ч�ͻ�
					log.info("��Ч�ͻ� ");
				ajaxVo=new ZsCustomBelongingAjaxVo("4");
				ajaxList.add(ajaxVo);
			}else{
				List<ZsEmployeeTableVo> employlist=zsInfoDao.getEmployIdAndNameByCusId(bean);
				String employName=employlist.get(0).getEmployeeName();
				if(employName.equals(httpSession.getAttribute("username"))){
					log.info("�Լ��Ŀͻ� ");
					ajaxVo=new ZsCustomBelongingAjaxVo("1", "/mbem/mcrm/business/personal.action?cusId="+zsCustom.getCusId());
					ajaxList.add(ajaxVo);
				}else if(!employName.equals(httpSession.getAttribute("username")) && employName !=null && !"".equals(employName)){
					log.info("���˵Ŀͻ� "+employName);
					//ajaxVo=new ZsCustomBelongingAjaxVo(cst_type, cst_name, is_show_saler_name);//������ҵ���ʵĿͻ���������ʾ��ҵ����
					ajaxVo=new ZsCustomBelongingAjaxVo("2", zsCustom.getCusName(), "1", "stageName", "createdOn", employName, zsCustom.getTel()); 
					ajaxList.add(ajaxVo);
				}
			}		
		}
		return ajaxList;
	}


	@Override
	public String checkExistCusByTel(String tel) {
		
		String res="";
		ZsInfoVo bean=new ZsInfoVo();
		//1 ���Ҹÿͻ��Ƿ����
		bean.setTel(tel);
		List<ZsCustomTableVo> list=zsInfoDao.getZsBasicInfoDao(bean);
		log.info(list.size());
		if(list.size() !=0){
			httpSession.setAttribute("checkTelValid", "�õ绰�Ѿ��Ǽǹ�");//�õ绰�����Ѿ�ʹ�õǼǹ�
			res="no";
		}else{
			//httpSession.setAttribute("checkTelValid", "yes");//û�еǼǹ����û��ĵ绰����
			res="yes";
		}
		return res;
	}

	@Override
	public String getAutoGenNextFollowDate() {
		ZsCustomFollowRecordTableVo vo=zsInfoDao.getNextFollowDate();
		String nextday=vo.getNextFollowDay();
		log.info("���� "+vo.getNextFollowDay());
		String nday=nextday.substring(0,10);
		log.info(nday);
		httpSession.setAttribute("autoGenNextFollowDate", nday);
		log.info(httpSession.getAttribute("autoGenNextFollowDate"));
		return nday;
	}

	@Override
	public String insertIntentionCondition(String cusId, String employeeId,
			String createDate,String modifyDate,String progGuid) {
		ZsInfoVo bean=new ZsInfoVo();
		bean.setCusId(cusId);
		bean.setEmployeeId(employeeId);
		bean.setIntentionCreateDate(createDate);
		bean.setIntentionModifyDate(modifyDate);
		bean.setProgGuid(progGuid);
		
		int status=zsInfoDao.insertIntentionCondition(bean);
		if(status==-1){
			return "bad";
		}else{
			return "succ";
		}
		
	}
	
	@Override
	public List<ZsIntentionTableVo> getIntentionLevel() {
		List<ZsIntentionTableVo> list=zsInfoDao.getIntentionLevel();
		return list;
	}
	
	@Override
	public int setCustomToInvalid() {
		String cusId=(String) httpSession.getAttribute("currentCustomID");
		ZsInfoVo vo=new ZsInfoVo();
		vo.setCusId(cusId);
		int _ret=zsInfoDao.setCustomToInvalid(vo);
		return _ret;
	}
	
	public List<ZsInfoVo> getZsCustomListInfo(ZsInfoVo bean) {
		List<ZsInfoVo> customList = new ArrayList<ZsInfoVo>();
		customList = zsInfoDao.getZsCustomListInfoDao(bean);
		return customList;
	}
	public int getZsCustomCount(ZsInfoVo bean) {
		int count = zsInfoDao.getZsCustomCountDao(bean);
		return count;
	}
	public int getZsCustomCountByType(ZsInfoVo bean) {
		int count = zsInfoDao.getZsCustomCountByTypeDao(bean);
		log.info(count+"count");
		return count;
	}
	public List<ZsInfoVo> getZsCustomListInfoByType(ZsInfoVo bean) {
		List<ZsInfoVo> customList = new ArrayList<ZsInfoVo>();
		customList = zsInfoDao.getZsCustomListInfoByTypeDao(bean);
		return customList;
	}
	@Override
	public   List<Map<String, Object>>getTel_allCustomer() {
		  List<Map<String, Object>> map=zsInfoDao.getTel_allCustomer();
		return map;
	}
	@Override
	public String getDictionaryValue(String  classes) {
		ZsDictionaryTableVo vo=new ZsDictionaryTableVo();
		vo.setClasses(classes);
		String selectName="";
		ZsDictionaryTableVo entity=zsInfoDao.getDictionaryValue(vo);
		if(entity !=null ){
			selectName=entity.getSelectName();
		}
		return selectName;
	}
}
