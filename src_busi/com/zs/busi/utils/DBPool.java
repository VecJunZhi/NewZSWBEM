package com.zs.busi.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBPool {/*
	public static javax.sql.DataSource ds;
	public static javax.sql.DataSource ds2000;
	static {
		try {
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			Object obj = (Object) ctx.lookup("jdbc/sqlServer");
			ds = (javax.sql.DataSource) obj;
			Object obj2000 = (Object) ctx.lookup("jdbc/sqlServer2000");
			ds2000 = (javax.sql.DataSource) obj2000;
			System.out.println("conn ");
			
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" fd "+ e);
		}
	}
*/}
