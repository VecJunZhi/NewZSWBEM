package com.zs.common.task;

import java.net.InetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zs.oa.entity.AttendMachineNodeDto;
import com.zs.oa.service.IAttendSynchService;
import com.zs.oa.service.impl.AttendSynchServiceImpl;

/**
 * OA定时任务
 * @author zsjr
 *
 */
public class OaTaskController {

	Log log= LogFactory.getLog(OaTaskController.class);
	IAttendSynchService  service=new AttendSynchServiceImpl();
	
	public void AttendSynchTask(){
		
		//判断是不是服务器执行定时任务
		String localIPAddr = "";
		try{
			InetAddress netAddress = InetAddress.getLocalHost();  
			localIPAddr = netAddress.getHostAddress(); 
		}catch(Exception e){
			e.printStackTrace();
		}
		if(!"192.168.1.23".equals(localIPAddr)){
			return;
		}

		/**
		 * 1	获取要同步考勤机的节点数据
		 * 2	根据节点数据获取考勤机数据
		 * 3	考勤机数据处理并封装成集合
		 * 4	开始同步
		 * 
		 */
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
				log.info(" step 0 : get the last attend data from db，startTime is "+startTime);
				String lastTime=service.insertToMysql(attendIp, port, secretKey, deviceCharset,startTime,"0001",remark);
				if(lastTime!=null && !"".equals(lastTime)){
					log.info(" step2: the attendData synch Completed,the machine code "+"0001");
					//需要更新时间
					boolean _res=service.updateMachineLastTime(lastTime,"local","0001");
					if(_res){
						//删除重复数据 上班删掉最晚，下班删掉最早
						log.info("step3 : attend machine update time ok");
						//service.deleteDuplData();
					}
				}
			}
			
		}
	}
}
