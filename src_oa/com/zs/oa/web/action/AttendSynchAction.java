package com.zs.oa.web.action;


import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zs.oa.dao.impl.AttendSynchDaoImpl;
import com.zs.oa.entity.AttendMachineNodeDto;
import com.zs.oa.service.IAttendSynchService;


@Controller
@RequestMapping(value="oa/attend")
public class AttendSynchAction {
	
	Log log =LogFactory.getLog(AttendSynchAction.class);
	@Autowired
	HttpServletRequest request;
	@Autowired
	IAttendSynchService service;
	
	@RequestMapping(value="/insertDataToTable")
	public  void insertDataToTable(@RequestParam(value="code",required=true) String code, @RequestParam String machineCode) throws Exception{
		
		if(code.equals("zs888attend")){
			//int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			/**
			 * 1	获取要同步考勤机的节点数据
			 * 2	根据节点数据获取考勤机数据
			 * 3	考勤机数据处理并封装成集合
			 * 4	开始同步
			 * 
			 */
			//if(hour !=0){
				AttendMachineNodeDto dto=service.selectAttendMachineNodeDate(machineCode);//1 选取考勤机节点数据
				if(dto !=null ){
					String attendIp=dto.getMachineIP();
					String sport=dto.getMachinePort();
					int port=Integer.parseInt(sport);
					String secretKey=dto.getMachineSecretkey();
					String deviceCharset=dto.getMachineCharset();
					String startTime=dto.getLastTime();
					String locked=dto.getMachineStatus();
					String remark=dto.getRemark();
					if("1".equals(locked)){//如果考勤机状态码为1 ，说明可以读取考勤数据
						log.info(" step 0 : get the last attend data from db");
						String lastTime=service.insertToMysql(attendIp, port, secretKey, deviceCharset,startTime,machineCode,remark);
						if(lastTime!=null && !"".equals(lastTime)){
							log.info(" step2: the attendData synch Completed,the machine code "+machineCode);
							//需要更新时间
							boolean _res=service.updateMachineLastTime(lastTime,request.getRemoteAddr(),machineCode);
							if(_res){
								//删除重复数据 上班删掉最晚，下班删掉最早
								log.info("step3 : attend machine update time ok");
								//service.deleteDuplData();
							}
						}
					}
					
				}
			/*}else{
				log.info("current time dont to synch");
			}*/
		}else{
			log.info("Verification code is wrong");
		}
	}
	@RequestMapping(value="/insertToMysqlForTxt")
	public void insertToMysqlForTxt(@RequestParam(value="code",required=true) String code){
		if(code.equals("zs888attend")){
			//service.insertToMysqlForTxt("天津");
			service.insertToMysqlForTxt("御泽");
		}
	}
	
	public  void insertDataToTable2() throws Exception{

			/**
			 * 1	获取要同步考勤机的节点数据
			 * 2	根据节点数据获取考勤机数据
			 * 3	考勤机数据处理并封装成集合
			 * 4	开始同步
			 * 
			 */
			//if(hour !=0){
				AttendMachineNodeDto dto=service.selectAttendMachineNodeDate("0001");//1 选取考勤机节点数据
				if(dto !=null ){
					String attendIp=dto.getMachineIP();
					String sport=dto.getMachinePort();
					int port=Integer.parseInt(sport);
					String secretKey=dto.getMachineSecretkey();
					String deviceCharset=dto.getMachineCharset();
					String startTime=dto.getLastTime();
					String locked=dto.getMachineStatus();
					String remark=dto.getRemark();
					if("1".equals(locked)){//如果考勤机状态码为1 ，说明可以读取考勤数据
						log.info(" step 0 : get the last attend data from db");
						String lastTime=service.insertToMysql(attendIp, port, secretKey, deviceCharset,startTime,"0001",remark);
						if(lastTime!=null && !"".equals(lastTime)){
							log.info(" step2: the attendData synch Completed,the machine code "+"0001");
							//需要更新时间
							boolean _res=service.updateMachineLastTime(lastTime,request.getRemoteAddr(),"0001");
							if(_res){
								//删除重复数据 上班删掉最晚，下班删掉最早
								log.info("step3 : attend machine update time ok");
								//service.deleteDuplData();
							}
						}
					}
					
				}

	}
	
public static void main(String[] args) throws Exception {
	/*IAttendSynchService service = new AttendSynchServiceImpl();
	String answer=service.getAttDataWithoutStartTime("192.168.1.44", 9922, "2015", "gbk");
	service.getEmployeeList(answer).size();*/
	Date date=new Date();
	@SuppressWarnings("deprecation")
	int hour=date.getHours();
	int h=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	System.out.println(hour +" "+h);
}
}
