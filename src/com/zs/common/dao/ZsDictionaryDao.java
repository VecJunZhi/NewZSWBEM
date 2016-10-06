package com.zs.common.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zs.common.entity.ZsDictionaryEntity;
@Repository
public interface ZsDictionaryDao {
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