package com.zst.test.rbac.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * mybatis 是一个ORM框架，可以利用集成开发数据访问层。
 * 
 * 1 什么是mybatis接口绑定，实现方式有哪些？
 * 		答：接口映射就是在mybaits中任意定义接口，然后将接口中的方法和sql语句绑定起来，这样做，就可以直接调用接口方法来操作相应的sql语句，更加方便，而不仅仅局限于sqlsession提供的一些方法。
 * 		接口绑定有两种实现方式：
 * 			注解方式绑定，在接口方法上加入相应的注解。
 * 			xml配置方式绑定：命名空间的路径必须为接口的全路径，接口方法必须在mybaits中实现。
 * 2 mybatis实现一对一的方式有哪些，具体如何操作。
 * 		答：联合查询和嵌套查询，在resultMap中配置assocication节点。
 * 3 mybatis实现一对多的方式有哪些，具体如何操作？
 * 		答：联合查询和嵌套查询，在resultMap中配置collection节点。
 * 4 mybatis动态sql如何设定的，有哪些语法？
 * 		答：通过if语可以实现动态sql的生成。一般与where，trim 等语句配合使用。
 * 5 mybatis的核心处理类是什么？
 * 		答：核心类为sqlsession.
 * 6 mybatis的好处是什么?
 * 		答：将sql语句从java程序中独立出来。
 * 		 	sql语句写在xml文件中，便于维护。
 * 			通过配置就，将结果转换为javabean对象。
 * 			通过自己写sql语句，提高了灵活性。
 * 7 mybatis实现批量操作，循环遍历集合。
 * 8 如何获得自增长的id
 * 		通过在insert中配置 useGeneratedKeys="true" keyProperty="id",从传入instert的对象中获得自增长id
 * 
 * 9 mybatis编程步骤：
 * 		SqlSessionFactory factory = null;//1 获取sqlsessionFactory 
		session=factory.openSession();//2 通过sqlsessionFactory获得sqlsession.
		session.delete("");//3 通过sqlsession进行操作
		session.commit();//4 提交事务
		session.close();//5 关闭会话。
 * 10 mybatis单独使用，如何应用到程序中。mybatis.xml(SqlMapConfig.xml)配置文件中配置的内容。
 *	  SqlMapConfig.xml中配置的内容和顺序如下： 
			properties（属性）
			settings（配置）
			typeAliases（类型别名）
			typeHandlers（类型处理器）
			objectFactory（对象工厂）
			plugins（插件）
			environments（环境集合属性对象）
			environment（环境子属性对象）
			transactionManager（事务管理）
			dataSource（数据源）
			mappers（映射器）//说明mapper的路径。
 * 11 SqlSessionFactoryBuilder ,SqlSessionFactory,SqlSession 的关系及各自的作用。
 *  SqlSessionFactoryBuilder
		这个类可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了。因此 SqlSessionFactoryBuilder 实例的最佳范围是方法范围（也就是局部方法变量）。
		你可以重用 SqlSessionFactoryBuilder 来创建多个 SqlSessionFactory 实例，但是最好还是不要让其一直存在以保证所有的 XML 解析资源开放给更重要的事情。
	SqlSessionFactory
		SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由对它进行清除或重建。使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，多次重建 SqlSessionFactory 被视为一种代码“坏味道（bad smell）”。因此 SqlSessionFactory 的最佳范围是应用范围。有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。
	SqlSession
	每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的范围是请求或方法范围。
	绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。也绝不能将 SqlSession 实例的引用放在任何类型的管理范围中，
	比如 Serlvet 架构中的 HttpSession。如果你现在正在使用一种 Web 框架，要考虑 SqlSession 放在一个和 HTTP 请求对象相似的范围中。
	换句话说，每次收到的 HTTP 请求，就可以打开一个 SqlSession，返回一个响应，就关闭它。这个关闭操作是很重要的，
	你应该把这个关闭操作放到 finally 块中以确保每次都能执行关闭。下面的示例就是一个确保 SqlSession 关闭的标准模式：
	SqlSession session = sqlSessionFactory.openSession();
		try {
		  // do work
		} finally {
		  session.close();
		}
	这里的sqlSession相当与jdbc中的connection
 * 12 解释mybatis中的映射器实例。
 * 映射器是创建用来绑定映射语句的接口	
 * @author yzj
 *
 */
public class Mybatis {
	
	public void sqlsession(){
		SqlSession session;
		SqlSessionFactory factory = null;
		session=factory.openSession();
		session.delete("");
		session.commit();
		session.close();
	}
	/**
	 * 基于mybatis单独使用的时候的实例
	 */
	public void example(){
		SqlSessionFactory sqlSessionFactory = null;
		String resource = "org/mybatis/example/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			try {
			  BlogMapper mapper = session.getMapper(BlogMapper.class);
			  Blog blog = mapper.selectBlog(101);
			} finally {
			  session.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
