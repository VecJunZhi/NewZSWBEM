package com.zs.common.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zs.common.entity.ZsDictionaryEntity;
@Repository
public interface ZsDictionaryDao {
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