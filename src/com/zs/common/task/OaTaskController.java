package com.zs.common.task;

import java.net.InetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zs.oa.entity.AttendMachineNodeDto;
import com.zs.oa.service.IAttendSynchService;
import com.zs.oa.service.impl.AttendSynchServiceImpl;

/**
 * OA��ʱ����
 * @author zsjr
 *
 */
public class OaTaskController {

	Log log= LogFactory.getLog(OaTaskController.class);
	IAttendSynchService  service=new AttendSynchServiceImpl();
	
	public void AttendSynchTask(){
		
		//�ж��ǲ��Ƿ�����ִ�ж�ʱ����
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
		 * 1	��ȡҪͬ�����ڻ��Ľڵ�����
		 * 2	���ݽڵ����ݻ�ȡ���ڻ�����
		 * 3	���ڻ����ݴ�����װ�ɼ���
		 * 4	��ʼͬ��
		 * 
		 */
		AttendMachineNodeDto dto=service.selectAttendMachineNodeDate("0001");//1 ѡȡ���ڻ��ڵ�����
		if(dto !=null ){
			String attendIp=dto.getMachineIP();
			String sport=dto.getMachinePort();
			int port=Integer.parseInt(sport);
			String secretKey=dto.getMachineSecretkey();
			String deviceCharset=dto.getMachineCharset();
			String startTime=dto.getLastTime();
			String locked=dto.getMachineStatus();
			String remark=dto.getRemark();
			if("1".equals(locked)){//������ڻ�״̬��Ϊ1 ��˵�����Զ�ȡ��������
				log.info(" step 0 : get the last attend data from db��startTime is "+startTime);
				String lastTime=service.insertToMysql(attendIp, port, secretKey, deviceCharset,startTime,"0001",remark);
				if(lastTime!=null && !"".equals(lastTime)){
					log.info(" step2: the attendData synch Completed,the machine code "+"0001");
					//��Ҫ����ʱ��
					boolean _res=service.updateMachineLastTime(lastTime,"local","0001");
					if(_res){
						//ɾ���ظ����� �ϰ�ɾ�������°�ɾ������
						log.info("step3 : attend machine update time ok");
						//service.deleteDuplData();
					}
				}
			}
			
		}
	}
}
