package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsCstAttachTableEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String buGuid;//��λguid
	private String cstGuid;//�ͻ�guid
	private String projGuid;//��Ŀguid
	private String userGuid;//ҵ��Աguid
	private String cstAttachGuid;//�ͻ�����guid
	private String id;
	public String getBuGuid() {
		return buGuid;
	}
	public void setBuGuid(String buGuid) {
		this.buGuid = buGuid;
	}
	public String getCstGuid() {
		return cstGuid;
	}
	public void setCstGuid(String cstGuid) {
		this.cstGuid = cstGuid;
	}
	public String getProjGuid() {
		return projGuid;
	}
	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}
	public String getUserGuid() {
		return userGuid;
	}
	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}
	public String getCstAttachGuid() {
		return cstAttachGuid;
	}
	public void setCstAttachGuid(String cstAttachGuid) {
		this.cstAttachGuid = cstAttachGuid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
