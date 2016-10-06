package com.zs.common.service;

import java.util.List;

import com.zs.common.entity.SearchOptionEntity;

public interface IOptionListService {
	public List<SearchOptionEntity> getOptionListByModule(SearchOptionEntity option);
}
