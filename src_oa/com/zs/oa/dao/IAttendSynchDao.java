package com.zs.oa.dao;

import java.util.List;
import java.util.Map;

import com.hanvon.faceid.sdk.FaceId;
import com.hanvon.faceid.sdk.FaceIdAnswer;
import com.hanvon.faceid.sdk.FaceId_ErrorCode;
import com.zs.common.util.DateUtil;
import com.zs.oa.entity.AttendMachineNodeDto;
import com.zs.oa.entity.AttendRecordDto;
import com.zs.oa.entity.DutyIntervalDto;
import com.zs.oa.entity.DutyTimeDto;
import com.zs.oa.entity.NewPerson;
import com.zs.oa.entity.OtherOweAttendDto;



public interface IAttendSynchDao {
	/**
	 *  插入考勤数据
	 * @param id
	 * @param type
	 * @param time
	 * @param ip
	 * @param remark
	 * @param dtype
	 * @param mduty
	 * @param mid
	 * @throws Exception
	 */
	 
	public void insertAttendRecord(String id,String type,String time,String ip,String remark,int dtype,int mduty,int mid) throws Exception;
	/**
	 * 获取考勤数据
	 * @return
	 * @throws Exception
	 */
	 
	public List<AttendRecordDto> queryAttendRecord() throws Exception;
	/**
	 * 根据工号获取用户ID
	 * @param work_no
	 * @return
	 * @throws Exception
	 */
	public String queryUser_Id(String work_no) throws Exception;
	/**
	 * 根据用户ID，获取该用户的排班类型
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	 
	public int queryDuty_Type(String user_id) throws Exception;
	/**
	 * 根据时间，判断当前时间段应该为第几次打卡
	 * @param time
	 * @return
	 * @throws Exception
	 */
	public String register_type(String time) throws Exception;
	/**
	 * 为确保相同数据不重复输入，在每次插入数据前，读取数据库中的时间最大值
	 * @return
	 * @throws Exception
	 */
	public String getStart_Time() throws Exception;
	/**
	 * 判断下午一点二点属于第几次打卡
	 * 
	 */
	public String otFry(int hour,String date,String user_id);
	/**
	 * 判断是否要现在插入此数据。
	 * @param user_id
	 * @return
	 */
	 public String JudgeIfInsert(String user_id );
	 /**
	  *  获取新增人员之间的Map集合
	  * @return
	  */
	 public Map<String, NewPerson>  insertLastMap();
	 /**
	  * 判读是否有需要夜间同步的数据
	  * @return
	  */
	 public String SynchNeed();
	 /**
	  * 获取重复数据的count数
	  * @param user_id
	  * @param register_type
	  * @return
	  */
	 public String selectMinValueForPM(String user_id ,String register_type);
	 /**
	  *  删除重复数据
	  * @param user_id
	  * @param register_type
	  * @param register_time
	  * @return
	  */
		public String deleteMinValueForPM(String user_id ,String register_type,String register_time);
	/**
	 * 获取考勤表里每个人的时间节点
	 * @return
	 */
		public  Map<String,OtherOweAttendDto>  selectExistEmployData();
		
//////////////////////////////////////////////////////////////////////////////
	/**
	 * 获得考勤间隔参数
	 * @param para_name
	 * @return
	 */
	public String getDutyInterval(String para_name);
	
	/**
	 * 获得OA系统SysPara中参数  (考勤时间间隔等等)
	 * @param paraArray[]    
	 * @return Map key:paraName   value: paraValue
	 */
	public Map<String,String> getOaSysPara(String[] paraArray);
	
	
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
	 * 获取相应考勤机的节点数据
	 * @param attendMachineCode
	 * @return
	 */
	public AttendMachineNodeDto selectAttendMachineNodeDate(String attendMachineCode);
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
	 * 从考勤机获取节点之后的数据
	 * @param AttendMachineNodeDto
	 * @return
	 */
	public String getDataFormAttend(AttendMachineNodeDto machineNode);
	
	
}
