package com.zs.busi.service;

import java.util.List;
import java.util.Map;

public interface ZsDictionaryBusinessService {
	/**
	 * 根据    类别classes 查找zs_词典中的  selectName值（天数值）
	 * @param zsDictionaryEntity
	 * @return
	 */
	public List<Map<String,String>> getZsDictionaryByClassesDao(List classesList);
	/**
	 * 批量更新zs_词典中的 selectName值 
	 * 
	 * 
	 */
	public  int  updateZsDictionaryByClassesDao(Map selectNameMap);
}
