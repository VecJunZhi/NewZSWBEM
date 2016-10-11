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
 * 1 说说spring中容器的概念？
 * 		答：spring容器是一个IOC容器控制对象的生命周期。
 * 2 什么是控制反转(IOC)，什么是依赖注入(DI)。
 * 		答：控制是说容器控制了对象及外部文件。对象的依赖对象的获取从容器中获取，而不是对象主动去获取，从容器中注入组合对象的过程称为控制反转。体现了面向对象设计的原则好莱坞原则：不用来找我们，我们找你。目的:代码解耦，对象依赖于容器。
 * 3 什么是面向切面编程(AOP)
 * 		答：面向切面可以实现在某一个方法运行时，动态加入其他的一些功能，如事务日志等。具体时通过定义切点，在配置相应的通知，形成切面，实现过程时通过生成代理对象，请求调用目标方法时先执行代理对象的通知功能，在调用目标对象中的真正方法。
 * 		       使用AOP好处时：可以专注与真正的业务逻辑的开发，aop的实现时反射机制。
 * 4 谈谈spring框架的主要模块，以及你所用到哪些模块
 * 		答：spring主要由20多个模块组成。大致可以分为：
 * 			WEB:主要提供 spring-mvc
 * 			CORE:spring的核心功能：主要有 core context bean SPel  提供了ioc的实现。
 * 			DATA ACCESS:提供了与数据库交互的支持。jdbc  orm  oxm jms transactions
 * 			AOP:
 * 			MESSAGE:
 * 5 使用spring框架的好处
 * 		答：spring是一个轻量级框架封装了很多的模块
 * 			ioc:代码解耦
 * 			aop:实现功能的动态注入。将业务逻辑和系统服务分开，专注于业务逻辑的开发。
 * 			容器：负责对象的创建及销毁，管理对象。
 * 			mvc：开发web应用，是一个web框架。
 * 			事务：spring提供一个持续的事务管理接口，可以扩展到上至本地事务下至全局事务。
 * 6 如何将spring配置到应用中有哪些方法
 * 		答：基于xml方式配置
 * 		        基于注解方式配置
 * 7 (1)怎样使用xml的方式配置spring
 * 		答：主要使用spring所规定的一系列标签来实现：主要由beans bean aop tx context mvc aso等。
 * 	 (2)怎样使用注解的方式配置spring
 * 		答：用注解的方式代替xml方式的bean描述，将bean描述转移到组件内部，在类上，方法上，加入注解，就可以加入到spring容器中。注解的方式将会被容器在xml方式注入钱处理，所以后者会覆盖前者的信息。
 * 		       注解在spring中默认是关闭的，需要spring文件中配置<context:annotation-config/>。
 * 8 描述spring Bean的生命周期
 * 		答：spring bean的生命周期由容器管理，通过bean factory来管理，init方法初始化  destroy方法进行销毁。
 * 9 描述spring中各种Bean的范围
 * 		答：主要由四种：prototype(为每一个bean请求提供一个实例),singleton,request（每一个来自客户端的请求创建要给实例）,session
 * 10 什么是spring的嵌入beans
 * 		答：将对象的依赖对象，通过proper属性中配置bean，将依赖对象进行注入。
 * 11 spring中的单例bean是否是线程安全的
 * 		答：springbean的单例没有附加线程安全，如有需要，需要自己进行配置。
 * 12 举例说明如何用spring注入一个java的集合类
 * 		答：	<bean>
 * 				<property>
 * 					<map>
 * 						<entry key=''>value</entry>
 * 					</map>
 * 				</property>
 * 			</bean>
 * 13 举例说明如何在spring的bean中注入一个java.util.properties
 * 14 请解释spring的bean的自动生成的原理
 * 		答：通过xml文件配置的bean，通过bean factory中获取，通过反射机制，创建相应的对象实例，注入到对象中
 * 15 解析自动生成bean之间模块的区别
 * 		答：自动生成bean  ：bean factory ，application context两种方式
 * 16 如何开启基于注解的自动写入
 * 		答：在spring配置文件中加入<context:annoiation-config>
 * 17 举例说明 @required @autowired @qualifier 注解
 * 		答：
 * 18 说明构造器注入和setter方法注入的区别
 * 		答：构造器注入：
 * 		   sett注入：
 * 19 列举spring中用到了那些设计模式
 * 		答：静态工厂模式：
 * 			工厂方法模式：
 * 			模板方法模式：
 * 			策略模式：
 * 			代理模式：
 * 			
 * 20 说出applicationContext和beanfactory的关系。
 * 		答：	beanfactory提供对象以及依赖对象，就像一个工厂类，负责对象的生命周期，初始化及销毁
 * 			applicationContext对beanfacotry进行了扩展，增加了其他功能，国际化，资源文件读取，
 * 			以下是三种较常见的 ApplicationContext 实现方式：
			1、ClassPathXmlApplicationContext：从classpath的XML配置文件中读取上下文，并生成上下文定义。应用程序上下文从程序环境变量中取得。
				ApplicationContext context = new ClassPathXmlApplicationContext(“bean.xml”);
			2、FileSystemXmlApplicationContext ：由文件系统中的XML配置文件读取上下文。
				ApplicationContext context = new FileSystemXmlApplicationContext(“bean.xml”);
			3、XmlWebApplicationContext：由Web应用的XML文件读取上下文。
 * 				如针对Web应用的WebApplicationContext
 * =========================================================================================
 * 21 解释JDBC模块：
 * 		答：
 * 22 解释ORM模块：
 * 23 解释WEB模块：
 * 24 applicationContext的实现有哪些？
 * 25 解释spring 管理bean的生命周期？
 * 26 那些是重要bean的生命周期方法，可以重载这些方法吗？
 * 27 spring中如何注入java集合
 * 		<list>类型用于注入一列值，允许有相同的值。
		<set> 类型用于注入一组值，不允许有相同的值。
		<map> 类型用于注入一组键值对，键和值都可以为任意类型。
		<props>类型用于注入一组键值对，键和值都只能为String类型
 * 28 详细解释spring的注解 @autowired @required @qualifier
 * 29 在spring中如何更加有效使用JDBC
 * 30 解释JDBCTemplate
 * 31 spring对Dao的支持
 * 32 解释spring对ORM的支持。
 * 33 spring支持的事务管理类型有那些，事务的隔离级别
 * 34 详述spring框架对事务管理的优点？
 * 
 * 35 spring mvc框架详述
 * 36 详解DispacherServlet
 * 37 哪里用到WebApplicationContext
 * 38 什么是spring mvc框架的控制器
 * 39 spring mvc的注解有哪些，详解作用。
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
		 * web : spring-web文件上传下载   spring-webmvc 
		 * core container :beans core context
		 * data access : jdbc orm oxm tx
		 * 
		 * aop：面向切面编程，可以定义方法拦截器和切入点
		 * aspects
		 * message
		 * instrumation
		 * test ：支持juint和testNG单元测试和集成测试
		 */
	}
	public void beans(){//beans的管理，生命周期
		/**
		 * 
		 */
	}
	public void ioc(){//控制反转：不创建对象，但是描述创建的方式。在IOC中容器控制了外部资源（对象或一些文件），将设计好的对象交给容器控制。
		/**
		 *正转：传统的应用程序中，在对象中主动获取依赖对象，称为正转。
		 *反转：容器帮我们查找并注入依赖对象，对象只是被动的接受依赖对象的过程称为反转。
		 *
		 *有了Ioc容器后，创建和查找依赖对象的控制权交给了容器，由容器注入组合对象。对象与对象实现了松耦合。
		 *Ioc体现了面向对象的原则：好莱坞原则：不用找我们，我们找你。Ioc帮对象找到依赖对象并注入，不用对象主动去找。
		 *
		 *控制反转：创建对象的控制权进行转移，以前是应用程序主动创建对象，现在交给第三方，ioc容器。ioc容器就像一个工厂，负责创建对象，你需要什么对象，就给你注入什么对象。所以现在的依赖关系就变成，对象都依赖于ioc容器，以前对象间的依赖关系就没有了，这样实现了松耦合。
		 *
		 *spring就是通过反射来实现注入，具体的方法：set  构造函数  ，接口。
		 *
		 * beanFactory 是IOC的核心接口，负责实例化，定位，配置应用程序间的对象以及建立这些对象间的依赖关系。
		 * xmlBeanFactory实现了beanFactory接口，通过获取xml配置文件数据，组成应用对象及对象间的依赖关系。
		 * spring有三种注入方式：接口注入，set注入，构造函数注入。
		 * 
		 * 降低系统的耦合性，如何体现？
		 */
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-mybatis.xml"));
		DataSource ds= beanFactory.getBean(DataSource.class, "dataSource");
	}
	public void applicationContext(){//上下文
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-mybatis.xml");//区别与BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-mybatis.xml"));
		DataSource ds=context.getBean(DataSource.class, "dataSource");
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-mybatis.xml"));
		DataSource ds2= beanFactory.getBean("dataSourceBusi", DataSource.class);
       
		System.out.println(ds2);
		
	}
	public void aop(){//如何深入理解aop。
		/**
		 * 1 通过代理的方式实现aop机制
		 * 2 基本概念：
		 * 		切点：指定那些方法来接受或执行通知内容。
		 * 		通知：告诉那些方法需要做什么事情，要加入的功能，如事务 日志 安全等。
		 * 		切面：通知+切点 组成切面。
		 * 		织入：将切面应用到程序中，从而生成代理对象。
		 * 	可以看出，aop的实现就是代理模式。spring 将代理类（包裹有切面的类）织入到bean管理容器中，调用者调用目标类是通过调用代理类。先执行切面，再吧调用转给真正的目标类，调用其方法。
		 * 
		 * 允许开发者定义方法拦截器和切点
		 * 
		 * 降低系统的耦合性，如何体现？
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
	 * spring 提供了些操作数据库的dao层支持，目的是以标准的方式使用不同的数据库连接技术。为了以一种一致的方式访问数据库，spring提供了抽象Dao层类进行扩展，如jdbcDaoSupport返回一个jdbcTemplation,HibernateDaoSupport返回一个HibernateTemplaiton,
	 */
	public void dao(){

		DaoSupport ds;
		JdbcDaoSupport js;
		HibernateDaoSupport hs;
		
	}
	/**
	 * jdbcTemplaiton 是spring提供了一个通用的通过jdbc连接数据库的操作模板。
	 * 用到的设计模式：
	 * 	模板方法设计模式：将不变的和可变的分离出来，定义一个模板方法，将可变的部分交给子类实现。子类只需要注入查询的语句即可，获取连接，关闭连接等操作不必关注。
	 */
	public void jdbcTemplation(){
		JdbcTemplate jt;
		HibernateTemplate ht;
	
	}
	/**
	 * 为了简化spring配置文件中基本组件的配置，spring提供了注解方式来将对象的管理加入到容器中。
	 * spring提供的注解 @autowired @qualifier （用于依赖对象的注入）
	 * 		-- @autowired 可以用于对象的成员变量，方法上，构造函数上。
	 * 		-- @qualifier 可以用于对象的成员变量，方法参数中，构造函数参数中，
	 * 		注意：@autowired 用在成员变量上，通过反射机制注入对象，所以可以省略set get 方法。默认按照类型进行装配。
	 * 			@qualifier 与 @autowired 一起使用，当有多个同类型 bean时，用来限定 @autowired 按照那一个进行装配，将装配机制由默认的类型变为按照名称进行装配。
	 * spring支持有JSR-250规范提供的注解 @resource @postconstruct @predestory 
	 * 		-- @resource 功能等同与@autowired 。不同的是： @resource 默认按照 名称进行注入 ，有两个属性 name = ,type = .
	 * 		-- @postconstruce 标注在方法上，表示对象初始化之后执行的动作。
	 * 		-- @predesotry :标注在方法上，表示对象销毁之前执行的动作。
	 * spring 用来注入类的注解 @component @controller @service @repository
	 * 		spring 提供了注解可以用来将类对象加入到容器中，这样就不用在spring上下文配置文件中配置bean的描述。
	 * 		-- @component 泛化的组件，可以用于任何层次。
	 * 		-- @controller 通常用于控制层。
	 * 		-- @service 通常用于业务层，将异常类型独立于数据访问异常。
	 * 		-- @repository 通常用于数据访问层，可以将异常封装程数据访问异常。
	 * spring 中开启注解：
	 * 		配置  AutowiredAnnotationBeanPostProcessor，开启autowired注解。
	 * 		配置 CommonAnnotationBeanPostProcessor 开启spring支持的jsr-250规范注解。
	 * 	注意：1 为了简化配置，spring提供了一个简化配置，只需要 <context:annotation-config/>就可以开启注解。
	 * 		2 为了更灵活的配置，那些类需要纳入或移除spring容器中，我么可以配置：<context:component-scan/>的扫描过滤方式。
	 * 			过滤器类型：注释：
	 * 					类名指定：
	 * 					正则表达式：
	 * 					AspectJ表达式：
	 * 		eg:
				<context:component-scan base-package="com.baobaotao">
				    <context:include-filter type="regex" 
				        expression="com\.baobaotao\.service\..*"/>
				    <context:exclude-filter type="aspectj" 
				        expression="com.baobaotao.util..*"/>
				</context:component-scan>
	 * 		3 注解是类级别的，先于xml配置方式处理。
	 */
	@PostConstruct
	public void annotation(){
		System.out.println("初始化之后执行的方法..............");
	}
	/**
	 * spring-orm模块提供了与对象/关系映射框架的集成 如hibernate  ibatis，jpa 等。
	 * 	与hibernate的集成：不需要些表创建sql，通过持久化对象/关系配置，可以自动创建表。
	 * 					不需要些运行sql语句（或只写一小部分）。
	 * 					需要一个sessionFactory ,并注入一个Datasource.
	 *  与ibatis的集成：    需要写表创建sql，通过持久化对象/关系配置，可以自动创建表。
	 * 				      需要写运行sql语句（或只写一小部分）。
	 * 				      需要一个sessionFactory ,并注入一个Datasource.
	 */
	public void orm(){
		
	}
	/**
	 * spring mvc 模式提供了一个开发web项目的框架。
	 * DispatcherServlet 为核心控制器。控制整个流程。
	 * spring mvc 初始化过程：
	 * 		初始化中 加载配置文件，初始化 handleMapping ,handleAdapt, 
	 * spring mvc 请求处理流程：
	 * 	请求 diapatcherServlet 接受请求，--> 交给handleMapper处理，返回一个 handleExecuteChain(包含拦截器和相应的handle) -->先执行注册的拦截器，在交给HandleAdapt 执行相应的handl,返回一个modleAndView.
	 *  -->加载视图文件，显示在页面上。
	 *  
	 *  spring mvc 用到的设计模式：模板方法模式：不变的与变化的分离，将变化的写成抽象，让子类实现  httpservlet -->httpservletBean-->fremaorkServlet-->dispatcherSevlet
	 *  					  策略模式：配置文件的加载，衍生出很多的子类，用户选择合适的类进行文件加载。
	 *  spring mvc 配置到文件系统中：
	 *   1 开启注解。 cotroller （注意与spring的注解管理区分，以免重复管理）
	 *   2 配置 handelmapper handleAdapt ,viewResolver .
	 *   3 静态文件的处理。
	 *   4 如果要用到json数据，要将jacksonHttp注入到handleAdapt中。
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
