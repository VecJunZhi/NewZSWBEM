package com.zs.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zs.common.entity.ZsDictionaryEntity;

public interface ZsDictionaryService {
	/**
	 * ����    ���classes ����zs_�ʵ��е�  selectNameֵ������ֵ��
	 * @param zsDictionaryEntity
	 * @return
	 */
	public List<Map<String,String>> getZsDictionaryByClassesDao(List classesList);
	/**
	 * ��������zs_�ʵ��е� selectNameֵ 
	 * 
	 * 
	 */
	public  int  updateZsDictionaryByClassesDao(Map selectNameMap);
}
