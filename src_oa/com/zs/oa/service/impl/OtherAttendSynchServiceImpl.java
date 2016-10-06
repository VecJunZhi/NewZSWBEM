package com.zs.oa.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;

import com.hanvon.faceid.sdk.FaceId;
import com.hanvon.faceid.sdk.FaceIdAnswer;
import com.hanvon.faceid.sdk.FaceId_ErrorCode;
import com.zs.busi.utils.LogUtil;
import com.zs.common.util.DateUtil;
import com.zs.oa.dao.impl.AttendSynchDaoImpl;
import com.zs.oa.entity.AttendMachineNodeDto;
import com.zs.oa.entity.DutyIntervalDto;
import com.zs.oa.entity.DutyTimeDto;
import com.zs.oa.entity.EmployDto;
import com.zs.oa.entity.Employee;
import com.zs.oa.entity.NewPerson;
import com.zs.oa.entity.OtherOweAttendDto;
import com.zs.oa.entity.OtherResponseDto;
import com.zs.oa.service.IAttendSynchService;
	
	
public class OtherAttendSynchServiceImpl implements IAttendSynchService{
	
	Log log=LogUtil.getLog();
	AttendSynchDaoImpl dao=new AttendSynchDaoImpl();
	
	@Override
	public String getDataFormAttend(String attendIp, int port, String secretKey,String charSet)throws Exception {
			String answer=null;
			try{
				@SuppressWarnings("resource")
				FaceId tcpClient = new FaceId(attendIp, port);
				String sdfend= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			    String startTime=dao.getStart_Time();
			    if(startTime!=null && (!startTime.equals("false"))){
			    	String Command="GetRecord(start_time=\""+startTime+"\" end_time=\""+sdfend+"\")";
					FaceIdAnswer output = new FaceIdAnswer();
					tcpClient.setSecretKey(secretKey);
					FaceId_ErrorCode ErrorCode = tcpClient.Execute(Command, output, charSet);
					log.info("ercode "+ErrorCode+" faco "+FaceId_ErrorCode.Success);
					if (ErrorCode.equals(FaceId_ErrorCode.Success)) {
						 answer=output.answer;
					}else{
						  answer="getDataFormAttendBad";
						  log.info("answer code not equal ");
					}	
			    }else{
			    	answer="getDataFormAttendBad";
			    	log.info(" time huoqu bad ");
			    }
			
		} catch (Exception e) {
			answer="getDataFormAttendBad";
			log.info("answer catch bad "+e);
		}
		return answer;
	}
	@Override
	public String getAttDataWithoutStartTime(String attendIp, int port, String secretKey,String charSet)throws Exception {
		String answer=null;
		try{
			@SuppressWarnings("resource")
			FaceId tcpClient = new FaceId(attendIp, port);
			String sdfend= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		    String Command="GetRecord(end_time=\""+sdfend+"\")";
			FaceIdAnswer output = new FaceIdAnswer();
			tcpClient.setSecretKey(secretKey);
			FaceId_ErrorCode ErrorCode = tcpClient.Execute(Command, output, charSet);
			if (ErrorCode.equals(FaceId_ErrorCode.Success)) {
				answer=output.answer;
				log.info(" connect attend,get the attendMachine Data");
			}else{
				answer="getDataFormAttendBad";
				log.info("answer code not equal ");
			}	
		} catch (Exception e) {
			answer="getDataFormAttendBad";
			log.info("answer catch bad "+e);
		}
		return answer;
	}
	
