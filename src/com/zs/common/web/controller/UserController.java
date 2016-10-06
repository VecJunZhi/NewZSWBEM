package com.zs.common.web.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.zs.busi.service.ZsInfoService;
import com.zs.oa.dao.impl.OaUserDaoImpl;
import com.zs.rbac.core.LoginBaseStatus;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.core.UserLoginBase;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.UserService;
import com.zs.rbac.service.impl.UserServiceImpl;

/**
 * �û���֤������
 * @author JiaRui
 */
@Controller
public class UserController {
	
	private Log log = LogFactory.getLog(UserController.class);	
	
	@Autowired
	private ZsInfoService zsInfoService;
	
	@Autowired
	private UserService userService;

	
	/**
	 * PC�˵�¼ҳ��
	 */
	@RequestMapping(value="/loginWBEM",method = RequestMethod.GET)
	public String loginWBEM(){
		if(RBACSubject.getSecurityUtils().isAuthenticated()){
			return "redirect:/wbem/index.action";
		}else{
			return "/loginWBEM";
		}
		
	}
	
	/**
	 * �ֻ��˵�¼ҳ��
	 *  �ֻ����Զ���¼�жϣ��˷��������ã���Ҫ�Ľ�����cookie�м����û�����session id ��ͨ�����ݿ���֤��
	 * @param model
	 * @param response
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/loginMBEM",method = RequestMethod.GET)
	public String loginMBEM(Model model){
		String resultPage = "/loginMBEM";
		Subject subject = RBACSubject.getSecurityUtils();
		log.info("=================="+subject.getSession().getAttribute("user"));
		if(subject.isAuthenticated() || subject.isRemembered()){
			resultPage = returnLoginInfo();
		}
		return resultPage;
	}
	
	/**
	 * ��֤PC�˵�¼
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginWBEM",method = RequestMethod.POST)
	public String checkLoginWBEM(UserLoginBase userLoginBase,Model model) {
		userLoginBase.setRememberMe(false);
		String loginStatus= RBACSubject.checkUserLogin(userLoginBase);
		log.info("loginstatus "+loginStatus);
		if(loginStatus != null && LoginBaseStatus.LOCKEDACCOUNT.equals(loginStatus)){
			log.info("�˺ű�����");
			model.addAttribute("error", "�����˺ű�����");
			return "/loginWBEM";
		}
		
		if(!LoginBaseStatus.AUTHENTICATIONSUCCESS.equals(loginStatus)){
			model.addAttribute("error", "�û����������");
			log.info("�˺��������");
			return "/loginWBEM";
		}else{
			return "redirect:/wbem/index.action";
		}
	}
	
	/**
	 * �ֻ��˵�¼��ϵͳ�л�����
	 * @return
	 */
	private static String returnLoginInfo(){
		ModelAndView mav = new ModelAndView();
		String resultPage = ""; 
		Subject subject = RBACSubject.getSecurityUtils();
		Map<String,String> sysList = RBACSubject.getMobleSystem();	//<��ɫ����,URL>
		if(sysList.size() < 1){
			mav.addObject("error", "��û��Ȩ�޵�½��ϵͳ������ϵ����Ա");
			RBACSubject.logout();
			resultPage =  "/loginMBEM";
		}
		if(sysList.size() == 1){
			subject.getSession().setAttribute("sysList", sysList);
			Iterator<String> iter = sysList.keySet().iterator();
			if(iter.hasNext()){
				String key = iter.next();
				int flag = 0;
				switch(key){
				case "����ϵͳ����Ա":
					flag = 1;
					break;
				case "����ϵͳ":
					flag = 2;
					break;
				case "����ϵͳ":
					flag = 3;
					break;
				}
				resultPage = "redirect:/mbem/selectProject.action?url="+sysList.get(key)+"&isShow=0&roleId="+flag;
			}
		}
		if(sysList.size() > 1){
			subject.getSession().setAttribute("sysList", sysList);
			resultPage = "redirect:/mbem/selectSys.action";
		}
		return resultPage;
	}
	
