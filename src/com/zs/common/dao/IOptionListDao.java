package com.zs.common.dao;

import java.util.List;

import com.zs.common.entity.SearchOptionEntity;

public interface IOptionListDao {
	public List<SearchOptionEntity> getOptionListByModule(SearchOptionEntity option);
}
