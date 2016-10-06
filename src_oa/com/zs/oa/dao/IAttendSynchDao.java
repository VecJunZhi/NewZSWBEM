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
	 *  ���뿼������
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
	 * ��ȡ��������
	 * @return
	 * @throws Exception
	 */
	 
	public List<AttendRecordDto> queryAttendRecord() throws Exception;
	/**
	 * ���ݹ��Ż�ȡ�û�ID
	 * @param work_no
	 * @return
	 * @throws Exception
	 */
	public String queryUser_Id(String work_no) throws Exception;
	/**
	 * �����û�ID����ȡ���û����Ű�����
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	 
	public int queryDuty_Type(String user_id) throws Exception;
	/**
	 * ����ʱ�䣬�жϵ�ǰʱ���Ӧ��Ϊ�ڼ��δ�
	 * @param time
	 * @return
	 * @throws Exception
	 */
	public String register_type(String time) throws Exception;
	/**
	 * Ϊȷ����ͬ���ݲ��ظ����룬��ÿ�β�������ǰ����ȡ���ݿ��е�ʱ�����ֵ
	 * @return
	 * @throws Exception
	 */
	public String getStart_Time() throws Exception;
	/**
	 * �ж�����һ��������ڵڼ��δ�
	 * 
	 */
	public String otFry(int hour,String date,String user_id);
	/**
	 * �ж��Ƿ�Ҫ���ڲ�������ݡ�
	 * @param user_id
	 * @return
	 */
	 public String JudgeIfInsert(String user_id );
	 /**
	  *  ��ȡ������Ա֮���Map����
	  * @return
	  */
	 public Map<String, NewPerson>  insertLastMap();
	 /**
	  * �ж��Ƿ�����Ҫҹ��ͬ��������
	  * @return
	  */
	 public String SynchNeed();
	 /**
	  * ��ȡ�ظ����ݵ�count��
	  * @param user_id
	  * @param register_type
	  * @return
	  */
	 public String selectMinValueForPM(String user_id ,String register_type);
	 /**
	  *  ɾ���ظ�����
	  * @param user_id
	  * @param register_type
	  * @param register_time
	  * @return
	  */
		public String deleteMinValueForPM(String user_id ,String register_type,String register_time);
	/**
	 * ��ȡ���ڱ���ÿ���˵�ʱ��ڵ�
	 * @return
	 */
		public  Map<String,OtherOweAttendDto>  selectExistEmployData();
		
//////////////////////////////////////////////////////////////////////////////
	/**
	 * ��ÿ��ڼ������
	 * @param para_name
	 * @return
	 */
	public String getDutyInterval(String para_name);
	
	/**
	 * ���OAϵͳSysPara�в���  (����ʱ�����ȵ�)
	 * @param paraArray[]    
	 * @return Map key:paraName   value: paraValue
	 */
	public Map<String,String> getOaSysPara(String[] paraArray);
	
	
	/**
	 * �����û����Ż���û���user_id
	 * @param work_no
	 * @return
	 */
	public String selectUseridByWorkNo(String work_no);
	/**
	 * ����dutyType(�Ű�����)������߰�Ĺ涨ʱ��
	 * @param dutyType
	 * @return
	 */
	public DutyTimeDto getDutyTimeByDutyType(int dutyType);
	/**
	 * ��ȡ��Ӧ���ڻ��Ľڵ�����
	 * @param attendMachineCode
	 * @return
	 */
	public AttendMachineNodeDto selectAttendMachineNodeDate(String attendMachineCode);
	/**
	 * ���¿��ڻ��ڵ�ʱ��
	 * @param time
	 * @param machineCode
	 * @return
	 */
	public boolean updateMachineLastTime(String time,String lastIp,String machineCode);
	/**
	 * ɾ���ظ����� �ϰ�ɾ�������°�ɾ������
	 * @return
	 */
	public String deleteDuplData();
	
	
	/**
	 * �ӿ��ڻ���ȡ�ڵ�֮�������
	 * @param AttendMachineNodeDto
	 * @return
	 */
	public String getDataFormAttend(AttendMachineNodeDto machineNode);
	
	
}
