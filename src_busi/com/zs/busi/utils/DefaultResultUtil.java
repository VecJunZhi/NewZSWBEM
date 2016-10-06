package com.zs.busi.utils;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DefaultResultUtil {

    /**
     * 
     * @param obj
     * @return
     */
    public static JSONObject getDefaultResult(Object obj){
        JSONArray ja = JSONArray.fromObject(obj);
        JSONObject data = new JSONObject();
        data.put("data", ja);
        return data;
    }
    
    /**
     * 
     * @return
     */
    public static JSONObject getModificationResult(Object obj){
        JSONObject data = new JSONObject();
        data.put("data", obj);
        return data;
    }
    
	public static boolean isNum(String str){
		return str.matches("[0-9]*");
	}
    
   
    
    public static void main(String[] args) {
        getDefaultResult("true");
    }
}
