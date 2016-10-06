package com.zs.common.util.ding;
/**
 * 发送失败,返回的错误信息
 * errcode:返回码
 * errmsg:对返回码的文本描述内容
 * invaliduser:无效的userid
 * invalidparty:无效的部门id
 * messageId:标识企业消息的id，字符串，最长128个字符
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
