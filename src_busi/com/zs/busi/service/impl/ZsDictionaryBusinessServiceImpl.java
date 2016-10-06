package com.zs.busi.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.busi.service.ZsDictionaryBusinessService;
import com.zs.busi.utils.LogUtil;
import com.zs.common.dao.ZsDictionaryDao;
@Service
public class ZsDictionaryBusinessServiceImpl implements ZsDictionaryBusinessService{
	@Autowired
	ZsDictionaryDao sDictionaryDao;			
	Log log=LogUtil.getLog();
	/**
	 * 根据    类别classes 查找zs_词典中的  selectName值（天数值）
	 * @param zsDictionaryEntity
	 * @return
	 */		
	public List<Map<String,String>> getZsDictionaryByClassesDao(List classesList){		
		List<Map<String,String>>  zsDictionaryEntityList=sDictionaryDao.getZsDictionaryByClasses(classesList);	
		return zsDictionaryEntityList;
	};
	/**
	 * 批量更新zs_词典中的 selectName值 
	 * 
	 * 
	 */
	public  int  updateZsDictionaryByClassesDao(Map selectNameMap){		
		if(true){			
			sDictionaryDao.updateZsDictionaryByClasses(selectNameMap);			
			return 1;		
		}else{			
			return 0;		
		}	
	}
}
