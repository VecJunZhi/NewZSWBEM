package com.zs.oa.service;

import java.util.List;

import com.zs.oa.entity.AttendMachineNodeDto;
import com.zs.oa.entity.EmployDto;

public interface ILocalAttendSynchService {
	
	/**
	 * 同步本地考勤机数据
	 * @return true:success ,false:failure
	 */
	public boolean syncLocalAttend();
	
	
	/**
	 * 获取考勤机的节点数据
	 * @param attendMachineCode
	 * @return
	 */
	public AttendMachineNodeDto selectAttendMachineNodeDate(String attendMachineCode);
	
	
	/**
	 * 将考勤数据封装为对象-->list集合
	 * @param answer
	 * @return
	 */
	public  List<EmployDto> encapsulateDataToList(String answer);
}
