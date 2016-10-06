package com.zs.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.busi.utils.LogUtil;
import com.zs.common.dao.IOptionListDao;
import com.zs.common.entity.SearchOptionEntity;
import com.zs.common.service.IOptionListService;
import com.zs.common.util.search.TagOption;
import com.zs.common.util.search.TagType;

@Service
public class OptionListService /*extends ABSOptionListService*/ implements IOptionListService {
	
	Log log=LogUtil.getLog();
	@Autowired
	IOptionListDao optionListDao;
	/**
	 * 
	 * @param option
	 * @return
	 */
	public List<SearchOptionEntity> getOptionListByModule(SearchOptionEntity option) {
		List<SearchOptionEntity> optionList = new ArrayList<SearchOptionEntity>();
		optionList = optionListDao.getOptionListByModule(option);
		return optionList;
	}
	
	
}
