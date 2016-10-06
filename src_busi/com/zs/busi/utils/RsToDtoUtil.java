package com.zs.busi.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ResultSet 转成 Dto
 * @author 
 *
 */
public class RsToDtoUtil {
    
    private static Log log=LogFactory.getLog(RsToDtoUtil.class);
    

    /**
     * ResultSet 转成 Dto(仅包含int 和 String两种类型的变量)
     * @param rs
     * @param claz
     * @return
     */
    public static <T> T tranRsToDto(ResultSet rs, Class<T> claz){
        T t = null;
        try {
            t = claz.newInstance();
            //getFields()获得某个类的所有的公共（public）的字段，包括父类
            //getDeclaredFields()获得某个类的所有申明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
            Field fields[] = claz.getDeclaredFields();
            for(Field f : fields){
                Class<?> type = f.getType();//获得字段类型
                String fName = f.getName();//获得字段名称
                String setMethodName = "set"+initialsToUppercase(fName);
                Method method = claz.getMethod(setMethodName, type);
                String typeName = type.getSimpleName();//返回源代码中给出的底层类的简称
         //    System.out.println("reToDaoUtil-type:"+type+"-typeName:"+typeName+"-fName:"+fName);
                if("String".equalsIgnoreCase(typeName)){
                    String value = rs.getString(fName);
                    method.invoke(t, value);
                }else if("Integer".equalsIgnoreCase(typeName) || "int".equalsIgnoreCase(typeName)){
                    int value = rs.getInt(fName);
                    method.invoke(t, value);
                }else{
                    throw new Exception(claz+" 只支持int和String类型的变量");
                }
            }
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e){
            log.error("", e);
        }
        return t;
    }
    
    /**
     * 字符串首字母转成大写字母
     * @param str
     * @return
     */
    public static String initialsToUppercase(String str){
        Character initials = str.charAt(0);
        initials = Character.toUpperCase(initials);
        return initials + str.substring(1);
    }
    
}
