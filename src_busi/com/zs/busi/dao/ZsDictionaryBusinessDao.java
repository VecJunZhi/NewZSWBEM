package com.zs.busi.dao;

import java.util.List;
import java.util.Map;

import com.zs.common.entity.ZsDictionaryEntity;

public interface ZsDictionaryBusinessDao {
	/**
	 * ����    ���classes ����zs_�ʵ��е�  selectNameֵ������ֵ��
	 * @param classes
	 * @return Map<String,ZsDictionaryEntity> key:classes   values:ZsDictionaryEntity
	 * List<ZsdictionaryEntity>
	 */
	public List<Map<String,String>> getZsDictionaryByClasses(List<String> classesList);
    /**
	 * ��������zs_�ʵ��е� selectNameֵ 
	 * 
	 * 
	 */
	public  int  updateZsDictionaryByClasses(Map selectNameMap);	
	public int insert(ZsDictionaryEntity record);
	public int insertSelective(ZsDictionaryEntity record);
}
