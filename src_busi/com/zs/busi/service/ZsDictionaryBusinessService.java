package com.zs.busi.service;

import java.util.List;
import java.util.Map;

public interface ZsDictionaryBusinessService {
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
