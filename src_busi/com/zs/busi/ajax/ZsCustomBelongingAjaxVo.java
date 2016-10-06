package com.zs.busi.ajax;

import javax.persistence.Entity;

@Entity
public class ZsCustomBelongingAjaxVo {
	private String cst_type;// 
	private String cst_name;// 
	private String isown ;//
	private String allow_gj ;//
	private String is_show_saler_name ;//
	private String stageName ;//
	private String createdOn ;//
	private String user_name;//
	private String tel ;//
	private String cst_id;//
	private String opp_id ;//
	private String redirect_url;
	public String getCst_type() {
		return cst_type;
	}
	public void setCst_type(String cst_type) {
		this.cst_type = cst_type;
	}
	public String getCst_name() {
		return cst_name;
	}
	public void setCst_name(String cst_name) {
		this.cst_name = cst_name;
	}
	public String getIsown() {
		return isown;
	}
	public void setIsown(String isown) {
		this.isown = isown;
	}
	public String getAllow_gj() {
		return allow_gj;
	}
	public void setAllow_gj(String allow_gj) {
		this.allow_gj = allow_gj;
	}
	public String getIs_show_saler_name() {
		return is_show_saler_name;
	}
	public void setIs_show_saler_name(String is_show_saler_name) {
		this.is_show_saler_name = is_show_saler_name;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCst_id() {
		return cst_id;
	}
	public void setCst_id(String cst_id) {
		this.cst_id = cst_id;
	}
	public String getOpp_id() {
		return opp_id;
	}
	public void setOpp_id(String opp_id) {
		this.opp_id = opp_id;
	}
	public String getRedirect_url() {
		return redirect_url;
	}
	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}
	/**
	 * 新客�?->新增页面
	 * 自己的客�?->查看到客户跟进和详细资料 有个客户id
	 * @param cst_type
	 * @param redirect_url
	 */
	public ZsCustomBelongingAjaxVo(String cst_type, String redirect_url) {
		super();
		this.cst_type = cst_type;
		this.redirect_url = redirect_url;
	}
	/**
	 * 其它置业顾问的客户，但不显示置业顾问 
	 * @param cst_type
	 * @param cst_name
	 * @param is_show_saler_name
	 */
	public ZsCustomBelongingAjaxVo(String cst_type, String cst_name,
			String is_show_saler_name) {
		super();
		this.cst_type = cst_type;
		this.cst_name = cst_name;
		this.is_show_saler_name = is_show_saler_name;
	}
	/**
	 * 其它置业顾问的客户，且显示置业顾问，可联�?
	 * @param cst_type
	 * @param cst_name
	 * @param is_show_saler_name
	 * @param stageName
	 * @param createdOn
	 * @param user_name
	 * @param tel
	 */
	public ZsCustomBelongingAjaxVo(String cst_type, String cst_name,
			String is_show_saler_name, String stageName, String createdOn,
			String user_name, String tel) {
		super();
		this.cst_type = cst_type;
		this.cst_name = cst_name;
		this.is_show_saler_name = is_show_saler_name;
		this.stageName = stageName;
		this.createdOn = createdOn;
		this.user_name = user_name;
		this.tel = tel;
	}
	/**
	 * 不是自己的且可以跟进的公共客�?
	 * @param cst_type
	 * @param cst_name
	 * @param isown
	 * @param allow_gj
	 * @param stageName
	 * @param createdOn
	 * @param user_name
	 * @param cst_id
	 * @param opp_id
	 */
	public ZsCustomBelongingAjaxVo(String cst_type, String cst_name,
			String isown, String allow_gj, String stageName, String createdOn,
			String user_name, String cst_id, String opp_id) {
		super();
		this.cst_type = cst_type;
		this.cst_name = cst_name;
		this.isown = isown;
		this.allow_gj = allow_gj;
		this.stageName = stageName;
		this.createdOn = createdOn;
		this.user_name = user_name;
		this.cst_id = cst_id;
		this.opp_id = opp_id;
	}
	/**
	 * �?0的情�?
	 * @param cst_type
	 * @param cst_name
	 * @param isown
	 * @param allow_gj
	 */
	public ZsCustomBelongingAjaxVo(String cst_type, String cst_name,
			String isown, String allow_gj) {
		super();
		this.cst_type = cst_type;
		this.cst_name = cst_name;
		this.isown = isown;
		this.allow_gj = allow_gj;
	}
	public ZsCustomBelongingAjaxVo(String cst_type) {
		super();
		this.cst_type = cst_type;
	}

	
	
	


}
