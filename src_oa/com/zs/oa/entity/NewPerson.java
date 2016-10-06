package com.zs.oa.entity;

public class NewPerson {
	public String user_id;
	public String work_no;
	public String name;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getWork_no() {
		return work_no;
	}
	public void setWork_no(String work_no) {
		this.work_no = work_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public NewPerson(String user_id, String work_no, String name) {
		super();
		this.user_id = user_id;
		this.work_no = work_no;
		this.name = name;
	}
	public NewPerson() {
		super();
	}
	

}
