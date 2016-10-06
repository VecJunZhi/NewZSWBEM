package com.zs.oa.entity;

public class OtherOweAttendDto {
	public String user_id;
	public String maxTime;
	public String name;
	public String work_no;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWork_no() {
		return work_no;
	}
	public void setWork_no(String work_no) {
		this.work_no = work_no;
	}
	public OtherOweAttendDto(String user_id, String maxTime, String name,
			String work_no) {
		super();
		this.user_id = user_id;
		this.maxTime = maxTime;
		this.name = name;
		this.work_no = work_no;
	}
	public OtherOweAttendDto() {
		super();
	}
	

}
