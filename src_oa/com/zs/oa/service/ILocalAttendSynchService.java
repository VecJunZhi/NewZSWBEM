package com.zs.oa.service;

import java.util.List;

import com.zs.oa.entity.AttendMachineNodeDto;
import com.zs.oa.entity.EmployDto;

public interface ILocalAttendSynchService {
	
	/**
	 * ͬ�����ؿ��ڻ�����
	 * @return true:success ,false:failure
	 */
	public boolean syncLocalAttend();
	
	
	/**
	 * ��ȡ���ڻ��Ľڵ�����
	 * @param attendMachineCode
	 * @return
	 */
	public AttendMachineNodeDto selectAttendMachineNodeDate(String attendMachineCode);
	
	
	/**
	 * ���������ݷ�װΪ����-->list����
	 * @param answer
	 * @return
	 */
	public  List<EmployDto> encapsulateDataToList(String answer);
}
