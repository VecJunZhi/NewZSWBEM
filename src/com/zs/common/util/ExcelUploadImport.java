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
	 * 上传文件并且读取excel文件到list集合中
	 * @param file：用户上传的文件
	 * @param request
	 * @param response
	 * @param clas：list中封装的类型
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> uploadFile_And_readExcelToList(MultipartFile file, HttpServletRequest request,HttpServletResponse response,Class<T> clas) {
		if (file == null){
	    	 return null;
	    }
		//一   上传文件
		String name;
		name = uploadExcelFile(file, request, response);
	    long size = file.getSize();
	    if ((name == null || name.equals("")) && size == 0){
	    	return null;
	    }
	    //二  得到上传文件的版本号：
	    String version=getExcelVersion(name);
	    //三：读取excel表格，并封装到list中
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
	 * 上传excel文件
	 * @param file：用户上传的文件
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String uploadExcelFile(MultipartFile file, HttpServletRequest request,HttpServletResponse response) {
		//步骤一：创建目录文件，如果没有则创建
		String temp = request.getSession().getServletContext().getRealPath("/")+File.separator+"temp"+File.separator;//创建目录
		File tempFile = new File(temp);
	    if (!tempFile.exists()) {
	      tempFile.mkdirs();
	    }
	    //步骤二：创建好目录后，复制文件
	    String name = file.getOriginalFilename();// 获取文件名
	    logger.info(name);
	    //name = new String(name.getBytes("GBK"),"UTF-8");
	    logger.info("filename "+name);
	    SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		String date = d.format(new Date());
	    String fileName=date+"_"+name;//组装文件名，日期+原始文件名
	    temp=temp+fileName;//目录+文件名   ，复制文件
	    File outFile=new File(temp);
	    try {
			FileUtils.copyInputStreamToFile(file.getInputStream(),outFile);
		} catch (IOException e) {
			logger.info("上传失败");
		}
	    return name;
	}
	/**
	 * 根据上传文件的名称获取其版本号，即后缀
	 * @param fileName
	 * @return
	 */
	public String getExcelVersion(String fileName){
		String version=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		return version;
	}
	/**
	 * 读取excel文件封装到list集合中，可以兼容 2003和2007版本。
	 * @param in：输入流
	 * @param type：上传文件的后缀
	 * @param clas：集合中封装的类型字节码
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
	  * 读取excel文件封装到list集合中，只兼容 2003版本。
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
	    // 循环工作表Sheet
	    for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){
	    	HSSFSheet hssfSheet;
	    	hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	    	if (hssfSheet == null) {
	    		continue;
	    	}
	    	// 循环行Row
	    	for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
		        HSSFRow hssfRow = hssfSheet.getRow(rowNum);
		        logger.info(fields.length);
		        logger.info(hssfRow.getLastCellNum());
		        if(hssfRow.getLastCellNum()!=fields.length){
		        	logger.info("格式不正确");
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
		        		//开始注入数据
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
	 * 读取excel文件封装到list集合中，只兼容 2007版本。
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
		    // 循环工作表Sheet
			logger.info(hssfWorkbook.getNumberOfSheets());
		    for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet hssfSheet;
				hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				// 循环行Row
				logger.info(hssfSheet.getLastRowNum());
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow hssfRow = hssfSheet.getRow(rowNum);
					logger.info(hssfRow.getLastCellNum());
					logger.info(fields.length);
					if(hssfRow.getLastCellNum()!=fields.length){
			        	logger.info("格式不正确");
			        	break;
			        }
					t=clas.newInstance();
				    for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
		        		Field f=fields[i];
		        		String fName=f.getName();
		        		Class<?> fType=f.getType();
		        		String setMethodName = "set"+initialsToUppercase(fName);
		                Method method = clas.getMethod(setMethodName, fType);
		                //开始注入数据
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

