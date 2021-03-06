package com.zs.common.util.search;

public class RadioEntity {
	
	private String name;
	private String tagname;
	private String tagtype;
	private String isnull;
	private int sortindex;
	private String[] value;
	private String[] checked;
	private String content;
	private String comment;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getTagtype() {
		return tagtype;
	}
	public void setTagtype(String tagtype) {
		this.tagtype = tagtype;
	}
	public String[] getValue() {
		return value;
	}
	public void setValue(String[] value) {
		this.value = value;
	}
	public String[] getChecked() {
		return checked;
	}
	public void setChecked(String[] checked) {
		this.checked = checked;
	}
	public String getIsnull() {
		return isnull;
	}
	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}
	public int getSortindex() {
		return sortindex;
	}
	public void setSortindex(int sortindex) {
		this.sortindex = sortindex;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
