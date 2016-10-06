package com.zs.oa.entity;

public class AttendAbnormalDto {
	
	public String user_id;
	public String register_time;
	public String typestr;
	public String remark;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getRegister_time() {
		return register_time;
	}
	public void setRegister_time(String register_time) {
		this.register_time = register_time;
	}
	public String getTypestr() {
		return typestr;
	}
	public void setTypestr(String typestr) {
		this.typestr = typestr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public AttendAbnormalDto(String user_id, String register_time,
			String typestr, String remark) {
		super();
		this.user_id = user_id;
		this.register_time = register_time;
		this.typestr = typestr;
		this.remark = remark;
	}
	public AttendAbnormalDto() {
		super();
	}

	
}
