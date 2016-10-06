package com.zs.test.busi;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zs.busi.web.controller.ZsCustomerController;



public class UserService {
	
    private SqlSessionTemplate sessionTemplate ;
	
	public UserService(SqlSessionTemplate sessionTemplate) {
		super();
		this.sessionTemplate = sessionTemplate;
	}
	
	public UserService() {
		super();
	}

	public  void crduInterface(int id){
		System.out.println("fe");
		
		UserMapper mapper=sessionTemplate.getMapper(UserMapper.class);
		User user=mapper.getUser(id);
		//sessionTemplate.close(); //spring负责的sqlsession 中他的生命周期由spring负责，不能手动关闭
		System.out.println(user);
		
	}

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// 
			
			ApplicationContext ctx;
			ctx= new ClassPathXmlApplicationContext("spring.xml");
			/*McrmDaoFactory service=(McrmDaoFactory) ctx.getBean("userService");
			ZsInfoDao dao=service.getZsInfoDaoImpl();
			System.out.println("dao "+dao.getNextFollowDate().getNextFollowDay());
			
			ZsInfoDaoImpl daoimple= (ZsInfoDaoImpl) ctx.getBean("userService2");
			System.out.println("daomple "+daoimple.getNextFollowDate().getNextFollowDay());*/
			
			/*ZsInfoService serimple= (ZsInfoService) ctx.getBean("userService3");//集成了接口，强转成接口，否则报错
			System.out.println("serimple "+serimple.checkExistCusByTel("333"));*/
			
			ZsCustomerController action= (ZsCustomerController) ctx.getBean("userService4");
			System.out.println("action "+action.getAutoGenNextFollowDate(null, null));
			
	}

}
