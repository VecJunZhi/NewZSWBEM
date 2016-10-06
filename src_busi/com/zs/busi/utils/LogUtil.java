package com.zs.busi.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogUtil {
	
	public static Log getLog(){
		Log log=LogFactory.getLog(LogUtil.class);
		return log;
	}

}
