package com.zs.oa.entity;

public class AttendRecordDto {
	
	public String USER_ID;
	public String REGISTER_TYPE;
	public String REGISTER_TIME;
	public String REGISTER_IP;
	public String REMARK;
    public int DUTY_TYPE;
    public int IS_MOBILE_DUTY;
    public int ATTEND_MOBILE_ID;
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getREGISTER_TYPE() {
		return REGISTER_TYPE;
	}
	public void setREGISTER_TYPE(String rEGISTER_TYPE) {
		REGISTER_TYPE = rEGISTER_TYPE;
	}
	public String getREGISTER_TIME() {
		return REGISTER_TIME;
	}
	public void setREGISTER_TIME(String rEGISTER_TIME) {
		REGISTER_TIME = rEGISTER_TIME;
	}
	public String getREGISTER_IP() {
		return REGISTER_IP;
	}
	public void setREGISTER_IP(String rEGISTER_IP) {
		REGISTER_IP = rEGISTER_IP;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public int getDUTY_TYPE() {
		return DUTY_TYPE;
	}
	public void setDUTY_TYPE(int dUTY_TYPE) {
		DUTY_TYPE = dUTY_TYPE;
	}
	public int getIS_MOBILE_DUTY() {
		return IS_MOBILE_DUTY;
	}
	public void setIS_MOBILE_DUTY(int iS_MOBILE_DUTY) {
		IS_MOBILE_DUTY = iS_MOBILE_DUTY;
	}
	public int getATTEND_MOBILE_ID() {
		return ATTEND_MOBILE_ID;
	}
	public void setATTEND_MOBILE_ID(int aTTEND_MOBILE_ID) {
		ATTEND_MOBILE_ID = aTTEND_MOBILE_ID;
	}
	public AttendRecordDto(String uSER_ID, String rEGISTER_TYPE,
			String rEGISTER_TIME, String rEGISTER_IP, String rEMARK,
			int dUTY_TYPE, int iS_MOBILE_DUTY, int aTTEND_MOBILE_ID) {
		super();
		USER_ID = uSER_ID;
		REGISTER_TYPE = rEGISTER_TYPE;
		REGISTER_TIME = rEGISTER_TIME;
		REGISTER_IP = rEGISTER_IP;
		REMARK = rEMARK;
		DUTY_TYPE = dUTY_TYPE;
		IS_MOBILE_DUTY = iS_MOBILE_DUTY;
		ATTEND_MOBILE_ID = aTTEND_MOBILE_ID;
	}
	public AttendRecordDto() {
		super();
	}
    
}