	@Override
	public List<Employee> getEmployeeList(String answer) throws Exception {
		List<String> listime= new ArrayList<String>();
	    List<String> listid= new ArrayList<String>();
	    List<String> listname= new ArrayList<String>();
	    List<Employee> list= new ArrayList<Employee>();
	       //时间
	       Pattern p = Pattern.compile("([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))\\s\\d+:\\d+:\\d+"); 
	       Matcher m = p.matcher(answer);
	       //名称
	       Pattern pname = Pattern.compile("name=\"[\u4e00-\u9fa5]{2,3}\""); 
	       Matcher mname = pname.matcher(answer);
	       //ID
	       Pattern pid = Pattern.compile("id=\"[0-9]{2,}\""); 
	       Matcher mid = pid.matcher(answer);
	       String time=null;
	       String id=null;
	       String name=null;
	     
	       String id2=null;
	       String name2=null;
	       while(m.find()) {                    
	         time=m.group();
	    	 listime.add(time);
	       } 
	       while(mname.find()){
		   	  name=mname.group();
		   	  name2=name.substring(6,name.length()-1);
		   	  listname.add(name2);
	       }
	       while(mid.find()){
	         id=mid.group();
	         id2=id.substring(4,id.length()-1);
	         listid.add(id2);
	       }
	       for (int i = 0; i < listime.size(); i++) {
	    	 Employee employee = new Employee(listime.get(i), listid.get(i), listname.get(i));
	         list.add(employee);
	       }
	    return list;
	}
	
	@Override
	public String insertDataWithoutJudge(List<Employee> list,String attendIp) throws Exception {
		Employee em=null;
	    String user_id=null;
	    int duty_type=0;
	    String register_type=null;
	    String flg="success";
	    try {
	    	for (int i = 0; i < list.size(); i++) {
		  		em=list.get(i);
		  		user_id=dao.queryUser_Id(em.getId());
		  		if(user_id!=null && (!"".equals(user_id)) && (!user_id.equals("false"))){
		  			duty_type=dao.queryDuty_Type(user_id);
			  		if(duty_type !=10){
			  			register_type=dao.register_type(em.getTime());
			  		}if(register_type!=null && !register_type.equals("false")){
			  			dao.insertAttendRecord(user_id, register_type, em.getTime(), attendIp, "", duty_type, 0, 0);
			  		}
		  		}
	  		}
		} catch (Exception e) {
			flg="error";
			log.info(e);
		}
	   return flg;
	}
	
	@Override
	public String insertEmployee(List<Employee> list,String attendIp) throws Exception {
		Employee em=null;
	    String user_id=null;
	    int duty_type=0;
	    String register_type=null;
	    String flg="success";
	    try {
	    	for (int i = 0; i < list.size(); i++) {
	  			em=list.get(i);
	  			user_id=dao.queryUser_Id(em.getId());
	  			if(user_id!=null && (!"".equals(user_id)) && (!user_id.equals("false"))){
	  				String count=dao.JudgeIfInsert(user_id);
	  					if(count.equals("0")){
	  						log.info(" new data not insert now");
	  					}else{
	  						duty_type=dao.queryDuty_Type(user_id);
		  					if(duty_type !=10){
		  						register_type=dao.register_type(em.getTime());
		  					}if(register_type!=null && !register_type.equals("false")){
		  						
		  						if(register_type.equals("2") ){
		  							
		  							String count2=dao.selectMinValueForPM(user_id, "2");
		  							if(count2.equals("1")){
		  								String result=dao.deleteMinValueForPM(user_id, "2","");
		  								if(result.equals("true")){
		  									dao.insertAttendRecord(user_id, register_type, em.getTime(), attendIp, "", duty_type, 0, 0);
		  								}
		  								dao.insertAttendRecord(user_id, register_type, em.getTime(), attendIp, "", duty_type, 0, 0);
		  							}
		  							
		  						}else if(register_type.equals("4")){
		  							
		  							String count3=dao.selectMinValueForPM(user_id, "4");
		  							if(count3.equals("1")){
		  								String result=dao.deleteMinValueForPM(user_id, "4","");
		  								if(result.equals("true")){
		  									dao.insertAttendRecord(user_id, register_type, em.getTime(), attendIp, "", duty_type, 0, 0);
		  								}
		  								
		  							}
		  							
		  						}else{
		  							dao.insertAttendRecord(user_id, register_type, em.getTime(), attendIp, "", duty_type, 0, 0);
		  						}
		  						
		  						
		  			  			
		  					}
	  					}
	  			}
	  				
	  		}
		} catch (Exception e) {
			flg="error";
			e.printStackTrace();
		}
	   
		return flg;
	}
	@Override
	public String insertEmployeeData(List<EmployDto> list,String address) throws Exception {
		EmployDto em=null;
	     String user_id=null;
	     int duty_type=0;
	     String register_type=null;
	     String flg="";
	     try {
	    	  for (int i = 0; i < list.size(); i++) {
	  			em=list.get(i);
	  			user_id=em.getUser_id();
	  			duty_type=dao.queryDuty_Type(user_id);
		  					if(duty_type !=0){
		  						register_type=register_typeByduty_type(em.getTime(),duty_type);
		  					}if(register_type!=null && !register_type.equals("false")){
		  						dao.insertAttendRecord(user_id, register_type, em.getTime(), address, "", duty_type, 0, 0);
		  			  			flg=em.getTime();
			  					}
	  		}
	   } catch (Exception e) {
			flg=null;
			log.info("make error "+e);
		}
	   
	   return flg;
	}
	
