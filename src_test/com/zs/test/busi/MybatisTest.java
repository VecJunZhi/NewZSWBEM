package com.zs.test.busi;


import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;


public class MybatisTest {
	static Log log = LogFactory.getLog(MybatisTest.class);
	static SqlSession session=null;
	static SqlSessionFactory sessionFactory=null;
	static{
		
        String resource = "mybatisConf.xml";
       
        InputStream is = MybatisTest.class.getClassLoader().getResourceAsStream(resource);
       
         sessionFactory = new SqlSessionFactoryBuilder().build(is);
        
        
         		
	}
		
		public static void selectOne(){
			
			String statement = "com.zs.mybatis.test.userapper.getUser";
	       
			session = sessionFactory.openSession();
	        User user = session.selectOne(statement, 1);
	        System.out.println(user);
		}
		public static void selectByMap(){
			
	        String statement3="com.zs.mybatis.test.userapper.getUserDulParmMap";
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("id", 1);
	        map.put("age", 28);
	        session = sessionFactory.openSession();
	        User user3=session.selectOne(statement3, map);
	        System.out.println("user3 "+user3);
		}
		public static void selectByBean(){
			
	        String statement4="com.zs.mybatis.test.userapper.getUserDulParmBean";
	        User user4_1 = new User(1, "", 28);
	        session = sessionFactory.openSession();
	        User user4=session.selectOne(statement4, user4_1);
	        System.out.println("user4 "+user4);
		}
		public static void selectResultList(){
			
	        String statement2 ="com.zs.mybatis.test.userapper.getUserByAge";
	        session = sessionFactory.openSession();
	        List<User>list= session.selectList(statement2, 27);
	        for (User user2 : list) {
				System.out.println(user2.getName());
			}
		}
		public static void insertUser(){
			
	        String statement5 ="com.zs.mybatis.test.userapper.insertUserByMap";
	        Map<String, Object> map5 = new HashMap<String, Object>();
	        map5.put("name", "yzj");
	        map5.put("age", 27);
	        session = sessionFactory.openSession();
	        int int_= session.insert(statement5, map5);
	        session.commit();
	        System.out.println(int_);
		}
		public static void updateUser(){
			
	        String statement6 ="com.zs.mybatis.test.userapper.updateUserByBean";
	        User user6_1 = new User(1, "yzjfe", 28);
	        session = sessionFactory.openSession();
	       int int_up= session.update(statement6, user6_1);
	       session.commit();
	        System.out.println("update "+int_up);
	        
		}
		public static void deleteUser(){
			
	        String statement7="com.zs.mybatis.test.userapper.deleteUserByBean";
	        session = sessionFactory.openSession();
	        session.delete(statement7, 36);
	        session.commit();
		}
		public static void insertDulp(){
			List<User> list = new ArrayList<User>();
			User user=new User("afe", 22);
			User user1 =new User( "re", 23);
			list.add(user);
			list.add(user1);
			String statement="com.zs.mybatis.test.userapper.insertDulp";
			session=sessionFactory.openSession();
			int k=session.insert(statement, list);
			session.commit();
			session.close();
			System.out.println(k);
			
		}
		public static void insertReturnKey(){
			User user=new User(0, "getkey22", 22);
			String statement="com.zs.mybatis.test.userapper.insertReturnKey";
			session=sessionFactory.openSession();
			int k=session.insert(statement, user);
			session.commit();
			session.close();
			System.out.println(user.getId());
		}
		
		public static void crdTransaction(){
			String statement="testSql";
			TransactionFactory factory = new JdbcTransactionFactory();
			session=sessionFactory.openSession();
			Transaction trans=factory.newTransaction(session.getConnection());
			try {
				session.insert(statement);
				session.update(statement);
			} catch (Exception e) {
				try {
					trans.rollback();//事务回滚
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}finally{
				try {
					trans.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public static void crduInterface(int id){
			System.out.println("fe");
			session=sessionFactory.openSession();
			UserMapper mapper=session.getMapper(UserMapper.class);
			User user=mapper.getUser(id);
			System.out.println(user);
			
		}
		
/*		public static void crduInterfaceOtherPath(int id){
			System.out.println("fe");
			session=sessionFactory.openSession();
			userapper mapper=session.getMapper(userapper.class);
			User user=mapper.getUser(id);
			System.out.println(user.getAge());
		}*/
		public static void main(String[] args) throws IOException {
			//deleteUser();
			//insertDulp();
			//insertReturnKey();
			//selectOne();
			crduInterface(2);
			//crduInterfaceOtherPath(2);
			//selectResultList();
	    }
		
		public void test(){
			log.info("df");
			crduInterface(2);
		}
}
