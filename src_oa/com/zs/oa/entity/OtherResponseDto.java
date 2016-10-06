package com.zs.oa.entity;

public class OtherResponseDto {public String time;
	public String user_id;
	public String name;
	public String work_no;
	public String attendIp;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public String getAttendIp() {
		return attendIp;
	}
	public void setAttendIp(String attendIp) {
		this.attendIp = attendIp;
	}
	public OtherResponseDto(String time, String user_id, String name,
			String work_no, String attendIp) {
		super();
		this.time = time;
		this.user_id = user_id;
		this.name = name;
		this.work_no = work_no;
		this.attendIp = attendIp;
	}
	public OtherResponseDto() {
		super();
	}

}
