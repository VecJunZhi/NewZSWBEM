package com.zs.busi.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ResultSet ת�� Dto
 * @author 
 *
 */
public class RsToDtoUtil {
    
    private static Log log=LogFactory.getLog(RsToDtoUtil.class);
    

    /**
     * ResultSet ת�� Dto(������int �� String�������͵ı���)
     * @param rs
     * @param claz
     * @return
     */
    public static <T> T tranRsToDto(ResultSet rs, Class<T> claz){
        T t = null;
        try {
            t = claz.newInstance();
            //getFields()���ĳ��������еĹ�����public�����ֶΣ���������
            //getDeclaredFields()���ĳ����������������ֶΣ�������public��private��proteced�����ǲ���������������ֶΡ�
            Field fields[] = claz.getDeclaredFields();
            for(Field f : fields){
                Class<?> type = f.getType();//����ֶ�����
                String fName = f.getName();//����ֶ�����
                String setMethodName = "set"+initialsToUppercase(fName);
                Method method = claz.getMethod(setMethodName, type);
                String typeName = type.getSimpleName();//����Դ�����и����ĵײ���ļ��
         //    System.out.println("reToDaoUtil-type:"+type+"-typeName:"+typeName+"-fName:"+fName);
                if("String".equalsIgnoreCase(typeName)){
                    String value = rs.getString(fName);
                    method.invoke(t, value);
                }else if("Integer".equalsIgnoreCase(typeName) || "int".equalsIgnoreCase(typeName)){
                    int value = rs.getInt(fName);
                    method.invoke(t, value);
                }else{
                    throw new Exception(claz+" ֻ֧��int��String���͵ı���");
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
     * �ַ�������ĸת�ɴ�д��ĸ
     * @param str
     * @return
     */
    public static String initialsToUppercase(String str){
        Character initials = str.charAt(0);
        initials = Character.toUpperCase(initials);
        return initials + str.substring(1);
    }
    
}
