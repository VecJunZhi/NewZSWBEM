package com.zs.oa.entity;
/**
 * OA�û������
 * uid Ψһ����ID
 * userId �û���
 * userName �û���ʵ����
 * userNameIndex �û���������
 * byName ��¼��
 * useingKey ʹ��USB KEY��¼
 * useingFinger ʹ��ָ����֤
 * password �û�����
 * keySn ��USB Key���к�
 * secureKeySn ��̬���뿨��
 * userPriv ��ɫ���
 * userPrivNo ��ɫ�����
 * userPrivName ��ɫ����
 * postPriv ����Χ
 * postDept ����Χָ������
 * deptId ����ID
 * deptIdOther ��������
 * sex �Ա�
 * birthday ����
 * isLunar �Ƿ�ũ��(1-��,0-��)
 * telNoDept �����绰
 * faxNoDept ��������
 * addHome ��ͥסַ
 * postNoHome ��ͥ�ʱ�
 * telNoHome ��ͥ�绰
 * mobilNo �ֻ���
 * bpNo BP����
 * email �����ʼ���ַ
 * oicqNo QQ����
 * icqNo ICQ����
 * msn MSN����
 * avatar �Զ���Сͷ��
 * callSound ������ʾ��
 * lastVisitTime �ϴη���ϵͳ��ʱ��
 * smsOn �������Ѵ��ڵ�����ʽ(1-�Զ�,0-�ֶ�)
 * menuType ��¼ģʽ(1-�ڱ����ڴ�OA,2-���´��ڴ�OA��ʾ������,3-���´��ڴ�)
 * lastPassTime �ϴ��޸������ʱ��
 * theme ��������
 * shortCut ��ݲ˵�ID��
 * portal �Ż�ID��
 * panel ��¼����ʾ��������(1-����,2-��֯,3-����,4-����)
 * onLine ����ʱ��
 * onStatus ��¼����״̬(1-����,2-æµ,3-�뿪)
 * attendStatus ��λ״̬(1-����,2-���,3-���)
 * mobilNoHidden �ֻ������Ƿ񹫿�(0-����,1-������)
 * mytableLeft ��������ģ��
 * mytableRight �Ҳ������ģ��
 * userPrivOther ������ɫ���봮
 * userNo �û������
 * notLogin ��ֹ��¼OAϵͳ(1-��ֹ,0-����ֹ)
 * notViewUser ��ֹ�鿴�û��б�(1-��ֹ,0-����ֹ)
 * notViewTable ��ֹ��ʾ����(1-��ֹ,0-����ֹ)
 * notSearch ��ֹOA����(1-��ֹ,0-����ֹ)
 * bkGround ���汳��ͼƬ
 * bindIp ��IP��ַ
 * lastVisitIp �ϴη���ϵͳ��IP
 * menuImage �˵�ͼ��(0-ÿ���˵�ʹ�ò�ͬͼ��,1-����ʾ�˵�ͼ��)
 * weatherCity 
 * showRss �Ƿ���ʾ������Ѷ(1-��ʾ,0-����ʾ)
 * myRss ������Ѷ�е���ַ����
 * remark ��ע
 * menuExpand Ĭ��չ���˵�
 * myStatus ����ǩ��
 * limitLogin ��¼��������
 * photo �û���ƬͼƬ
 * imRange ��ʱͨѶʹ��Ȩ��(1-����ʹ��,2-��ֹʹ��)
 * leaveTime ����ʱ��
 * secretLevel �ʼ��ܼ�����(1-������,2-����[һ��],3-����[��Ҫ],4-����[�ǳ���Ҫ])
 * userPara �û�����
 * notMobileLogin ��ֹ��¼OAϵͳ�ֻ��ͻ���(1-��ֹ,0-����ֹ)
 * manageModuleType �û������ģ��
 * userPrivType �û���ɫ����
 * userManageOrgs �û�����ķ�֧����ID��
 */
