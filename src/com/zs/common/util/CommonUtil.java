package com.zs.common.util;

import java.text.DecimalFormat;

public class CommonUtil {
	
	//将单位由元改为万元，并格式化数字，
	/**
	 * 
	 * @param d    需要转换的数据
	 * @param unitRadix   转换的单位进制
	 * @return
	 */
	public static String formate(Object obj,double unitRadix){
		if("String".equals(obj.getClass().getSimpleName())){
			obj=Double.parseDouble((String)obj);
		}
		
		DecimalFormat df = new DecimalFormat("0.00");
		String db=df.format(obj);
		Double doub=Double.parseDouble(db)/unitRadix;
		DecimalFormat df2 = new DecimalFormat("#,###.00");
		String dfd=df2.format(doub);
		return dfd;
	}
	public static String formatTosepara(double data) {
		DecimalFormat df = new DecimalFormat("#,##0.00"); 
		return df.format(data);
		}
	public static void main(String[] args) {
		
		  System.out.println(formate("252252.1",1));
		// System.out.println(formatTosepara(323244.667));
	}
}
