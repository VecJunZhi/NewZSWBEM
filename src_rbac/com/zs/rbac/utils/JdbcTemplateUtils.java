package com.zs.rbac.utils;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */
public class JdbcTemplateUtils {

    private static JdbcTemplate jdbcTemplate;
    public static DataSource ds;
    static Log log=LogFactory.getLog(JdbcTemplateUtils.class);
    public static JdbcTemplate jdbcTemplate() {
        if(jdbcTemplate == null) {
            jdbcTemplate = createDataSource();
        }
        return jdbcTemplate;
    }

/*    private static JdbcTemplate createJdbcTemplate() {
    	try {
    		Context initCtx = new InitialContext();
        	Context ctx = (Context) initCtx.lookup("java:comp/env");
    		Object obj = ctx.lookup("jdbc/Mysqlzswbem");
    		ds = (DataSource) obj;
    		log.info(ds);
		} catch (Exception e) {
			log.info(e);
		}
    	return new JdbcTemplate(ds);
    }*/
    private static JdbcTemplate createDataSource(){
    	@SuppressWarnings("deprecation")
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-mybatis.xml"));
        ds= beanFactory.getBean("dataSourceBusi", DataSource.class);
        log.info("datasourcr "+ds);
        return new JdbcTemplate(ds);
    }

}
