package com.zs.oa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hanvon.faceid.sdk.FaceId;
import com.hanvon.faceid.sdk.FaceIdAnswer;
import com.hanvon.faceid.sdk.FaceId_ErrorCode;
import com.zs.common.util.DateUtil;
import com.zs.oa.dao.impl.AttendSynchDaoImpl;
import com.zs.oa.entity.AttendMachineNodeDto;
import com.zs.oa.entity.EmployDto;
import com.zs.oa.service.ILocalAttendSynchService;

/**
 * 本地考勤同步实现
 * 
 * @author Jiarui
 * 
 */
public class LocalAttendSynchServiceImpl implements ILocalAttendSynchService {

	Log log = LogFactory.getLog(LocalAttendSynchServiceImpl.class);

	@Autowired
	static
	AttendSynchDaoImpl attendDao;

	@Override
	public boolean syncLocalAttend() {
		AttendMachineNodeDto machineNode = attendDao.selectAttendMachineNodeDate("0001");
		String attendData = null;
		if ("1".equals(machineNode.getMachineStatus())) {
			attendData = attendDao.getDataFormAttend(machineNode);
		}
		if (attendData != null && !"getDataFormAttendBad".equals(attendData)) {
			
			String[] paras = {"duty_interval_after1","duty_interval_after2","duty_interval_before1","duty_interval_before2"};
			Map<String, String> attendInterval = attendDao.getOaSysPara(paras);
			
		}

		System.out.println("result" + "attendData");

		return false;
	}

	@Override
	public AttendMachineNodeDto selectAttendMachineNodeDate(String attendMachineCode) {
		return attendDao.selectAttendMachineNodeDate(attendMachineCode);
	}

	@Override
	public List<EmployDto> encapsulateDataToList(String answer) {
		List<EmployDto> exlist = new ArrayList<EmployDto>();
		// EmployDto employ=null;
		// List<Employee> list;
		// try{
		// list = getEmployeeList(answer);
		// if(list.size()>0){
		// for (int i = 0; i < list.size(); i++) {
		// Employee employee=list.get(i);
		// String user_id =
		// attendDao.selectUseridByWorkNo(employee.getId());//根据工号获取user_id
		// if(user_id!=null && !"".equals(user_id)){
		// employ=new EmployDto(employee.getTime(), user_id, employee.getName(),
		// employee.getId());
		// exlist.add(employ);
		// }
		// }
		// }
		// } catch (Exception e) {
		// log.info("make error :"+e);
		// }
		return exlist;
	}

	public static void main(String[] args) {
		//new LocalAttendSynchServiceImpl().syncLocalAttend();
		String attendData = getDataFormAttend1();
		String[] array =  attendData.split("\n");
		for(int i=1;i<array.length;i++){
			if(i == array.length-1){
				break;
			}
			System.out.println("i=" + i + " data=" + array[i]);
			// 时间
			Pattern p = Pattern.compile("([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-9])))\\s\\d+:\\d+:\\d+");
			Matcher m = p.matcher(array[i]);
			// 名称
			Pattern pname = Pattern.compile("name=\"[\u4e00-\u9fa5]{2,3}\"");
			Matcher mname = pname.matcher(array[i]);
			// ID
			Pattern pid = Pattern.compile("id=\"[0-9]{2,}\"");
			Matcher mid = pid.matcher(array[i]);
			
//			if(m.find()) {
//			    // System.out.println(m.group());
//			    System.out.println(m.group()); 
//			}
//			if (m.find()) {
//			    // System.out.println(m.group());
//			    time = m.group();
//			    listime.add(time);
//			}
//			if (mname.find()) {
//			    // System.out.println(mname.group());
//			    name = mname.group();
//			    name2 = name.substring(6, name.length() - 1);
//			    listname.add(name2);
//			}
//			if (mid.find()) {
//			    id = mid.group();
//			    // System.out.println(mid.group());
//			    id2 = id.substring(4, id.length() - 1);
//			    listid.add(id2);
//			}
//			
//			String time = null;
//			String id = null;
//			String name = null;
//			String id2 = null;
//			String name2 = null;

			

		}
		System.out.println(array.length + "===array[0]=" + array[0] );
		System.out.println(attendData);
	}
	
	public static String getDataFormAttend1() {
		String answer = null;
		String startTime = "2016-03-10 00:00:00";
		String endTime = DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");
		try {

			FaceId tcpClient = new FaceId("192.168.1.60",9922);
			if (startTime != null && (!"false".equals(startTime))) {
				String Command = "GetRecord(start_time=\"" + startTime + "\" end_time=\"" + endTime + "\")";
				FaceIdAnswer output = new FaceIdAnswer();
				tcpClient.setSecretKey("2015");
				FaceId_ErrorCode ErrorCode = tcpClient.Execute(Command, output,"gbk");
				if (ErrorCode.equals(FaceId_ErrorCode.Success)) {
					answer = output.answer;
					tcpClient.close();
				} else {
					answer = "getDataFormAttendBad";
					tcpClient.close();
					//log.info(" answer code not equal ");
				}
			} else {
				answer = "getDataFormAttendBad";
				tcpClient.close();
				//log.info(" time huoqu bad ");
			}
		} catch (Exception e) {
			answer = "getDataFormAttendBad";
			//log.info("answer catch bad " + e);
		}
		return answer;
	}

}
