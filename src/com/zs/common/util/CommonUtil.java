package com.zs.common.util;

import java.text.DecimalFormat;

public class CommonUtil {
	
	//����λ��Ԫ��Ϊ��Ԫ������ʽ�����֣�
	/**
	 * 
	 * @param d    ��Ҫת��������
	 * @param unitRadix   ת���ĵ�λ����
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