	/**
	 * ��֤�ֻ��˵�¼
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginMBEM",method = RequestMethod.POST)
	
	public String checkWBEMLogin(UserLoginBase userLoginBase,Model model) {
		
		userLoginBase.setRememberMe(true);		//�����Զ���¼
		String loginStatus= RBACSubject.checkUserLogin(userLoginBase);
		if(loginStatus != null && LoginBaseStatus.LOCKEDACCOUNT.equals(loginStatus)){
			log.info("�˺ű�����");
			model.addAttribute("error", "�����˺ű�����");
			return "/loginMBEM";
		}
		if(!LoginBaseStatus.AUTHENTICATIONSUCCESS.equals(loginStatus)){
			model.addAttribute("error", "�û����������");
			log.info("�˺��������");
			return "/loginMBEM";
		}
		
		String resultPage = returnLoginInfo(); 
		log.info("ready return...."+resultPage);
		return resultPage;
	}
	/**
	 * ��֤�ֻ��˵�¼
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginMBEMDD",method = RequestMethod.POST)
	@ResponseBody
	public String checkWBEMLoginDD(UserLoginBase userLoginBase,Model model) {
		
		userLoginBase.setRememberMe(false);		//�ر��Զ���¼
		log.info("password: "+userLoginBase.getPassword()+" username: "+userLoginBase.getUserName());
		String loginStatus= RBACSubject.checkUserLogin(userLoginBase);
		if(loginStatus != null && LoginBaseStatus.LOCKEDACCOUNT.equals(loginStatus)){
			log.info("�˺ű�����");
			model.addAttribute("error", "�����˺ű�����");
			return "/loginMBEM.action";
		}
		if(!LoginBaseStatus.AUTHENTICATIONSUCCESS.equals(loginStatus)){
			model.addAttribute("error", "�û����������");
			log.info("�˺��������");
			return "/loginMBEM.action";
		}
		
		String resultPage = returnLoginInfo(); 

		return resultPage;
	}
	/**
	 * ��ȡ�����û���ǰuid,userName,password
	 */
	@RequestMapping("/check")
	@ResponseBody
	public Map<String,String> check(Model model,String access_token,String code){
		//��ȡuserId
		String userId="";
		try{
			BufferedReader in = null;
		    String urlNameString="https://oapi.dingtalk.com/user/getuserinfo?access_token="+access_token+"&code="+code;
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
		    connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null){ 
		      JSONObject jo = JSONObject.parseObject(line);
		      userId = jo.getString("userid").toString(); 
		    }
		 }catch(Exception e){
			  e.printStackTrace(); 
		 }
		//����userId��ȡuserName
		String userName="";
		String oaDriver="";
		String oaUrl="";
		String oaUserName="";
		String oaPassword="";
		Properties p = new Properties();
		try {
			p.load(OaUserDaoImpl.class.getClassLoader().getResourceAsStream("config.properties"));
			oaDriver=p.getProperty("oa_driver");
			oaUrl=p.getProperty("oa_url");
			oaUserName=p.getProperty("oa_username");
			oaPassword=p.getProperty("oa_password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			 Class.forName(oaDriver);
			 String url =oaUrl;
			 con = DriverManager.getConnection(url,oaUserName,oaPassword);
			 String sql ="select MOBIL_NO from user where uid=?";
		     pst = con.prepareStatement(sql);
		     pst.setString(1, userId);
		     rs = pst.executeQuery();
		     while(rs.next()){
		    	 userName=rs.getString("MOBIL_NO"); 
		     }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//����userName��ȡpassword
		String password = "";
		UserService userService = new UserServiceImpl();
		User user = userService.findUserByLoginName(userName);
		if(user !=null){
			password = user.getPassword();
		}else if(user ==null){
			password="noHas";
		}
		
		//���� �û���������
		Map<String,String> map = new HashMap<String,String>();
		map.put("userName", userName);
		map.put("password", password);
		return map;
	}
	
}
