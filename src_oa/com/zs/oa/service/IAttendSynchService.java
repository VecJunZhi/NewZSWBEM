package com.zs.oa.service;

import java.util.List;
import java.util.Map;

import com.zs.oa.entity.AttendMachineNodeDto;
import com.zs.oa.entity.DutyIntervalDto;
import com.zs.oa.entity.DutyTimeDto;
import com.zs.oa.entity.EmployDto;
import com.zs.oa.entity.Employee;
import com.zs.oa.entity.NewPerson;

public interface IAttendSynchService {
	/**
	 * 从考勤机获取最原始的数据
	 * @param attendIp
	 * @param port
	 * @param secretKey
	 * @return
	 * @throws Exception
	 */
      public String getDataFormAttend(String attendIp,int port,String secretKey,String charSet) throws Exception;
    /**
     * 将考勤机的数据解析，封装成对象，储存在一个list集合中。
     * @return
     * @throws Exception
     */
      public List<Employee> getEmployeeList(String answer) throws Exception;
      /**
       * 将数据插入到MySQL上下班考勤记录表中
       * @param attendIp
       * @return
       * @throws Exception
       */
      
      public String insertEmployee(List<Employee> list,String attendIp) throws Exception;
/**
 * 
 */
  	public String sysnchNewPerNight();
  	/**
  	 * 
  	 */
  	public String getAttDataWithoutStartTime(String attendIp, int port, String secretKey,String charSet)
			throws Exception;
  	/**
  	 * 
  	 */
	public String sysnchNeedJudge();
	/**
	 * 
	 */
	public Map<String, NewPerson>  insertLastMap();
	/**
	 * 
	 */
	public String insertDataWithoutJudge(List<Employee> list,String attendIp) throws Exception;
	/**
	 * 
	 */
	
	public String selectMinValueForPM(String user_id ,String register_type);

	public String delectMinValueForPM(String user_id ,String register_type,String register_time);
	public List<EmployDto> getListToMysql(String answer) throws Exception;
	public String insertToMysql(String attendIp,int  port,String secretKey,String  charSet) throws Exception;
	public String insertEmployeeData(List<EmployDto> list,String attendIp) throws Exception;
//////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 获取考勤机的节点数据
	 * @param attendMachineCode
	 * @return
	 */
	public AttendMachineNodeDto selectAttendMachineNodeDate(String attendMachineCode);
	/**
	 * 获得系统设置的考勤间隔参数
	 * @return
	 */
	public DutyIntervalDto getDutyIntervalData();
	/**
	 * 插入数据
	 * @param attendIp
	 * @param port
	 * @param secretKey
	 * @param charSet
	 * @param startTime
	 * @return
	 */
	 
	public String insertToMysql(String attendIp,int  port,String secretKey,String charSet,String startTime,String machineCode,String remark);
	/**
	 * 从考勤机获取节点之后的数据
	 * @param attendIp
	 * @param port
	 * @param secretKey
	 * @param charSet
	 * @param startTime
	 * @return
	 */
	public String getDataFormAttend(String attendIp, int port, String secretKey,String charSet,String startTime);
	/**
	 * 将考勤数据封装为对象-->list集合
	 * @param answer
	 * @return
	 */
	public  List<EmployDto> encapsulateDataToList(String answer);
	/**
	 * 根据用户工号获得用户的user_id
	 * @param work_no
	 * @return
	 */
	public String selectUseridByWorkNo(String work_no);
	/**
	 * 根据dutyType(排版类型)获得上线班的规定时间
	 * @param dutyType
	 * @return
	 */
	public DutyTimeDto getDutyTimeByDutyType(int dutyType);
	/**
	 * 通过考勤类型获得上下班时间进而判断该时间是第几次打卡
	 * @param time
	 * @param duty_type
	 * @return
	 */
	public String register_typeByduty_type(String time,int duty_type);
	/**
	 * 更新考勤机节点时间
	 * @param time
	 * @param machineCode
	 * @return
	 */
	public boolean updateMachineLastTime(String time,String lastIp,String machineCode);
	/**
	 * 删除重复数据 上班删掉最晚，下班删掉最早
	 * @return
	 */
	public String deleteDuplData();
	/**
	 * 手动同步天津考勤
	 */
	public String insertToMysqlForTxt(String remark);
	
}



