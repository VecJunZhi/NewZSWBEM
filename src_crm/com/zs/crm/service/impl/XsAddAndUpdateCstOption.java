package com.zs.crm.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import com.zs.busi.utils.LogUtil;
import com.zs.common.entity.SearchOptionEntity;
import com.zs.common.util.search.CommonSearch;
import com.zs.common.util.search.TagOption;
import com.zs.common.util.search.TagType;

public class XsAddAndUpdateCstOption extends CommonSearch {
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
				if(content.equals("")) {
					to.getRadio().setValue(nullStr);
					break;
				}
				optionVal = content.split(",");
				to.getRadio().setValue(optionVal);
				to.getRadio().setName(option.getName());
				to.getRadio().setTagname(option.getTagname());
				to.getRadio().setTagtype(TagType.RADIO);
				to.getRadio().setIsnull(option.isNull()==true?"1":"0");
				to.getRadio().setSortindex(option.getSort());
				to.getRadio().setContent(content);
				to.getRadio().setComment(option.getComment()==null?"":option.getComment());
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
				optionVal = content.split(",");
				to.getSelect().setName(option.getName());
				to.getSelect().setIsnull((option.isNull() == true?"1":"0"));
				to.getSelect().setTagname(option.getTagname());
				to.getSelect().setValue(optionVal);
				to.getSelect().setTagtype(TagType.SELECT);
				to.getSelect().setComment(option.getComment()==null?"":option.getComment());
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
				to.getCheckbox().setTagtype(TagType.RADIO);
				to.getCheckbox().setIsnull(option.isNull()==true?"1":"0");
				to.getCheckbox().setComment(option.getComment()==null?"":option.getComment());
				break;
			case TagType.DATE:
				to.getDate().setName(option.getName());
				to.getDate().setTagtype(TagType.DATE);
				to.getDate().setIsnull(option.isNull()==true?"1":"0");
				to.getDate().setComment(option.getComment()==null?"":option.getComment());
				to.getDate().setTagname(option.getTagname());
				to.getDate().setContent(content);
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
		String id = "";
		String name = "";
		String tagname = "";
		String locationId = "";
		String value = "";
		switch(to.getTagtype()) {
			case TagType.TEXT:
				isnull = to.getText().getIsnull();
				id = to.getText().getTagname();
				name = to.getText().getName();
				locationId = to.getText().getComment()+"Option";
				if(name.equals("手机") || name.equals("家庭电话") || name.equals("办公电话") || name.equals("传真")) {
					/*htmlStr.append("<div class='ui-block-a' style='width:55%'>");
					htmlStr.append("<input type="+TagType.TEXT);
					htmlStr.append(" name="+to.getText().getComment());
					htmlStr.append(" palceholder='请输入'");
					htmlStr.append(" id="+id);
					if(isnull.equals("0")){
						htmlStr.append(" required");
					}
					htmlStr.append(" value="+to.getText().getValue());
					htmlStr.append("></div><div class='ui-block-b' style='width:32%'>");
					//htmlStr.append("<input type='button' value='默认' data-mini='true' onclick='setDefault(this)' id="+id+"set");
					//htmlStr.append("></div><div class='ui-block-c' style='width:13%'></div>");
					htmlStr.append("</div><div class='ui-block-c' style='width:13%'><a href='#' class='ui-btn ui-corner-all ui-icon-delete ui-btn-icon-notext' onclick='tagRemove(this)'></a></div>");
					log.info("htmlstr  "+htmlStr.toString());*/
					htmlStr.append("<input type="+to.getTagtype());
					htmlStr.append(" placeholder='请输入'");
					htmlStr.append(" name="+to.getText().getComment());
					htmlStr.append(" id="+to.getText().getTagname());
					htmlStr.append(" style='width:60%;float:left'");
					
					if(isnull.equals("0")){
						htmlStr.append(" required");
					}
					htmlStr.append(" value="+to.getText().getValue());
					if(to.getText().getValue() !=null && !to.getText().getValue().equals("")){
						htmlStr.append(" readonly");//更新客户时不允许更改手机号
					}
					htmlStr.append(">");
				}
				else {
					htmlStr.append("<input type="+to.getTagtype());
					htmlStr.append(" placeholder='请输入'");
					htmlStr.append(" name="+to.getText().getComment());
					htmlStr.append(" id="+to.getText().getTagname());
					
					if(isnull.equals("0")){
						htmlStr.append(" required");
					}
					htmlStr.append(" value="+to.getText().getValue());
					htmlStr.append(">");
				}
				break;
			case TagType.RADIO:
				//log.info("radio");
				name = to.getRadio().getName();
				isnull = to.getRadio().getIsnull();
				locationId = to.getRadio().getComment()+"Option";
				value = to.getRadio().getContent();
				id = to.getRadio().getTagname();
				if(name.equals("性别")) {
					if(!value.equals("男") && !value.equals("女")&& !value.equals("")) {
						log.info("if");
						if(value.indexOf("y") != -1) {
							String val[] = value.split(",");
							for(int i=0; i<val.length; i++){
								if(val[i].indexOf("y")!= -1){
									if(val[i].indexOf("男") != -1) {
										htmlStr.append(" <input type='hidden' name='gender' id='sex' value='男'><span class='radio-btn man selected' data-value='0'><i class='customer-icon'> <img id='man' src='/mbem/mcrm/business/images/man.png' flag='1'></i></span><span class='radio-btn woman' data-value='1'><i class='customer-icon'> <img id='woman' src='/mbem/mcrm/business/images/woman_f.png' flag='0'></i></span>");
									}
									if(val[i].indexOf("女") != -1){
										htmlStr.append(" <input type='hidden' name='gender' id='sex' value='女'><span class='radio-btn man' data-value='0'><i class='customer-icon'> <img id='man' src='/mbem/mcrm/business/images/man_f.png' flag='0'></i></span><span class='radio-btn woman  selected' data-value='1'><i class='customer-icon'> <img id='woman' src='/mbem/mcrm/business/images/woman.png' flag='1'></i></span>");
									}
								}
							}
						}else {
							htmlStr.append(" <input type='hidden' name='gender' id='sex' value='男'><span class='radio-btn man selected' data-value='0'><i class='customer-icon'> <img id='man' src='/mbem/mcrm/business/images/man.png' flag='1'></i></span><span class='radio-btn woman' data-value='1'><i class='customer-icon'> <img id='woman' src='/mbem/mcrm/business/images/woman_f.png' flag='0'></i></span>");
						}
					}
				}
				else{
					if(StringUtils.join(to.getRadio().getValue()).indexOf(":") != -1){
						for(int i=0; i<to.getRadio().getValue().length; i++) {
							value = to.getRadio().getValue()[i];
							if(value.indexOf(":") != -1) {
								String []subOption;
								htmlStr.append("<div class='select-head erjih1'>");
								htmlStr.append(value.substring(0, value.indexOf(":")));
								htmlStr.append("</div><div id="+"sub"+id+i);
								htmlStr.append(" class='erji'>");
								subOption = value.substring(value.indexOf(":")+1).split("、");
								for(int j=0; j<subOption.length; j++) {
									value = subOption[j];
									if(value.indexOf("y")!= -1) {
										htmlStr.append("<label class='btn active'>");
										htmlStr.append("<input type="+to.getTagtype());
										htmlStr.append(" name="+to.getRadio().getComment());
										htmlStr.append(" id="+"sub"+to.getRadio().getTagname()+i+"a"+j);
										if(i == 0) {
											if(isnull.equals("0")) {
												htmlStr.append(" class='required'");
											}
										}
										htmlStr.append(" checked ");
										htmlStr.append(" value="+value.substring(1)+">");
										htmlStr.append(value.substring(1)+"</label>");
									} else {
										htmlStr.append("<label class='btn'>");
										htmlStr.append("<input type="+to.getTagtype());
										htmlStr.append(" name="+to.getRadio().getComment());
										htmlStr.append(" id="+"sub"+to.getRadio().getTagname()+i+"a"+j);
										if(i == 0) {
											if(isnull.equals("0")) {
												htmlStr.append(" class='required'");
											}
										}
										htmlStr.append(" value="+value+">");
										htmlStr.append(value+"</label>");
									}
								}
								htmlStr.append("</div>");
							}else {
								
								if(value.indexOf("y") != -1) {
									htmlStr.append("<div class='select-head erjih1'>");
									htmlStr.append(value.substring(1));
									htmlStr.append("</div><div id="+"sub"+id+i);
									htmlStr.append(" class='erji'>");
									htmlStr.append("<label class='btn active'>");
									htmlStr.append("<input type="+to.getTagtype());
									htmlStr.append(" name="+to.getRadio().getComment());
									htmlStr.append(" id="+to.getRadio().getTagname()+i+"a");
									if(i == 0) {
										if(isnull.equals("0")) {
											htmlStr.append(" class='required'");
										}
									}
									htmlStr.append(" checked ");
									htmlStr.append(" value="+value.substring(1)+">");
									htmlStr.append(value.substring(1)+"</label>");
								}else {
									htmlStr.append("<div class='select-head erjih1'>");
									htmlStr.append(value);
									htmlStr.append("</div><div id="+"sub"+id+i);
									htmlStr.append(" class='erji'>");
									htmlStr.append("<label class='btn'>");
									htmlStr.append("<input type="+to.getTagtype());
									htmlStr.append(" name="+to.getRadio().getComment());
									htmlStr.append(" id="+"sub"+to.getRadio().getTagname()+i+"a");
									if(i == 0) {
										if(isnull.equals("0")) {
											htmlStr.append(" class='required'");
										}
									}
									htmlStr.append(" value="+value+">");
									htmlStr.append(value+"</label>");
								}
								htmlStr.append("</div>");
							}
						}
					}
					else {
						for(int i=0; i<to.getRadio().getValue().length; i++) {
							value = to.getRadio().getValue()[i];
							if(value.indexOf("y") != -1) {
								htmlStr.append("<label class='btn active'>");
								htmlStr.append("<input type="+to.getTagtype());
								htmlStr.append(" name="+to.getRadio().getComment());
								//htmlStr.append(" id="+to.getRadio().getTagname());
								if(i == 0) {
									if(isnull.equals("0")) {
										htmlStr.append(" class='required'");
									}
								}
								htmlStr.append(" checked ");
								if(name.equals("是否无效")) {
									htmlStr.append(" onclick='wuxiaoRe()'");
								}
								htmlStr.append(" value="+value.substring(1)+">");
								htmlStr.append(value.substring(1)+"</label>");
							} else {
								htmlStr.append("<label class='btn'>");
								htmlStr.append("<input type="+to.getTagtype());
								htmlStr.append(" name="+to.getRadio().getComment());
								//htmlStr.append(" id="+to.getRadio().getTagname());
								if(i == 0) {
									if(isnull.equals("0")) {
										htmlStr.append(" class='required'");
									}
								}
								if(name.equals("是否无效")) {
									htmlStr.append(" onclick='wuxiaoRe()'");
								}
								htmlStr.append(" value="+value+">");
								htmlStr.append(value+"</label>");
								}
							
							}	
						}
					}
				break;
			case TagType.CHECKBOX:
				name = to.getCheckbox().getName();
				isnull = to.getCheckbox().getIsnull();
				locationId = to.getCheckbox().getComment()+"Option";
				id = to.getCheckbox().getTagname();
				for(int i=0; i<to.getCheckbox().getValue().length; i++) {
					value = to.getCheckbox().getValue()[i];
					if(value.indexOf("y") != -1) {
						htmlStr.append("<label class='btn active'>");
						htmlStr.append("<input type="+to.getTagtype());
						htmlStr.append(" name="+to.getCheckbox().getComment());
						//htmlStr.append(" id="+to.getCheckbox().getTagname()+"sub"+i);
						if(i == 0) {
							if(isnull.equals("0")) {
								htmlStr.append(" class='required'");
							}
						}
						htmlStr.append(" checked ");
						htmlStr.append(" value="+value.substring(1)+">");
						htmlStr.append(value.substring(1)+"</label>");
					}
					else {
						htmlStr.append("<label class='btn'>");
						htmlStr.append("<input type="+to.getTagtype());
						htmlStr.append(" name="+to.getCheckbox().getComment());
						//htmlStr.append(" id="+to.getCheckbox().getTagname()+"sub"+i);
						if(i == 0) {
							if(isnull.equals("0")) {
								htmlStr.append(" class='required'");
							}
						}
						htmlStr.append(" value="+value+">");
						htmlStr.append(value+"</label>");
					}
				}
				break;
			case TagType.TEXTAREA:
				log.info("textarea");
				name = to.getTextarea().getName();
				isnull = to.getTextarea().getIsnull();
				id = to.getTextarea().getTagname();
				htmlStr.append("<textarea placeholder=");
				tagname = to.getTextarea().getTagname();
				if(tagname.indexOf("select") != -1)
					htmlStr.append(" 请选择");//用于显示请输入、请选择等字样
				else
					htmlStr.append(" 请输入");//用于显示请输入、请选择等字样
				htmlStr.append(" name=");
				htmlStr.append(to.getTextarea().getComment());
				htmlStr.append(" id=");
				htmlStr.append(to.getTextarea().getTagname());
				//htmlStr.append(to.getTextarea().getComment());
				if(isnull.equals("0")){
					htmlStr.append(" required");
				}
				htmlStr.append(">");
				htmlStr.append(to.getTextarea().getValue());
				htmlStr.append("</textarea>");
				break;
			case TagType.SELECT:
				log.info("select");
				isnull = to.getSelect().getIsnull();
				name = to.getSelect().getName();
				htmlStr.append("<select name=");
				htmlStr.append("'"+to.getSelect().getComment()+"'");
				htmlStr.append(" id="+to.getSelect().getTagname());//2016.4.5
				log.info(to.getSelect().getComment());
				htmlStr.append(">");
				for(int i=0; i<to.getSelect().getValue().length; i++) {
					htmlStr.append("<option value=");
					htmlStr.append("'"+to.getSelect().getValue()[i]+"'");
					htmlStr.append(" >");
					htmlStr.append(to.getSelect().getValue()[i]+"</option>");
				}
				if(to.getSelect().getValue().length == 1){
					if(to.getSelect().getValue()[0].equals("无效")) {
						htmlStr.append("<option value=");
						htmlStr.append("'"+"有效"+"'");
						htmlStr.append(" >");
						htmlStr.append("有效"+"</option>");
					} else if(to.getSelect().getValue()[0].equals("有效")) {
						htmlStr.append("<option value=");
						htmlStr.append("'"+"无效"+"'");
						htmlStr.append(" >");
						htmlStr.append("无效"+"</option>");
					}
				}
				htmlStr.append("</select>");
				break;
			case TagType.DATE:
				log.info("date");
				isnull = to.getDate().getIsnull();
				name = to.getDate().getName();
				htmlStr.append("<input type="+TagType.DATE);
				htmlStr.append(" name="+to.getDate().getComment());
				htmlStr.append(" id="+to.getDate().getTagname());
				if(isnull.equals("0")){
					htmlStr.append(" required");
				}
				htmlStr.append(" value="+to.getDate().getContent());
				htmlStr.append(">");
				
		}
		optionMap.put("htmlStr", htmlStr.toString());
		optionMap.put("isNull", isnull);//是否必填
		optionMap.put("tagType", to.getTagtype());
		optionMap.put("name", name);
		optionMap.put("locationId", locationId);//用于快速定位
		optionMap.put("id", id);
		//log.info("html："+htmlStr);
		return optionMap;
	}
}
