package com.zs.crm.service.impl;

import org.apache.commons.logging.Log;

import com.zs.busi.utils.LogUtil;
import com.zs.common.entity.SearchOptionEntity;
import com.zs.common.util.search.CommonSearch;
import com.zs.common.util.search.TagOption;
import com.zs.common.util.search.TagType;

public class XsFollowSearch extends CommonSearch{
	Log log=LogUtil.getLog();
	/**
	 * 
	 */
	public TagOption generateHtmlEntity(SearchOptionEntity option) {
		TagOption to = new TagOption();
		String type = option.getOptionType();
		String content = (option.getContent()==null?"":option.getContent());
		to.setTagtype(type);
		String []optionVal;
		String []nullStr={};
		switch(type) {
			case TagType.TEXT:
				String read = ((option.isEdit() == true)? "":"readonly");
				to.getText().setReadonly(read);
				to.getText().setValue(content);
				to.getText().setName(option.getName());
				to.getText().setTagname(option.getTagname());
				to.getText().setTagtype(TagType.TEXT);
				to.setTagtype(TagType.TEXT);
				break;
			case TagType.RADIO:
				if(content.equals("")) {
					to.getRadio().setValue(nullStr);
					break;
				}
				optionVal = content.split(",");
				to.getRadio().setValue(optionVal);
				to.getRadio().setName(option.getName());
				to.getRadio().setTagname(option.getTagname());
				to.getRadio().setTagtype(TagType.RADIO);
				log.info(optionVal[0]);
				log.info(optionVal[1]);
				break;
			case TagType.CHECKBOX:
				if(content.equals("")) {
					to.getCheckbox().setValue(nullStr);
					break;
				}
				optionVal = content.split(",");
				to.getCheckbox().setValue(optionVal);
				to.getCheckbox().setName(option.getName());
				to.getCheckbox().setTagname(option.getTagname());
				to.getCheckbox().setTagtype(TagType.CHECKBOX);
				log.info(optionVal[0]);
				log.info(optionVal[1]);
				break;
			case TagType.SELECT:
				if(content.equals("")) {
					to.getSelect().setValue(nullStr);
					break;
				}
				optionVal = content.split(",");
				to.getSelect().setName(option.getName());
				to.getSelect().setTagname(option.getTagname());
				to.getSelect().setValue(optionVal);
				to.getSelect().setTagtype(TagType.SELECT);
				break;
			case TagType.BUTTON:
				to.getButton().setName(option.getName());
				to.getButton().setTagname(option.getTagname());
				to.getButton().setValue(option.getContent());
				to.getButton().setTagtype(TagType.BUTTON);
				break;
		}
		return to;
	}
	/**
	 * 
	 * @param to
	 * @return
	 */
	public String jointHtmlStr(TagOption to) {
		StringBuffer htmlStr = new StringBuffer();
		String type = to.getTagtype();
		switch(to.getTagtype()) {
			case TagType.TEXT:
				htmlStr.append("<span>");
				htmlStr.append(to.getText().getName());
				htmlStr.append(":</span>");
				htmlStr.append("<input type=");
				htmlStr.append("'"+type+"'");
/*				htmlStr.append(" value=");
				htmlStr.append("'"+to.getText().getValue()+"'");*/
				htmlStr.append(" tagtype=");
				htmlStr.append("'"+to.getText().getTagtype()+"'");
				htmlStr.append(" tagname=");
				htmlStr.append("'"+to.getText().getTagname()+"'");
				htmlStr.append(" id=");
				htmlStr.append("'"+to.getText().getTagname()+"'");
				//htmlStr.append(" readonly=");
				htmlStr.append(" placeholder=");
				htmlStr.append("'"+to.getText().getValue()+"'");//用于显示请输入、请选择等字样
				htmlStr.append(to.getText().getReadonly());
				htmlStr.append(">");
				break;
			case TagType.RADIO:
				log.info("radio");
				htmlStr.append("<span>");
				htmlStr.append(to.getRadio().getName());
				htmlStr.append(":</span>");
				for(int i=0; i<to.getRadio().getValue().length; i++) {
					htmlStr.append("<input type="+type);
					htmlStr.append(" name="+to.getRadio().getName());
					htmlStr.append(" value=");
					htmlStr.append("'"+to.getRadio().getValue()[i]+"'");
					htmlStr.append(" tagtype=");
					htmlStr.append("'"+to.getRadio().getTagtype()+"'");
					htmlStr.append(" tagname=");
					htmlStr.append("'"+to.getRadio().getTagname()+"'");
					htmlStr.append(">"+to.getRadio().getValue()[i]);
				}
				break;
			case TagType.CHECKBOX:
				log.info("checkbox");
				htmlStr.append("<span>");
				htmlStr.append(to.getCheckbox().getName());
				htmlStr.append(":</span>");
				for(int i=0; i<to.getCheckbox().getValue().length; i++) {
					htmlStr.append("<input type="+type);
					htmlStr.append(" name="+to.getCheckbox().getName());
					htmlStr.append(" value=");
					htmlStr.append("'"+to.getCheckbox().getValue()[i]+"'");
					htmlStr.append(" tagtype=");
					htmlStr.append("'"+to.getCheckbox().getTagtype()+"'");
					htmlStr.append(" tagname=");
					htmlStr.append("'"+to.getCheckbox().getTagname()+"'");
					htmlStr.append(">"+to.getCheckbox().getValue()[i]);
				}
				break;
			case TagType.SELECT:
				log.info("select");
				htmlStr.append("<span>");
				htmlStr.append(to.getSelect().getName());
				htmlStr.append(":</span>");
				htmlStr.append("<select name=");
				htmlStr.append("'"+to.getSelect().getName()+"'");
				htmlStr.append(" tagname=");
				htmlStr.append("'"+to.getSelect().getTagname()+"'");
				htmlStr.append(" tagtype=");
				htmlStr.append("'"+to.getSelect().getTagtype()+"'");
				htmlStr.append(">");
				for(int i=0; i<to.getSelect().getValue().length; i++) {
					htmlStr.append("<option value=");
					htmlStr.append("'"+to.getSelect().getValue()[i]+"'");
					htmlStr.append(" >");
					htmlStr.append(to.getSelect().getValue()[i]+"</option>");
				}
				htmlStr.append("</select>");
				break;
			case TagType.BUTTON:
				log.info("button");
				htmlStr.append("<input type=");
				htmlStr.append("'"+TagType.BUTTON+"'");
				htmlStr.append(" id=");
				htmlStr.append("'"+to.getButton().getTagname()+"'");
				htmlStr.append(" name=");
				htmlStr.append("'"+to.getButton().getName()+"'");
				htmlStr.append(" value=");
				htmlStr.append("'"+to.getButton().getValue()+"'");
				htmlStr.append(" tagtype=");
				htmlStr.append("'"+to.getButton().getTagtype()+"'");
				htmlStr.append(">");
				break;
				
		}
		log.info("html："+htmlStr);
		return htmlStr.toString();
	}
}
