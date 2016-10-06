package com.zs.oa.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class DBpool {
	public static javax.sql.DataSource ds;
	public static javax.sql.DataSource ds_zs;
	static Log log =LogFactory.getLog(DBpool.class);

	static {
		try {
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			Object obj = (Object) ctx.lookup("jdbc/Mysql");
			ds = (javax.sql.DataSource) obj;
			
			Object obj2 = ctx.lookup("jdbc/Mysqlzswbem");
    		ds_zs = (DataSource) obj2;
			/*@SuppressWarnings("deprecation")
			BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-mybatis.xml"));
			ds_zs= beanFactory.getBean("dataSourceBusi", DataSource.class);*/
    	} catch (NamingException e) {
			log.info("conn is bad");
			e.printStackTrace();
		}
	}
}