public class OaUserEntity {
	public int uid;
	public String userId;
	public String userName;
	public String userNameIndex;
	public String byName;
	public String useingKey;
	public String useingFinger;
	public String password;
	public String keySn;
	public String secureKeySn;
	public int userPriv;
	public int userPrivNo;
	public String userPrivName;
	public String postPriv;
	public String postDept;
	public int deptId;
	public String deptIdOther;
	public String sex;
	public String birthday;
	public String isLunar;
	public String telNoDept;
	public String faxNoDept;
	public String addHome;
	public String postNoHome;
	public String telNoHome;
	public String mobilNo;
	public String bpNo;
	public String email;
	public String oicqNo;
	public String icqNo;
	public String msn;
	public String avatar;
	public String callSound;
	public String lastVisitTime;
	public String smsOn;
	public String menuType;
	public String lastPassTime;
	public int theme;
	public String shortCut;
	public String portal;
	public String panel;
	public int onLine;
	public String onStatus;
	public String attendStatus;
	public String mobilNoHidden;
	public String mytableLeft;
	public String mytableRight;
	public String userPrivOther;
	public int userNo;
	public int notLogin;
	public String notViewUser;
	public String notViewTable;
	public String notSearch;
	public String bkGround;
	public String bindIp;
	public String lastVisitIp;
	public String menuImage;
	public String weatherCity;
	public String showRss;
	public String myRss;
	public String remark;
	public String menuExpand;
	public String myStatus;
	public String limitLogin;
	public String photo;
	
	
	public int imRange;
	public String leaveTime;
	public int secretLevel;
	public String userPara;
	
	
	public int notMobileLogin;
	public String manageModuleType;
	public int userPrivType;
	public String userManageOrgs;
	
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNameIndex() {
		return userNameIndex;
	}
	public void setUserNameIndex(String userNameIndex) {
		this.userNameIndex = userNameIndex;
	}
	public String getByName() {
		return byName;
	}
	public void setByName(String byName) {
		this.byName = byName;
	}
	public String getUseingKey() {
		return useingKey;
	}
	public void setUseingKey(String useingKey) {
		this.useingKey = useingKey;
	}
	public String getUseingFinger() {
		return useingFinger;
	}
	public void setUseingFinger(String useingFinger) {
		this.useingFinger = useingFinger;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getKeySn() {
		return keySn;
	}
	public void setKeySn(String keySn) {
		this.keySn = keySn;
	}
	public String getSecureKeySn() {
		return secureKeySn;
	}
	public void setSecureKeySn(String secureKeySn) {
		this.secureKeySn = secureKeySn;
	}
	public int getUserPriv() {
		return userPriv;
	}
	public void setUserPriv(int userPriv) {
		this.userPriv = userPriv;
	}
	public int getUserPrivNo() {
		return userPrivNo;
	}
	public void setUserPrivNo(int userPrivNo) {
		this.userPrivNo = userPrivNo;
	}
	public String getUserPrivName() {
		return userPrivName;
	}
	public void setUserPrivName(String userPrivName) {
		this.userPrivName = userPrivName;
	}
	public String getPostPriv() {
		return postPriv;
	}
	public void setPostPriv(String postPriv) {
		this.postPriv = postPriv;
	}
	public String getPostDept() {
		return postDept;
	}
	public void setPostDept(String postDept) {
		this.postDept = postDept;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptIdOther() {
		return deptIdOther;
	}
	public void setDeptIdOther(String deptIdOther) {
		this.deptIdOther = deptIdOther;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getIsLunar() {
		return isLunar;
	}
	public void setIsLunar(String isLunar) {
		this.isLunar = isLunar;
	}
	public String getTelNoDept() {
		return telNoDept;
	}
	public void setTelNoDept(String telNoDept) {
		this.telNoDept = telNoDept;
	}
	public String getFaxNoDept() {
		return faxNoDept;
	}
	public void setFaxNoDept(String faxNoDept) {
		this.faxNoDept = faxNoDept;
	}
	public String getAddHome() {
		return addHome;
	}
	public void setAddHome(String addHome) {
		this.addHome = addHome;
	}
	public String getPostNoHome() {
		return postNoHome;
	}
	public void setPostNoHome(String postNoHome) {
		this.postNoHome = postNoHome;
	}
	public String getTelNoHome() {
		return telNoHome;
	}
	public void setTelNoHome(String telNoHome) {
		this.telNoHome = telNoHome;
	}
	public String getMobilNo() {
		return mobilNo;
	}
	public void setMobilNo(String mobilNo) {
		this.mobilNo = mobilNo;
	}
	public String getBpNo() {
		return bpNo;
	}
	public void setBpNo(String bpNo) {
		this.bpNo = bpNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOicqNo() {
		return oicqNo;
	}
	public void setOicqNo(String oicqNo) {
		this.oicqNo = oicqNo;
	}
	public String getIcqNo() {
		return icqNo;
	}
	public void setIcqNo(String icqNo) {
		this.icqNo = icqNo;
	}
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getCallSound() {
		return callSound;
	}
	public void setCallSound(String callSound) {
		this.callSound = callSound;
	}
	public String getLastVisitTime() {
		return lastVisitTime;
	}
	public void setLastVisitTime(String lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}
	public String getSmsOn() {
		return smsOn;
	}
	public void setSmsOn(String smsOn) {
		this.smsOn = smsOn;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getLastPassTime() {
		return lastPassTime;
	}
	public void setLastPassTime(String lastPassTime) {
		this.lastPassTime = lastPassTime;
	}
	public int getTheme() {
		return theme;
	}
	public void setTheme(int theme) {
		this.theme = theme;
	}
	public String getShortCut() {
		return shortCut;
	}
	public void setShortCut(String shortCut) {
		this.shortCut = shortCut;
	}
	public String getPortal() {
		return portal;
	}
	public void setPortal(String portal) {
		this.portal = portal;
	}
	public String getPanel() {
		return panel;
	}
	public void setPanel(String panel) {
		this.panel = panel;
	}
	public int getOnLine() {
		return onLine;
	}
	public void setOnLine(int onLine) {
		this.onLine = onLine;
	}
	public String getOnStatus() {
		return onStatus;
	}
	public void setOnStatus(String onStatus) {
		this.onStatus = onStatus;
	}
	public String getAttendStatus() {
		return attendStatus;
	}
	public void setAttendStatus(String attendStatus) {
		this.attendStatus = attendStatus;
	}
	public String getMobilNoHidden() {
		return mobilNoHidden;
	}
	public void setMobilNoHidden(String mobilNoHidden) {
		this.mobilNoHidden = mobilNoHidden;
	}
	public String getMytableLeft() {
		return mytableLeft;
	}
	public void setMytableLeft(String mytableLeft) {
		this.mytableLeft = mytableLeft;
	}
	public String getMytableRight() {
		return mytableRight;
	}
	public void setMytableRight(String mytableRight) {
		this.mytableRight = mytableRight;
	}
	public String getUserPrivOther() {
		return userPrivOther;
	}
	public void setUserPrivOther(String userPrivOther) {
		this.userPrivOther = userPrivOther;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getNotLogin() {
		return notLogin;
	}
	public void setNotLogin(int notLogin) {
		this.notLogin = notLogin;
	}
	public String getNotViewUser() {
		return notViewUser;
	}
	public void setNotViewUser(String notViewUser) {
		this.notViewUser = notViewUser;
	}
	public String getNotViewTable() {
		return notViewTable;
	}
	public void setNotViewTable(String notViewTable) {
		this.notViewTable = notViewTable;
	}
	public String getNotSearch() {
		return notSearch;
	}
	public void setNotSearch(String notSearch) {
		this.notSearch = notSearch;
	}
	public String getBkGround() {
		return bkGround;
	}
	public void setBkGround(String bkGround) {
		this.bkGround = bkGround;
	}
	public String getBindIp() {
		return bindIp;
	}
	public void setBindIp(String bindIp) {
		this.bindIp = bindIp;
	}
	public String getLastVisitIp() {
		return lastVisitIp;
	}
	public void setLastVisitIp(String lastVisitIp) {
		this.lastVisitIp = lastVisitIp;
	}
	public String getMenuImage() {
		return menuImage;
	}
	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
	}
	public String getWeatherCity() {
		return weatherCity;
	}
	public void setWeatherCity(String weatherCity) {
		this.weatherCity = weatherCity;
	}
	public String getShowRss() {
		return showRss;
	}
	public void setShowRss(String showRss) {
		this.showRss = showRss;
	}
	public String getMyRss() {
		return myRss;
	}
	public void setMyRss(String myRss) {
		this.myRss = myRss;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMenuExpand() {
		return menuExpand;
	}
	public void setMenuExpand(String menuExpand) {
		this.menuExpand = menuExpand;
	}
	public String getMyStatus() {
		return myStatus;
	}
	public void setMyStatus(String myStatus) {
		this.myStatus = myStatus;
	}
	public String getLimitLogin() {
		return limitLogin;
	}
	public void setLimitLogin(String limitLogin) {
		this.limitLogin = limitLogin;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getImRange() {
		return imRange;
	}
	public void setImRange(int imRange) {
		this.imRange = imRange;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public int getSecretLevel() {
		return secretLevel;
	}
	public void setSecretLevel(int secretLevel) {
		this.secretLevel = secretLevel;
	}
	public String getUserPara() {
		return userPara;
	}
	public void setUserPara(String userPara) {
		this.userPara = userPara;
	}
	public int getNotMobileLogin() {
		return notMobileLogin;
	}
	public void setNotMobileLogin(int notMobileLogin) {
		this.notMobileLogin = notMobileLogin;
	}
	public String getManageModuleType() {
		return manageModuleType;
	}
	public void setManageModuleType(String manageModuleType) {
		this.manageModuleType = manageModuleType;
	}
	public int getUserPrivType() {
		return userPrivType;
	}
	public void setUserPrivType(int userPrivType) {
		this.userPrivType = userPrivType;
	}
	public String getUserManageOrgs() {
		return userManageOrgs;
	}
	public void setUserManageOrgs(String userManageOrgs) {
		this.userManageOrgs = userManageOrgs;
	}
}
