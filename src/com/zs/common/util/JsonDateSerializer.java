package com.zs.common.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 
 * @author JiaRui
 *
 */
public class JsonDateSerializer {
	
	/**
	 * springMVC ±º‰ Ù–‘±‡º≠∆˜
	 * @param srdb
	 */
	@InitBinder
	public void SimpleDateFormat(ServletRequestDataBinder srdb){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		srdb.registerCustomEditor(Date.class, new CustomDateEditor(sdf,true));
	}
}
