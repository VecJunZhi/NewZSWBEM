package com.zs.common.util.search;

import com.zs.common.entity.SearchOptionEntity;

public abstract class SearchOptionAbs {
	public abstract TagOption generatorHtml(SearchOptionEntity option);
	public abstract String jointHtml(TagType to);
}
