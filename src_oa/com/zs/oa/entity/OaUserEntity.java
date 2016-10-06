package com.zs.oa.entity;
/**
 * OA用户相关类
 * uid 唯一自增ID
 * userId 用户名
 * userName 用户真实姓名
 * userNameIndex 用户姓名索引
 * byName 登录名
 * useingKey 使用USB KEY登录
 * useingFinger 使用指纹验证
 * password 用户密码
 * keySn 绑定USB Key序列号
 * secureKeySn 动态密码卡号
 * userPriv 角色编号
 * userPrivNo 角色排序号
 * userPrivName 角色名称
 * postPriv 管理范围
 * postDept 管理范围指定部门
 * deptId 部门ID
 * deptIdOther 辅助部门
 * sex 性别
 * birthday 生日
 * isLunar 是否农历(1-是,0-否)
 * telNoDept 工作电话
 * faxNoDept 工作传真
 * addHome 家庭住址
 * postNoHome 家庭邮编
 * telNoHome 家庭电话
 * mobilNo 手机号
 * bpNo BP机号
 * email 电子邮件地址
 * oicqNo QQ号码
 * icqNo ICQ号码
 * msn MSN号码
 * avatar 自定义小头像
 * callSound 短信提示音
 * lastVisitTime 上次访问系统的时间
 * smsOn 短信提醒窗口弹出方式(1-自动,0-手动)
 * menuType 登录模式(1-在本窗口打开OA,2-在新窗口打开OA显示工具栏,3-在新窗口打开)
 * lastPassTime 上次修改密码的时间
 * theme 界面主题
 * shortCut 快捷菜单ID串
 * portal 门户ID串
 * panel 登录后显示的左侧面板(1-导航,2-组织,3-短信,4-搜索)
 * onLine 在线时长
 * onStatus 记录在线状态(1-联机,2-忙碌,3-离开)
 * attendStatus 岗位状态(1-出差,2-请假,3-外出)
 * mobilNoHidden 手机号码是否公开(0-公开,1-不公开)
 * mytableLeft 左侧的桌面模块
 * mytableRight 右侧的桌面模块
 * userPrivOther 辅助角色编码串
 * userNo 用户排序号
 * notLogin 禁止登录OA系统(1-禁止,0-不禁止)
 * notViewUser 禁止查看用户列表(1-禁止,0-不禁止)
 * notViewTable 禁止显示桌面(1-禁止,0-不禁止)
 * notSearch 禁止OA搜索(1-禁止,0-不禁止)
 * bkGround 桌面背景图片
 * bindIp 绑定IP地址
 * lastVisitIp 上次访问系统的IP
 * menuImage 菜单图标(0-每个菜单使用不同图标,1-不显示菜单图标)
 * weatherCity 
 * showRss 是否显示今日资讯(1-显示,0-不显示)
 * myRss 今日资讯中的网址设置
 * remark 备注
 * menuExpand 默认展开菜单
 * myStatus 个性签名
 * limitLogin 登录次数限制
 * photo 用户名片图片
 * imRange 即时通讯使用权限(1-允许使用,2-禁止使用)
 * leaveTime 早退时间
 * secretLevel 邮件密级类型(1-非涉密,2-秘密[一般],3-机密[重要],4-绝密[非常重要])
 * userPara 用户参数
 * notMobileLogin 禁止登录OA系统手机客户端(1-禁止,0-不禁止)
 * manageModuleType 用户管理的模块
 * userPrivType 用户角色类型
 * userManageOrgs 用户管理的分支机构ID串
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
