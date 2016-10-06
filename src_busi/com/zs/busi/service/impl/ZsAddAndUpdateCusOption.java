package com.zs.busi.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import com.zs.busi.utils.LogUtil;
import com.zs.common.entity.SearchOptionEntity;
import com.zs.common.util.search.CommonSearch;
import com.zs.common.util.search.TagOption;
import com.zs.common.util.search.TagType;

public class ZsAddAndUpdateCusOption extends CommonSearch {
	Log log=LogUtil.getLog();
	/**
	 * 
	 * @param option
	 * @return
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
				log.info("content"+content);
				to.getText().setValue(content);
				to.getText().setName(option.getName());
				to.getText().setIsnull((option.isNull() == true?"1":"0"));
				to.getText().setTagname(option.getTagname()==null?"":option.getTagname());
				to.getText().setTagtype(TagType.TEXT);
				to.setTagtype(TagType.TEXT);
				to.getText().setSortindex(option.getSort());
				to.getText().setComment(option.getComment()==null?"":option.getComment());
				break;
			case TagType.RADIO:
/*				if(content.equals("")) {
					to.getRadio().setValue(nullStr);
					break;
				}*/
				optionVal = content.split(",");
				to.getRadio().setValue(optionVal);
				to.getRadio().setName(option.getName());
				to.getRadio().setTagname(option.getTagname());
				to.getRadio().setTagtype(TagType.RADIO);
				to.getRadio().setIsnull(option.isNull()==true?"1":"0");
				to.getRadio().setSortindex(option.getSort());
				to.getRadio().setContent(content);
				break;
			case TagType.TEXTAREA:
				to.getTextarea().setName(option.getName());
				to.getTextarea().setComment(option.getComment()==null?"":option.getComment());
				to.getTextarea().setIsnull(option.isNull()==true?"1":"0");
				to.getTextarea().setTagname(option.getTagname()==null?"":option.getTagname());
				to.getTextarea().setSortindex(option.getSort());
				to.getTextarea().setValue(content);
				break;
			case TagType.SELECT:
				if(content.equals("")) {
					to.getSelect().setValue(nullStr);
					log.info("break");
					break;
				}
				if(content.indexOf(",") == -1) {
					if(option.getComment().equals("valided")) {
						if(content.equals("1"))
							content = "有效,无效";
						else
							content = "无效,有效";
					}
				}
				optionVal = content.split(",");
				to.getSelect().setName(option.getName());
				to.getSelect().setIsnull((option.isNull() == true?"1":"0"));
				to.getSelect().setTagname(option.getTagname());
				to.getSelect().setValue(optionVal);
				to.getSelect().setTagtype(TagType.SELECT);
				to.getSelect().setComment(option.getComment()==null?"":option.getComment());
				break;
		}
		return to;
	}
	/**
	 * 
	 * @param to
	 * @return
	 */
	public Map<String,String> jointHtmlStr(TagOption to) {
		StringBuffer htmlStr = new StringBuffer();
		String type = to.getTagtype();
		Map<String,String> optionMap = new HashMap<String,String>(); 
		String isnull = "1";
		String group = "0";
		String select = "";
		String href = "";
		String id = "";
		String name = "";
		String tagname = "";
		switch(to.getTagtype()) {
			case TagType.TEXT:
				isnull = to.getText().getIsnull();
				group = to.getText().getSortindex()+"";
				select = to.getText().getSelect();
				href = to.getText().getTagname();
				id = to.getText().getTagname();
				name = to.getText().getName();
				htmlStr.append("<input type=");
				htmlStr.append(type);
				tagname = to.getText().getTagname();
				htmlStr.append(" placeholder=");
				if(tagname.indexOf("select") != -1)
					htmlStr.append(" 请选择");//用于显示请输入、请选择等字样
				else
					htmlStr.append(" 请输入");//用于显示请输入、请选择等字样
				htmlStr.append(" name=");
				htmlStr.append(to.getText().getComment());
				htmlStr.append(" id=");
				if(tagname.indexOf("select") != -1)
					htmlStr.append("sid"+to.getText().getTagname());
				else
					htmlStr.append(to.getText().getTagname());
				if(to.getText().getTagname().equals("beginTime")) {
					htmlStr.append(" class='kbtn'");
				}
				htmlStr.append(to.getText().getReadonly());
				htmlStr.append(" value=");
				htmlStr.append(to.getText().getValue());
				htmlStr.append(">");
				break;
			case TagType.RADIO:
				log.info("radio");
				name = to.getRadio().getName();
				log.info("name"+name);
				isnull = to.getRadio().getIsnull();
				group = to.getRadio().getSortindex()+"";
				String value = to.getRadio().getContent()==null?"":to.getRadio().getContent();
				log.info("value"+value);
				if(name.equals("性别")) {
					log.info("性别");
					if(!value.equals("男") && !value.equals("女")&& !value.equals("")) {
						htmlStr.append(" <input type='hidden' name='sex' id='sex' value='男'><span class='radio-btn man selected' data-value='0'><i class='customer-icon'> <img id='man' src='/mbem/mcrm/business/images/man.png' flag='1'></i></span><span class='radio-btn woman' data-value='1'><i class='customer-icon'> <img id='woman' src='/mbem/mcrm/business/images/woman_f.png' flag='0'></i></span>");
						
						log.info("if");
					}
					else {
						log.info("else");
						htmlStr.append("<input type=text");
						htmlStr.append(" name="+to.getRadio().getName());
						htmlStr.append(" value=");
						htmlStr.append("'"+value+"'");
						htmlStr.append(" tagtype=");
						htmlStr.append("'"+to.getRadio().getTagtype()+"'");
						htmlStr.append(" tagname=");
						htmlStr.append("'"+to.getRadio().getTagname()+"'");
						htmlStr.append(">");
					}
				}
				else{
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
				}
				break;
			case TagType.TEXTAREA:
				log.info("textarea");
				name = to.getTextarea().getName();
				isnull = to.getTextarea().getIsnull();
				href = to.getTextarea().getTagname();
				id = to.getTextarea().getTagname();
				group = to.getTextarea().getSortindex()+"";
				htmlStr.append("<textarea placeholder=");
				tagname = to.getTextarea().getTagname();
				if(tagname.indexOf("select") != -1)
					htmlStr.append(" 请选择");//用于显示请输入、请选择等字样
				else
					htmlStr.append(" 请输入");//用于显示请输入、请选择等字样
				htmlStr.append(" name=");
				htmlStr.append(to.getTextarea().getComment());
				htmlStr.append(" id=");
				htmlStr.append("sid"+to.getTextarea().getTagname()+">");
				//htmlStr.append(to.getTextarea().getComment());
				htmlStr.append(to.getTextarea().getValue());
				htmlStr.append("</textarea>");
				break;
			case TagType.SELECT:
				log.info("select");
				isnull = to.getSelect().getIsnull();
				name = to.getSelect().getName();
				htmlStr.append("<select name=");
				htmlStr.append("'"+to.getSelect().getComment()+"'");
				log.info(to.getSelect().getComment());
				htmlStr.append(">");
				for(int i=0; i<to.getSelect().getValue().length; i++) {
					htmlStr.append("<option value=");
					htmlStr.append("'"+to.getSelect().getValue()[i]+"'");
					htmlStr.append(" >");
					htmlStr.append(to.getSelect().getValue()[i]+"</option>");
				}
				htmlStr.append("</select>");
				break;
				
		}
		optionMap.put("htmlstr", htmlStr.toString());
		if(isnull.equals("1"))
			optionMap.put("isnull", "");
		else
			optionMap.put("isnull","data-require= '1'");
		optionMap.put("group", group);
		optionMap.put("select", select);
		if(href.indexOf("select") != -1) {
			optionMap.put("href","<a href=#"+href+" data-transition=\"slide\">");
			//optionMap.put("href","<a href=#pagetwo"+" data-transition=\"slide\">");
			optionMap.put("end", "</a>");
		}
		else {
			optionMap.put("href", "");
			optionMap.put("end", "");
		}	
		if(id.indexOf("select") != -1)
			optionMap.put("click", "onclick=showhidediv()");
		else
			optionMap.put("click", "");
		optionMap.put("name", name);
		log.info("html："+htmlStr);
		return optionMap;
	}
}