	public String smpInsertDataToSql(List<OtherResponseDto> list,String address) throws Exception {
		
		AttendSynchDaoImpl dao=new AttendSynchDaoImpl();
		OtherResponseDto em=null;
	     String user_id=null;
	     int duty_type=0;
	     String register_type=null;
	     String flg="";
	      try {
	    	  for (int i = 0; i < list.size(); i++) {
	  			em=list.get(i);
	  			user_id=em.getUser_id();
	  			duty_type=dao.queryDuty_Type(user_id);
	  			if(duty_type !=0){
		  				register_type=register_typeByduty_type(em.getTime(),duty_type);
		  		}if(register_type!=null && !register_type.equals("false")){
		  				dao.insertAttendRecord(user_id, register_type, em.getTime(), address, "", duty_type, 0, 0);
		  			  	flg=em.getTime();
			  	}
	  		}
	    	
	   } catch (Exception e) {
			
			flg="error";
			e.printStackTrace();
		}
	    return flg;
	}
	/**
	 * 
	 */
	@Override
	public String sysnchNewPerNight(){
		
		Calendar cal =Calendar.getInstance();
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		int minute=cal.get(Calendar.MINUTE);
		if (hour==1 && minute<59 && minute>=30  ){
			return "true";
		}else{
			return "false";
			}
		}
		@Override	
		public String sysnchNeedJudge(){
			String count=dao.SynchNeed();
			return count;
	}
		@Override
		public Map<String, NewPerson>  insertLastMap(){
			Map<String, NewPerson> map=dao.insertLastMap();
			return map;
		}
		@Override
		public String selectMinValueForPM(String user_id ,String register_type){
			String count=dao.selectMinValueForPM(user_id, register_type);
			return count;
			
		}
		@Override
		public String delectMinValueForPM(String user_id ,String register_type,String register_time){
			String result=dao.deleteMinValueForPM(user_id, register_type,register_time);
			return result;
		}
		/**
	 * 
	 * @throws Exception 
	 */
	@Override
	public  List<EmployDto> getListToMysql(String answer) throws Exception{
	
		 List<EmployDto> exlist = new ArrayList<EmployDto>();
		 EmployDto employ=null;
		 NewPerson newp=null;
		 List<Employee> list=getEmployeeList(answer);
			if(list.size()>0){
				 Map<String, OtherOweAttendDto> map=dao.selectExistEmployData();
			 if(map.size()!=0){
				  Iterator<Entry<String, OtherOweAttendDto>> it2=map.entrySet().iterator();
				  OtherOweAttendDto otherDto=null;
				  while(it2.hasNext()){
					Entry<String, OtherOweAttendDto> entry=it2.next();
					otherDto=entry.getValue();
					DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date maxtime=df.parse(otherDto.getMaxTime());
					for (int i = 0; i < list.size(); i++) {
					Date timer=	df.parse(list.get(i).getTime());
						if(otherDto.getWork_no().equals(list.get(i).getId()) && (maxtime.getTime()<timer.getTime())){
							employ=new EmployDto(list.get(i).getTime(), otherDto.getUser_id(), otherDto.getName(), otherDto.getWork_no());
							exlist.add(employ);
						}
						
					}
				} 
			  }
			String count=sysnchNeedJudge();
			if(!count.equals("0") && (!"".equals(count))){
			 Map<String, NewPerson>	mapN=insertLastMap();
			  Iterator<Entry<String, NewPerson>> it=mapN.entrySet().iterator();
			  while(it.hasNext()){
				Entry<String, NewPerson> entry=it.next();
				newp=entry.getValue();
				for (int i = 0; i < list.size(); i++) {
					if(newp.getWork_no().equals(list.get(i).getId())){
						employ=new EmployDto(list.get(i).getTime(), newp.getUser_id(), newp.getName(), newp.getWork_no());
						exlist.add(employ);
					}
				}
				
			}
			}
			}
			return exlist;
	}
	@Override
	public String insertToMysql(String attendIp,int  port,String secretKey,String  charSet) throws Exception{
		
		String result="";
		String answer=getAttDataWithoutStartTime(attendIp, port, secretKey, charSet);
		if(!"getDataFormAttendBad".equals(answer)){
			 List<EmployDto> list=getListToMysql(answer);
			 if(list.size()>0){
			 result=insertEmployeeData(list, attendIp);
			}else{
				log.info(" needlist size is zero");
			}
		}else{
			log.info(" answer bad");
		}
		
		
		
		return result;
		
	}
	/////////////////////////////////////////////新版本//////////////////////////////////////////////
	@Override
	public AttendMachineNodeDto selectAttendMachineNodeDate(String attendMachineCode){
		
		AttendMachineNodeDto dto=dao.selectAttendMachineNodeDate(attendMachineCode);
		return dto;
	}
	@Override
	public DutyIntervalDto getDutyIntervalData() {
		String after1=dao.getDutyInterval("duty_interval_after1");
		String after2=dao.getDutyInterval("duty_interval_after2");
		String before1=dao.getDutyInterval("duty_interval_before1");
		String before2=dao.getDutyInterval("duty_interval_before2");
		DutyIntervalDto dto=new DutyIntervalDto(after1, after2, before1, before2);
		return dto;
	}
	
