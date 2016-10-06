package com.zs.busi.dao;

import java.util.List;
import java.util.Map;

import com.zs.common.entity.ZsDictionaryEntity;

public interface ZsDictionaryBusinessDao {
	/**
	 * 根据    类别classes 查找zs_词典中的  selectName值（天数值）
	 * @param classes
	 * @return Map<String,ZsDictionaryEntity> key:classes   values:ZsDictionaryEntity
	 * List<ZsdictionaryEntity>
	 */
	public List<Map<String,String>> getZsDictionaryByClasses(List<String> classesList);
    /**
	 * 批量更新zs_词典中的 selectName值 
	 * 
	 * 
	 */
	public  int  updateZsDictionaryByClasses(Map selectNameMap);	
	public int insert(ZsDictionaryEntity record);
	public int insertSelective(ZsDictionaryEntity record);
}
