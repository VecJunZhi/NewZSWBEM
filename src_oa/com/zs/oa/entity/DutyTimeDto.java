package com.zs.oa.entity;

public class DutyTimeDto {
	private String duty_time1;
	private String duty_time2;
	private String duty_time3;
	private String duty_time4;
	public String getDuty_time1() {
		return duty_time1;
	}
	public void setDuty_time1(String duty_time1) {
		this.duty_time1 = duty_time1;
	}
	public String getDuty_time2() {
		return duty_time2;
	}
	public void setDuty_time2(String duty_time2) {
		this.duty_time2 = duty_time2;
	}
	public String getDuty_time3() {
		return duty_time3;
	}
	public void setDuty_time3(String duty_time3) {
		this.duty_time3 = duty_time3;
	}
	public String getDuty_time4() {
		return duty_time4;
	}
	public void setDuty_time4(String duty_time4) {
		this.duty_time4 = duty_time4;
	}
	public DutyTimeDto(String duty_time1, String duty_time2, String duty_time3,
			String duty_time4) {
		super();
		this.duty_time1 = duty_time1;
		this.duty_time2 = duty_time2;
		this.duty_time3 = duty_time3;
		this.duty_time4 = duty_time4;
	}
	public DutyTimeDto() {
		super();
	}
	
}