	@Override
	public String selectUseridByWorkNo(String work_no) {
		String user_id=dao.selectUseridByWorkNo(work_no);
		return user_id;
	}
	
	@Override
	public  List<EmployDto> encapsulateDataToList(String answer){
		List<EmployDto> exlist = new ArrayList<EmployDto>();
		EmployDto employ=null;
		List<Employee> list;
		try{
			list = getEmployeeList(answer);
			if(list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					  Employee employee=list.get(i);
					  String user_id=selectUseridByWorkNo(employee.getId());
					  if(user_id!=null && !"".equals(user_id)){
							employ=new EmployDto(employee.getTime(), user_id, employee.getName(), employee.getId());
							exlist.add(employ);
					  }
				 }
			}
		}catch (Exception e){
			log.info("make error :"+e);
		}
		return exlist;
	}
	@Override
	public String getDataFormAttend(String attendIp, int port, String secretKey,String charSet,String startTime){
		String answer=null;
		try {
			@SuppressWarnings("resource")
			FaceId tcpClient = new FaceId(attendIp, port);
			//String sdfend= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String sdfend= DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");
		    if(startTime!=null && (!startTime.equals("false"))){
		    	String Command="GetRecord(start_time=\""+startTime+"\" end_time=\""+sdfend+"\")";
				FaceIdAnswer output = new FaceIdAnswer();
				   tcpClient.setSecretKey(secretKey);
				   FaceId_ErrorCode ErrorCode = tcpClient.Execute(Command, output, charSet);
				   if (ErrorCode.equals(FaceId_ErrorCode.Success)) {
					 answer=output.answer;
					}else{
					   answer="getDataFormAttendBad";
					   log.info(" answer code not equal ");
				   }	
		    }else{
		    	answer="getDataFormAttendBad";
		    	log.info(" time huoqu bad ");
		    }
			
		} catch (Exception e) {
			answer="getDataFormAttendBad";
			log.info("answer catch bad "+e);
		}
		return answer;
	}
	@Override
	public DutyTimeDto getDutyTimeByDutyType(int dutyType) {
		DutyTimeDto dto=dao.getDutyTimeByDutyType(dutyType);
		return dto;
	}
	@Override
	public String register_typeByduty_type(String time,int duty_type) {
		 String num=null;
		 String moringMinWork=null;//上午上班前(时间不限，用00：00：00代替)
		 String moringMaxWork=null;//上午上班后
		 String moringMinOffWork=null;//上午下班前
		 String moringMaxOFFWork=null;//上午下班后
		 String noonMinWork=null;//下午上班前
		 String noonMaxWork=null;//下午上班后
		 String noonMinOffWork=null;//下午下班前
		 String noonMaxOFFWork=null;//下午下班后(时间不限，用 23：59：59代替)
		try {
			
			DutyTimeDto dutyTimeDto= getDutyTimeByDutyType(duty_type);//获得上下班时间
			DutyIntervalDto intervalDto=getDutyIntervalData();//获得上下班时间间隔
			//上午上班
			moringMaxWork=DateUtil.addMinute(Integer.parseInt(intervalDto.getDuty_interval_after1()), "HH:mm:ss", dutyTimeDto.getDuty_time1());
			moringMinWork=DateUtil.addMinute(-(Integer.parseInt(intervalDto.getDuty_interval_before1())), "HH:mm:ss", dutyTimeDto.getDuty_time1());
			//上午下班
			moringMinOffWork=DateUtil.addMinute(-(Integer.parseInt(intervalDto.getDuty_interval_before2())), "HH:mm:ss", dutyTimeDto.getDuty_time2());
			moringMaxOFFWork=DateUtil.addMinute(Integer.parseInt(intervalDto.getDuty_interval_after2()), "HH:mm:ss", dutyTimeDto.getDuty_time2());
			//下午上班
			noonMaxWork=DateUtil.addMinute(Integer.parseInt(intervalDto.getDuty_interval_after1()), "HH:mm:ss", dutyTimeDto.getDuty_time3());
			noonMinWork=DateUtil.addMinute(-(Integer.parseInt(intervalDto.getDuty_interval_before1())), "HH:mm:ss", dutyTimeDto.getDuty_time3());
			//下午下班
			noonMinOffWork=DateUtil.addMinute(-(Integer.parseInt(intervalDto.getDuty_interval_before2())), "HH:mm:ss", dutyTimeDto.getDuty_time4());
			noonMaxOFFWork=DateUtil.addMinute(Integer.parseInt(intervalDto.getDuty_interval_after2()), "HH:mm:ss", dutyTimeDto.getDuty_time4());
			if(DateUtil.isBetween(time.substring(11), "00:00:00", moringMaxWork, "HH:mm:ss")){
				num="1";
			}else if(DateUtil.isBetween(time.substring(11), moringMinOffWork, moringMaxOFFWork, "HH:mm:ss")){
				num="2";
			}else if(DateUtil.isBetween(time.substring(11), noonMinWork, noonMaxWork, "HH:mm:ss")){
				num="3";
			}else if(DateUtil.isBetween(time.substring(11), noonMinOffWork, "23:59:59", "HH:mm:ss")){
				num="4";
			}else {
				num="5";
			}
		} catch (Exception e) {
			num="false";
			log.info("make error ： "+e);
		}
		return num;
	}
	@Override
	public String insertToMysql(String attendIp,int  port,String secretKey,String  charSet,String startTime,String machineCode,String remark){
		String result="";
		String answer=getDataFormAttend(attendIp, port, secretKey, charSet,startTime);
		if(!"getDataFormAttendBad".equals(answer)){
			 List<EmployDto> list=encapsulateDataToList(answer);
			 if(list.size()>0){
				 try {
					 result=insertEmployeeData(list, remark);
				 } catch (Exception e) {
					 log.info("make error : "+e);
				 }
			}else{
				log.info(" encapsulate Data To List size is zero");
			}
		}else{
			log.info(" get attend machine data is bad");
		}
			
			
			
			return result;
			
		}
	@Override
	public boolean updateMachineLastTime(String time, String lastIP,String machineCode) {
		boolean _flg=dao.updateMachineLastTime(time, lastIP,machineCode);
		return _flg;
	}
	@Override
	public String deleteDuplData() {
		dao.deleteDuplData();
		return "";
	}
	@Override
	public String insertToMysqlForTxt(String remark) {
		// TODO Auto-generated method stub
		return null;
	}
	
		
		
	
	}
