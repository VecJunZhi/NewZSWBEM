package com.zst.test.rbac.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import org.apache.xbean.spring.context.XmlWebApplicationContext;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.jdo.JdoSystemException;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

/**
 * 
 * @author yzj
 *
 */
public class Spring {
/**
 * 1 ˵˵spring�������ĸ��
 * 		��spring������һ��IOC�������ƶ�����������ڡ�
 * 2 ʲô�ǿ��Ʒ�ת(IOC)��ʲô������ע��(DI)��
 * 		�𣺿�����˵���������˶����ⲿ�ļ����������������Ļ�ȡ�������л�ȡ�������Ƕ�������ȥ��ȡ����������ע����϶���Ĺ��̳�Ϊ���Ʒ�ת�����������������Ƶ�ԭ�������ԭ�򣺲����������ǣ��������㡣Ŀ��:����������������������
 * 3 ʲô������������(AOP)
 * 		�������������ʵ����ĳһ����������ʱ����̬����������һЩ���ܣ���������־�ȡ�����ʱͨ�������е㣬��������Ӧ��֪ͨ���γ����棬ʵ�ֹ���ʱͨ�����ɴ�������������Ŀ�귽��ʱ��ִ�д�������֪ͨ���ܣ��ڵ���Ŀ������е�����������
 * 		       ʹ��AOP�ô�ʱ������רע��������ҵ���߼��Ŀ�����aop��ʵ��ʱ������ơ�
 * 4 ̸̸spring��ܵ���Ҫģ�飬�Լ������õ���Щģ��
 * 		��spring��Ҫ��20���ģ����ɡ����¿��Է�Ϊ��
 * 			WEB:��Ҫ�ṩ spring-mvc
 * 			CORE:spring�ĺ��Ĺ��ܣ���Ҫ�� core context bean SPel  �ṩ��ioc��ʵ�֡�
 * 			DATA ACCESS:�ṩ�������ݿ⽻����֧�֡�jdbc  orm  oxm jms transactions
 * 			AOP:
 * 			MESSAGE:
 * 5 ʹ��spring��ܵĺô�
 * 		��spring��һ����������ܷ�װ�˺ܶ��ģ��
 * 			ioc:�������
 * 			aop:ʵ�ֹ��ܵĶ�̬ע�롣��ҵ���߼���ϵͳ����ֿ���רע��ҵ���߼��Ŀ�����
 * 			�������������Ĵ��������٣��������
 * 			mvc������webӦ�ã���һ��web��ܡ�
 * 			����spring�ṩһ���������������ӿڣ�������չ������������������ȫ������
 * 6 ��ν�spring���õ�Ӧ��������Щ����
 * 		�𣺻���xml��ʽ����
 * 		        ����ע�ⷽʽ����
 * 7 (1)����ʹ��xml�ķ�ʽ����spring
 * 		����Ҫʹ��spring���涨��һϵ�б�ǩ��ʵ�֣���Ҫ��beans bean aop tx context mvc aso�ȡ�
 * 	 (2)����ʹ��ע��ķ�ʽ����spring
 * 		����ע��ķ�ʽ����xml��ʽ��bean��������bean����ת�Ƶ�����ڲ��������ϣ������ϣ�����ע�⣬�Ϳ��Լ��뵽spring�����С�ע��ķ�ʽ���ᱻ������xml��ʽע��Ǯ�������Ժ��߻Ḳ��ǰ�ߵ���Ϣ��
 * 		       ע����spring��Ĭ���ǹرյģ���Ҫspring�ļ�������<context:annotation-config/>��
 * 8 ����spring Bean����������
 * 		��spring bean��������������������ͨ��bean factory������init������ʼ��  destroy�����������١�
 * 9 ����spring�и���Bean�ķ�Χ
 * 		����Ҫ�����֣�prototype(Ϊÿһ��bean�����ṩһ��ʵ��),singleton,request��ÿһ�����Կͻ��˵����󴴽�Ҫ��ʵ����,session
 * 10 ʲô��spring��Ƕ��beans
 * 		�𣺽��������������ͨ��proper����������bean���������������ע�롣
 * 11 spring�еĵ���bean�Ƿ����̰߳�ȫ��
 * 		��springbean�ĵ���û�и����̰߳�ȫ��������Ҫ����Ҫ�Լ��������á�
 * 12 ����˵�������springע��һ��java�ļ�����
 * 		��	<bean>
 * 				<property>
 * 					<map>
 * 						<entry key=''>value</entry>
 * 					</map>
 * 				</property>
 * 			</bean>
 * 13 ����˵�������spring��bean��ע��һ��java.util.properties
 * 14 �����spring��bean���Զ����ɵ�ԭ��
 * 		��ͨ��xml�ļ����õ�bean��ͨ��bean factory�л�ȡ��ͨ��������ƣ�������Ӧ�Ķ���ʵ����ע�뵽������
 * 15 �����Զ�����bean֮��ģ�������
 * 		���Զ�����bean  ��bean factory ��application context���ַ�ʽ
 * 16 ��ο�������ע����Զ�д��
 * 		����spring�����ļ��м���<context:annoiation-config>
 * 17 ����˵�� @required @autowired @qualifier ע��
 * 		��
 * 18 ˵��������ע���setter����ע�������
 * 		�𣺹�����ע�룺
 * 		   settע�룺
 * 19 �о�spring���õ�����Щ���ģʽ
 * 		�𣺾�̬����ģʽ��
 * 			��������ģʽ��
 * 			ģ�巽��ģʽ��
 * 			����ģʽ��
 * 			����ģʽ��
 * 			
 * 20 ˵��applicationContext��beanfactory�Ĺ�ϵ��
 * 		��	beanfactory�ṩ�����Լ��������󣬾���һ�������࣬���������������ڣ���ʼ��������
 * 			applicationContext��beanfacotry��������չ���������������ܣ����ʻ�����Դ�ļ���ȡ��
 * 			���������ֽϳ����� ApplicationContext ʵ�ַ�ʽ��
			1��ClassPathXmlApplicationContext����classpath��XML�����ļ��ж�ȡ�����ģ������������Ķ��塣Ӧ�ó��������Ĵӳ��򻷾�������ȡ�á�
				ApplicationContext context = new ClassPathXmlApplicationContext(��bean.xml��);
			2��FileSystemXmlApplicationContext �����ļ�ϵͳ�е�XML�����ļ���ȡ�����ġ�
				ApplicationContext context = new FileSystemXmlApplicationContext(��bean.xml��);
			3��XmlWebApplicationContext����WebӦ�õ�XML�ļ���ȡ�����ġ�
 * 				�����WebӦ�õ�WebApplicationContext
 * =========================================================================================
 * 21 ����JDBCģ�飺
 * 		��
 * 22 ����ORMģ�飺
 * 23 ����WEBģ�飺
 * 24 applicationContext��ʵ������Щ��
 * 25 ����spring ����bean���������ڣ�
 * 26 ��Щ����Ҫbean���������ڷ���������������Щ������
 * 27 spring�����ע��java����
 * 		<list>��������ע��һ��ֵ����������ͬ��ֵ��
		<set> ��������ע��һ��ֵ������������ͬ��ֵ��
		<map> ��������ע��һ���ֵ�ԣ�����ֵ������Ϊ�������͡�
		<props>��������ע��һ���ֵ�ԣ�����ֵ��ֻ��ΪString����
 * 28 ��ϸ����spring��ע�� @autowired @required @qualifier
 * 29 ��spring����θ�����Чʹ��JDBC
 * 30 ����JDBCTemplate
 * 31 spring��Dao��֧��
 * 32 ����spring��ORM��֧�֡�
 * 33 spring֧�ֵ����������������Щ������ĸ��뼶��
 * 34 ����spring��ܶ����������ŵ㣿
 * 
 * 35 spring mvc�������
 * 36 ���DispacherServlet
 * 37 �����õ�WebApplicationContext
 * 38 ʲô��spring mvc��ܵĿ�����
 * 39 spring mvc��ע������Щ��������á�
 * =========================================
 * 40 
 * 
 * 
 */
	/*public static void main(String[] args) {
		Resource resource = null;
		BeanFactory factory =new XmlBeanFactory(resource);
		ApplicationContext applicationContext;
		ApplicationContext context=new ClassPathXmlApplicationContext("");
		new FileSystemXmlApplicationContext();
	}*/
	public void spring_framework(){
		/**
		 * web : spring-web�ļ��ϴ�����   spring-webmvc 
		 * core container :beans core context
		 * data access : jdbc orm oxm tx
		 * 
		 * aop�����������̣����Զ��巽���������������
		 * aspects
		 * message
		 * instrumation
		 * test ��֧��juint��testNG��Ԫ���Ժͼ��ɲ���
		 */
	}
	public void beans(){//beans�Ĺ�����������
		/**
		 * 
		 */
	}
	public void ioc(){//���Ʒ�ת�����������󣬵������������ķ�ʽ����IOC�������������ⲿ��Դ�������һЩ�ļ���������ƺõĶ��󽻸��������ơ�
		/**
		 *��ת����ͳ��Ӧ�ó����У��ڶ�����������ȡ�������󣬳�Ϊ��ת��
		 *��ת�����������ǲ��Ҳ�ע���������󣬶���ֻ�Ǳ����Ľ�����������Ĺ��̳�Ϊ��ת��
		 *
		 *����Ioc�����󣬴����Ͳ�����������Ŀ���Ȩ������������������ע����϶��󡣶��������ʵ��������ϡ�
		 *Ioc��������������ԭ�򣺺�����ԭ�򣺲��������ǣ��������㡣Ioc������ҵ���������ע�룬���ö�������ȥ�ҡ�
		 *
		 *���Ʒ�ת����������Ŀ���Ȩ����ת�ƣ���ǰ��Ӧ�ó������������������ڽ�����������ioc������ioc��������һ�����������𴴽���������Ҫʲô���󣬾͸���ע��ʲô�����������ڵ�������ϵ�ͱ�ɣ�����������ioc��������ǰ������������ϵ��û���ˣ�����ʵ��������ϡ�
		 *
		 *spring����ͨ��������ʵ��ע�룬����ķ�����set  ���캯��  ���ӿڡ�
		 *
		 * beanFactory ��IOC�ĺ��Ľӿڣ�����ʵ��������λ������Ӧ�ó����Ķ����Լ�������Щ������������ϵ��
		 * xmlBeanFactoryʵ����beanFactory�ӿڣ�ͨ����ȡxml�����ļ����ݣ����Ӧ�ö��󼰶�����������ϵ��
		 * spring������ע�뷽ʽ���ӿ�ע�룬setע�룬���캯��ע�롣
		 * 
		 * ����ϵͳ������ԣ�������֣�
		 */
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-mybatis.xml"));
		DataSource ds= beanFactory.getBean(DataSource.class, "dataSource");
	}
	public void applicationContext(){//������
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-mybatis.xml");//������BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-mybatis.xml"));
		DataSource ds=context.getBean(DataSource.class, "dataSource");
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-mybatis.xml"));
		DataSource ds2= beanFactory.getBean("dataSourceBusi", DataSource.class);
       
		System.out.println(ds2);
		
	}
	public void aop(){//����������aop��
		/**
		 * 1 ͨ������ķ�ʽʵ��aop����
		 * 2 �������
		 * 		�е㣺ָ����Щ���������ܻ�ִ��֪ͨ���ݡ�
		 * 		֪ͨ��������Щ������Ҫ��ʲô���飬Ҫ����Ĺ��ܣ������� ��־ ��ȫ�ȡ�
		 * 		���棺֪ͨ+�е� ������档
		 * 		֯�룺������Ӧ�õ������У��Ӷ����ɴ������
		 * 	���Կ�����aop��ʵ�־��Ǵ���ģʽ��spring �������ࣨ������������ࣩ֯�뵽bean���������У������ߵ���Ŀ������ͨ�����ô����ࡣ��ִ�����棬�ٰɵ���ת��������Ŀ���࣬�����䷽����
		 * 
		 * �������߶��巽�����������е�
		 * 
		 * ����ϵͳ������ԣ�������֣�
		 */
		/*	<aop:config>
			<aop:pointcut expression="" id=""/>
			<aop:advisor advice-ref=""/>
			<aop:aspect>
				<aop:after method=""/>
				<aop:after-returning method=""/>
				<aop:after-throwing method=""/>
				<aop:around method=""/>
				<aop:before method=""/>
				<aop:declare-parents types-matching="" implement-interface=""/>
				<aop:pointcut expression="" id=""/>
			</aop:aspect>
			</aop:config>
		*/
	}
	/**
	 * spring �ṩ��Щ�������ݿ��dao��֧�֣�Ŀ�����Ա�׼�ķ�ʽʹ�ò�ͬ�����ݿ����Ӽ�����Ϊ����һ��һ�µķ�ʽ�������ݿ⣬spring�ṩ�˳���Dao���������չ����jdbcDaoSupport����һ��jdbcTemplation,HibernateDaoSupport����һ��HibernateTemplaiton,
	 */
	public void dao(){

		DaoSupport ds;
		JdbcDaoSupport js;
		HibernateDaoSupport hs;
		
	}
	/**
	 * jdbcTemplaiton ��spring�ṩ��һ��ͨ�õ�ͨ��jdbc�������ݿ�Ĳ���ģ�塣
	 * �õ������ģʽ��
	 * 	ģ�巽�����ģʽ��������ĺͿɱ�ķ������������һ��ģ�巽�������ɱ�Ĳ��ֽ�������ʵ�֡�����ֻ��Ҫע���ѯ����伴�ɣ���ȡ���ӣ��ر����ӵȲ������ع�ע��
	 */
	public void jdbcTemplation(){
		JdbcTemplate jt;
		HibernateTemplate ht;
	
	}
	/**
	 * Ϊ�˼�spring�����ļ��л�����������ã�spring�ṩ��ע�ⷽʽ��������Ĺ�����뵽�����С�
	 * spring�ṩ��ע�� @autowired @qualifier ���������������ע�룩
	 * 		-- @autowired �������ڶ���ĳ�Ա�����������ϣ����캯���ϡ�
	 * 		-- @qualifier �������ڶ���ĳ�Ա���������������У����캯�������У�
	 * 		ע�⣺@autowired ���ڳ�Ա�����ϣ�ͨ���������ע��������Կ���ʡ��set get ������Ĭ�ϰ������ͽ���װ�䡣
	 * 			@qualifier �� @autowired һ��ʹ�ã����ж��ͬ���� beanʱ�������޶� @autowired ������һ������װ�䣬��װ�������Ĭ�ϵ����ͱ�Ϊ�������ƽ���װ�䡣
	 * spring֧����JSR-250�淶�ṩ��ע�� @resource @postconstruct @predestory 
	 * 		-- @resource ���ܵ�ͬ��@autowired ����ͬ���ǣ� @resource Ĭ�ϰ��� ���ƽ���ע�� ������������ name = ,type = .
	 * 		-- @postconstruce ��ע�ڷ����ϣ���ʾ�����ʼ��֮��ִ�еĶ�����
	 * 		-- @predesotry :��ע�ڷ����ϣ���ʾ��������֮ǰִ�еĶ�����
	 * spring ����ע�����ע�� @component @controller @service @repository
	 * 		spring �ṩ��ע������������������뵽�����У������Ͳ�����spring�����������ļ�������bean��������
	 * 		-- @component ��������������������κβ�Ρ�
	 * 		-- @controller ͨ�����ڿ��Ʋ㡣
	 * 		-- @service ͨ������ҵ��㣬���쳣���Ͷ��������ݷ����쳣��
	 * 		-- @repository ͨ���������ݷ��ʲ㣬���Խ��쳣��װ�����ݷ����쳣��
	 * spring �п���ע�⣺
	 * 		����  AutowiredAnnotationBeanPostProcessor������autowiredע�⡣
	 * 		���� CommonAnnotationBeanPostProcessor ����spring֧�ֵ�jsr-250�淶ע�⡣
	 * 	ע�⣺1 Ϊ�˼����ã�spring�ṩ��һ�������ã�ֻ��Ҫ <context:annotation-config/>�Ϳ��Կ���ע�⡣
	 * 		2 Ϊ�˸��������ã���Щ����Ҫ������Ƴ�spring�����У���ô�������ã�<context:component-scan/>��ɨ����˷�ʽ��
	 * 			���������ͣ�ע�ͣ�
	 * 					����ָ����
	 * 					������ʽ��
	 * 					AspectJ���ʽ��
	 * 		eg:
				<context:component-scan base-package="com.baobaotao">
				    <context:include-filter type="regex" 
				        expression="com\.baobaotao\.service\..*"/>
				    <context:exclude-filter type="aspectj" 
				        expression="com.baobaotao.util..*"/>
				</context:component-scan>
	 * 		3 ע�����༶��ģ�����xml���÷�ʽ����
	 */
	@PostConstruct
	public void annotation(){
		System.out.println("��ʼ��֮��ִ�еķ���..............");
	}
	/**
	 * spring-ormģ���ṩ�������/��ϵӳ���ܵļ��� ��hibernate  ibatis��jpa �ȡ�
	 * 	��hibernate�ļ��ɣ�����ҪЩ����sql��ͨ���־û�����/��ϵ���ã������Զ�������
	 * 					����ҪЩ����sql��䣨��ֻдһС���֣���
	 * 					��Ҫһ��sessionFactory ,��ע��һ��Datasource.
	 *  ��ibatis�ļ��ɣ�    ��Ҫд����sql��ͨ���־û�����/��ϵ���ã������Զ�������
	 * 				      ��Ҫд����sql��䣨��ֻдһС���֣���
	 * 				      ��Ҫһ��sessionFactory ,��ע��һ��Datasource.
	 */
	public void orm(){
		
	}
	/**
	 * spring mvc ģʽ�ṩ��һ������web��Ŀ�Ŀ�ܡ�
	 * DispatcherServlet Ϊ���Ŀ������������������̡�
	 * spring mvc ��ʼ�����̣�
	 * 		��ʼ���� ���������ļ�����ʼ�� handleMapping ,handleAdapt, 
	 * spring mvc ���������̣�
	 * 	���� diapatcherServlet ��������--> ����handleMapper��������һ�� handleExecuteChain(��������������Ӧ��handle) -->��ִ��ע������������ڽ���HandleAdapt ִ����Ӧ��handl,����һ��modleAndView.
	 *  -->������ͼ�ļ�����ʾ��ҳ���ϡ�
	 *  
	 *  spring mvc �õ������ģʽ��ģ�巽��ģʽ���������仯�ķ��룬���仯��д�ɳ���������ʵ��  httpservlet -->httpservletBean-->fremaorkServlet-->dispatcherSevlet
	 *  					  ����ģʽ�������ļ��ļ��أ��������ܶ�����࣬�û�ѡ����ʵ�������ļ����ء�
	 *  spring mvc ���õ��ļ�ϵͳ�У�
	 *   1 ����ע�⡣ cotroller ��ע����spring��ע��������֣������ظ�����
	 *   2 ���� handelmapper handleAdapt ,viewResolver .
	 *   3 ��̬�ļ��Ĵ���
	 *   4 ���Ҫ�õ�json���ݣ�Ҫ��jacksonHttpע�뵽handleAdapt�С�
	 */
	public void springMVC(){
		Dispatcher di;
		HttpServlet ht;
		DispatcherServlet dst;
		HandlerMapping hmap;
		HandlerExecutionChain hec;
		ApplicationContext xml;
		Controller cr;
		HttpRequestHandlerAdapter htad;
		AnnotationMethodHandlerAdapter handle;
	}
	/**
	 * 
	 */
	public void oxm(){
		
	}
	
}
