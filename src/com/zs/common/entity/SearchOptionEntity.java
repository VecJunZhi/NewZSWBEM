package com.zs.common.entity;

public class SearchOptionEntity {

	private int id;
	private String name;//ѡ��ǰ��ʾ������
	private String module;//�����ĸ�ģ��
	private String optionType;//ѡ�����ͣ��ı�����ѡ��
	private String content;//ѡ���е����ݣ��磺��ѡ�е���Ů���Զ��ŷָ�
	private String tagname;//��ǩ���ƣ�������װ����������json��
	private boolean isEnable;//�Ƿ����
	private boolean isShow;//�Ƿ���ʾ
	private boolean isNull;//�Ƿ�����Ϊ��
	private boolean isEdit;//�Ƿ�ɱ༭
	private int sort;
	private String comment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	public boolean isShow() {
		return isShow;
	}
	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}
	public boolean isNull() {
		return isNull;
	}
	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
	public boolean isEdit() {
		return isEdit;
	}
	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
