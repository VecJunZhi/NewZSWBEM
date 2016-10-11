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
 * mybatis ��һ��ORM��ܣ��������ü��ɿ������ݷ��ʲ㡣
 * 
 * 1 ʲô��mybatis�ӿڰ󶨣�ʵ�ַ�ʽ����Щ��
 * 		�𣺽ӿ�ӳ�������mybaits�����ⶨ��ӿڣ�Ȼ�󽫽ӿ��еķ�����sql�������������������Ϳ���ֱ�ӵ��ýӿڷ�����������Ӧ��sql��䣬���ӷ��㣬��������������sqlsession�ṩ��һЩ������
 * 		�ӿڰ�������ʵ�ַ�ʽ��
 * 			ע�ⷽʽ�󶨣��ڽӿڷ����ϼ�����Ӧ��ע�⡣
 * 			xml���÷�ʽ�󶨣������ռ��·������Ϊ�ӿڵ�ȫ·�����ӿڷ���������mybaits��ʵ�֡�
 * 2 mybatisʵ��һ��һ�ķ�ʽ����Щ��������β�����
 * 		�����ϲ�ѯ��Ƕ�ײ�ѯ����resultMap������assocication�ڵ㡣
 * 3 mybatisʵ��һ�Զ�ķ�ʽ����Щ��������β�����
 * 		�����ϲ�ѯ��Ƕ�ײ�ѯ����resultMap������collection�ڵ㡣
 * 4 mybatis��̬sql����趨�ģ�����Щ�﷨��
 * 		��ͨ��if�����ʵ�ֶ�̬sql�����ɡ�һ����where��trim ��������ʹ�á�
 * 5 mybatis�ĺ��Ĵ�������ʲô��
 * 		�𣺺�����Ϊsqlsession.
 * 6 mybatis�ĺô���ʲô?
 * 		�𣺽�sql����java�����ж���������
 * 		 	sql���д��xml�ļ��У�����ά����
 * 			ͨ�����þͣ������ת��Ϊjavabean����
 * 			ͨ���Լ�дsql��䣬���������ԡ�
 * 7 mybatisʵ������������ѭ���������ϡ�
 * 8 ��λ����������id
 * 		ͨ����insert������ useGeneratedKeys="true" keyProperty="id",�Ӵ���instert�Ķ����л��������id
 * 
 * 9 mybatis��̲��裺
 * 		SqlSessionFactory factory = null;//1 ��ȡsqlsessionFactory 
		session=factory.openSession();//2 ͨ��sqlsessionFactory���sqlsession.
		session.delete("");//3 ͨ��sqlsession���в���
		session.commit();//4 �ύ����
		session.close();//5 �رջỰ��
 * 10 mybatis����ʹ�ã����Ӧ�õ������С�mybatis.xml(SqlMapConfig.xml)�����ļ������õ����ݡ�
 *	  SqlMapConfig.xml�����õ����ݺ�˳�����£� 
			properties�����ԣ�
			settings�����ã�
			typeAliases�����ͱ�����
			typeHandlers�����ʹ�������
			objectFactory�����󹤳���
			plugins�������
			environments�������������Զ���
			environment�����������Զ���
			transactionManager���������
			dataSource������Դ��
			mappers��ӳ������//˵��mapper��·����
 * 11 SqlSessionFactoryBuilder ,SqlSessionFactory,SqlSession �Ĺ�ϵ�����Ե����á�
 *  SqlSessionFactoryBuilder
		�������Ա�ʵ������ʹ�úͶ�����һ�������� SqlSessionFactory���Ͳ�����Ҫ���ˡ���� SqlSessionFactoryBuilder ʵ������ѷ�Χ�Ƿ�����Χ��Ҳ���Ǿֲ�������������
		��������� SqlSessionFactoryBuilder ��������� SqlSessionFactory ʵ����������û��ǲ�Ҫ����һֱ�����Ա�֤���е� XML ������Դ���Ÿ�����Ҫ�����顣
	SqlSessionFactory
		SqlSessionFactory һ����������Ӧ����Ӧ�õ������ڼ�һֱ���ڣ�û���κ����ɶ�������������ؽ���ʹ�� SqlSessionFactory �����ʵ������Ӧ�������ڼ䲻Ҫ�ظ�������Σ�����ؽ� SqlSessionFactory ����Ϊһ�ִ��롰��ζ����bad smell��������� SqlSessionFactory ����ѷ�Χ��Ӧ�÷�Χ���кܶ෽��������������򵥵ľ���ʹ�õ���ģʽ���߾�̬����ģʽ��
	SqlSession
	ÿ���̶߳�Ӧ�������Լ��� SqlSession ʵ����SqlSession ��ʵ�������̰߳�ȫ�ģ�����ǲ��ܱ�����ģ�����������ѵķ�Χ������򷽷���Χ��
	���Բ��ܽ� SqlSession ʵ�������÷���һ����ľ�̬������һ�����ʵ������Ҳ���С�Ҳ�����ܽ� SqlSession ʵ�������÷����κ����͵Ĺ���Χ�У�
	���� Serlvet �ܹ��е� HttpSession���������������ʹ��һ�� Web ��ܣ�Ҫ���� SqlSession ����һ���� HTTP ����������Ƶķ�Χ�С�
	���仰˵��ÿ���յ��� HTTP ���󣬾Ϳ��Դ�һ�� SqlSession������һ����Ӧ���͹ر���������رղ����Ǻ���Ҫ�ģ�
	��Ӧ�ð�����رղ����ŵ� finally ������ȷ��ÿ�ζ���ִ�йرա������ʾ������һ��ȷ�� SqlSession �رյı�׼ģʽ��
	SqlSession session = sqlSessionFactory.openSession();
		try {
		  // do work
		} finally {
		  session.close();
		}
	�����sqlSession�൱��jdbc�е�connection
 * 12 ����mybatis�е�ӳ����ʵ����
 * ӳ�����Ǵ���������ӳ�����Ľӿ�	
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
	 * ����mybatis����ʹ�õ�ʱ���ʵ��
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
