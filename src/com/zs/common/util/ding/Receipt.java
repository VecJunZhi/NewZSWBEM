package com.zs.common.util.ding;
/**
 * ����ʧ��,���صĴ�����Ϣ
 * errcode:������
 * errmsg:�Է�������ı���������
 * invaliduser:��Ч��userid
 * invalidparty:��Ч�Ĳ���id
 * messageId:��ʶ��ҵ��Ϣ��id���ַ������128���ַ�
 */
public class Receipt {
	String errcode;
	String errmsg;
	String invaliduser;
	String invalidparty;
	String messageId;
	public String getInvaliduser() {
		return invaliduser;
	}
	public void setInvaliduser(String invaliduser) {
		this.invaliduser = invaliduser;
	}
	public String getInvalidparty() {
		return invalidparty;
	}
	public void setInvalidparty(String invalidparty) {
		this.invalidparty = invalidparty;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}
