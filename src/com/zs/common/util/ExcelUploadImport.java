package com.zs.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.zs.busi.utils.LogUtil;

public class ExcelUploadImport {
	Log logger=LogUtil.getLog();
	/**
	 * �ϴ��ļ����Ҷ�ȡexcel�ļ���list������
	 * @param file���û��ϴ����ļ�
	 * @param request
	 * @param response
	 * @param clas��list�з�װ������
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> uploadFile_And_readExcelToList(MultipartFile file, HttpServletRequest request,HttpServletResponse response,Class<T> clas) {
		if (file == null){
	    	 return null;
	    }
		//һ   �ϴ��ļ�
		String name;
		name = uploadExcelFile(file, request, response);
	    long size = file.getSize();
	    if ((name == null || name.equals("")) && size == 0){
	    	return null;
	    }
	    //��  �õ��ϴ��ļ��İ汾�ţ�
	    String version=getExcelVersion(name);
	    //������ȡexcel��񣬲���װ��list��
	    InputStream in = null;
		try {
			in = file.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    List<T> list = null;
	    list=  readExcelToList(in,version,clas);
	    return list;
	}
	/**
	 * �ϴ�excel�ļ�
	 * @param file���û��ϴ����ļ�
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String uploadExcelFile(MultipartFile file, HttpServletRequest request,HttpServletResponse response) {
		//����һ������Ŀ¼�ļ������û���򴴽�
		String temp = request.getSession().getServletContext().getRealPath("/")+File.separator+"temp"+File.separator;//����Ŀ¼
		File tempFile = new File(temp);
	    if (!tempFile.exists()) {
	      tempFile.mkdirs();
	    }
	    //�������������Ŀ¼�󣬸����ļ�
	    String name = file.getOriginalFilename();// ��ȡ�ļ���
	    logger.info(name);
	    //name = new String(name.getBytes("GBK"),"UTF-8");
	    logger.info("filename "+name);
	    SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		String date = d.format(new Date());
	    String fileName=date+"_"+name;//��װ�ļ���������+ԭʼ�ļ���
	    temp=temp+fileName;//Ŀ¼+�ļ���   �������ļ�
	    File outFile=new File(temp);
	    try {
			FileUtils.copyInputStreamToFile(file.getInputStream(),outFile);
		} catch (IOException e) {
			logger.info("�ϴ�ʧ��");
		}
	    return name;
	}
	/**
	 * �����ϴ��ļ������ƻ�ȡ��汾�ţ�����׺
	 * @param fileName
	 * @return
	 */
	public String getExcelVersion(String fileName){
		String version=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		return version;
	}
	/**
	 * ��ȡexcel�ļ���װ��list�����У����Լ��� 2003��2007�汾��
	 * @param in��������
	 * @param type���ϴ��ļ��ĺ�׺
	 * @param clas�������з�װ�������ֽ���
	 * @return
	 * @throws Exception
	 */
	 public <T> List<T> readExcelToList(InputStream in,String type,Class<T> clas) {
		 List<T> list = new ArrayList<T>();
		 if("xlsx".equalsIgnoreCase(type)){
		    	list = readExcel_Xlsx_ToList(in,clas);
		 }else if("xls".equalsIgnoreCase(type)){
		    	try {
					list = readExcel_Xls_ToList(in,clas);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 return list;
	}
	 /**
	  * ��ȡexcel�ļ���װ��list�����У�ֻ���� 2003�汾��
	  * @param is
	  * @param clas
	  * @return
	  * @throws Exception
	  */
	@SuppressWarnings("resource")
	private <T> List<T> readExcel_Xls_ToList(InputStream is,Class<T> clas) throws Exception{
		HSSFWorkbook hssfWorkbook = null;
		hssfWorkbook = new HSSFWorkbook(is);
		T t=null;
		List<T> list = new ArrayList<T>();
		Field fields[] =clas.getDeclaredFields();
	    // ѭ��������Sheet
	    for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){
	    	HSSFSheet hssfSheet;
	    	hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	    	if (hssfSheet == null) {
	    		continue;
	    	}
	    	// ѭ����Row
	    	for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
		        HSSFRow hssfRow = hssfSheet.getRow(rowNum);
		        logger.info(fields.length);
		        logger.info(hssfRow.getLastCellNum());
		        if(hssfRow.getLastCellNum()!=fields.length){
		        	logger.info("��ʽ����ȷ");
		        	break;
		        }
		        t=clas.newInstance();
		        for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
		        		
		        		Field f=fields[i];
		        		String fName=f.getName();
		        		Class<?> fType=f.getType();
		        		String setMethodName = "set"+initialsToUppercase(fName);
		        		logger.info(setMethodName);
		                Method method = clas.getMethod(setMethodName, fType);
		        		//��ʼע������
		                HSSFCell brandIdHSSFCell = hssfRow.getCell(i);
		                int k=brandIdHSSFCell.getCellType();
		                String value="";
		                logger.info(brandIdHSSFCell.getNumericCellValue());
		                if(k==0){
		                	logger.info("k "+k);
		                	value=brandIdHSSFCell==null?"0":brandIdHSSFCell.getNumericCellValue()+"";
		                }else if(k==1){
		                	value=brandIdHSSFCell==null?"null":brandIdHSSFCell.getStringCellValue();
		                }
		                logger.info(k);
		                logger.info(value);
		                logger.info(t);
		                method.invoke(t, value);
		                
		        }
		        list.add(t);
	    	}
	    }
	    return list;
	}
	/**
	 * ��ȡexcel�ļ���װ��list�����У�ֻ���� 2007�汾��
	 * @param is
	 * @param clas
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	private <T> List<T> readExcel_Xlsx_ToList(InputStream is,Class<T> clas) {
		XSSFWorkbook hssfWorkbook = null;
		List<T> list = new ArrayList<T>();
		try {
			hssfWorkbook = new XSSFWorkbook(is);
			T t=null;
			
			Field fields[] =clas.getDeclaredFields();
		    // ѭ��������Sheet
			logger.info(hssfWorkbook.getNumberOfSheets());
		    for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet hssfSheet;
				hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				// ѭ����Row
				logger.info(hssfSheet.getLastRowNum());
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow hssfRow = hssfSheet.getRow(rowNum);
					logger.info(hssfRow.getLastCellNum());
					logger.info(fields.length);
					if(hssfRow.getLastCellNum()!=fields.length){
			        	logger.info("��ʽ����ȷ");
			        	break;
			        }
					t=clas.newInstance();
				    for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
		        		Field f=fields[i];
		        		String fName=f.getName();
		        		Class<?> fType=f.getType();
		        		String setMethodName = "set"+initialsToUppercase(fName);
		                Method method = clas.getMethod(setMethodName, fType);
		                //��ʼע������
				    	XSSFCell brandIdHSSFCell = hssfRow.getCell(i);
				    	String value="";
				    	if(brandIdHSSFCell==null){
		                	value="";
		                }else{
		                	int k=brandIdHSSFCell.getCellType();
			                if(k==0){
			                	value=new Double(brandIdHSSFCell.getNumericCellValue()).toString();
			                	DecimalFormat df = new DecimalFormat("#");
			                	logger.info(df.format(brandIdHSSFCell.getNumericCellValue()));
			                	value=df.format(brandIdHSSFCell.getNumericCellValue());
			                }else if(k==1){
			                	value=brandIdHSSFCell==null?"null":brandIdHSSFCell.getStringCellValue();
			                }
		                }
				    	method.invoke(t, value);
				    	//logger.info(setMethodName+" "+value);
				    }
				    list.add(t);
				        
				}
		    }
		    return list;
		} catch (Exception e) {
			logger.info("e" +list);
			return list;
		}
		
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

