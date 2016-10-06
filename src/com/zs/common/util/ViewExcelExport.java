package com.zs.common.util;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.WHITE;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.zs.busi.utils.LogUtil;

public class ViewExcelExport extends AbstractExcelView {
	
	Log log=LogUtil.getLog();
	@SuppressWarnings("unchecked")
	@Override
	protected  void  buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook old_workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XSSFWorkbook workbook=new XSSFWorkbook();
		log.info("begin.....");
		String filenameBuffer="";
		String filenameSuffer = ".xlsx";
		String filename="";
		JSONArray data=(JSONArray)model.get("list");
        log.info(data.size());
        if(data.size()==0){
        	filenameBuffer="客户录入模板";
        }else if(data.size()>0){
        	filenameBuffer="客户资料";
        }
        //支持中文名字
        filename=URLEncoder.encode(filenameBuffer, "utf-8")+filenameSuffer;
        //filename=data.size()==0?"客户录入模板.xls":"客户资料.xls";
		//String excelName = "跟进中客户列表";
		response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition", "attachment; filename="+filename);
        String[] titiles = (String[]) model.get("titles");
        List<String> titles=getTitles(titiles);
		List<String> zygwList= (List<String>) model.get("zygwList");
		Class t=(Class) model.get("class");
		List<String> projectList= (List<String>) model.get("projectList");
		log.info(t.getName());
		ServletOutputStream out = response.getOutputStream();
        generateExcel(filenameBuffer, titles, data,workbook,zygwList,t,out,projectList);
	}
	
	public  List<String> getTitles(String[] titiles) {
		List<String> titles = new ArrayList<String>();
		 //titiles = {"新业务员","客户姓名","手机号码","置业顾问","跟进方式","客户状态","到期日期","下次跟进日期","客户意向","创建日期","置业顾问GUID","项目GUID","客户GUID"};//{ "辅料序号", "辅料名称"};
		titles.addAll(Arrays.asList(titiles));
		return titles;
	}

	public <T> XSSFWorkbook generateExcel(String excelName,
			List<String> titles, JSONArray ja,XSSFWorkbook workbook, List<String> zygwList,Class<T> t,ServletOutputStream out,List<String> projectList) {
		//HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建工作表实例
		
		XSSFSheet sheet = workbook.createSheet(excelName);
		sheet.setDefaultColumnWidth(256 * 15);
		//XSSFCellStyle cellStyle=workbook.createCellStyle();
		XSSFRow rowTitle = sheet.createRow(0);
		for (int i = 0; i < titles.size(); i++) {
			createCell(rowTitle, i, XSSFCell.CELL_TYPE_STRING, titles.get(i));//产生标题列
		}
		//JSONArray ja = JSONArray.fromObject(data);
		log.info(t.getSimpleName());
		String className=t.getSimpleName();
		try {
			int j = 0;
			Field fields[] = t.getDeclaredFields();
			for (; j < ja.size(); j++) {
				XSSFRow row = sheet.createRow(j+1);
				if("客户资料".equals(excelName) && ("ZsCustomerImportVo".equals(className) ||"XsCustomerImportVo".equals(className) )){
					for (int k = 0; k< fields.length; k++) {
						Field f = fields[k];
						Class<?> type = f.getType();
						String fName = f.getName();
						String typeName = type.getSimpleName();
						if ("String".equalsIgnoreCase(typeName)) {
							String value = ja.getJSONObject(j).getString(fName);
							createCell(row, k, XSSFCell.CELL_TYPE_STRING, value);
						} else if ("Integer".equalsIgnoreCase(typeName)
								|| "int".equalsIgnoreCase(typeName)) {
							int value = ja.getJSONObject(j).getInt(fName);
							createCell(row, k, XSSFCell.CELL_TYPE_NUMERIC, value);
						} else {
							throw new Exception(" 只支持int和String类型的变量");
						}
					}
				}else{
					for (int k = 1; k< fields.length; k++) {
						Field f = fields[k];
						Class<?> type = f.getType();
						String fName = f.getName();
						String typeName = type.getSimpleName();
						if ("String".equalsIgnoreCase(typeName)) {
							String value = ja.getJSONObject(j).getString(fName);
							createCell(row, k, XSSFCell.CELL_TYPE_STRING, value);
						} else if ("Integer".equalsIgnoreCase(typeName)
								|| "int".equalsIgnoreCase(typeName)) {
							int value = ja.getJSONObject(j).getInt(fName);
							createCell(row, k, XSSFCell.CELL_TYPE_NUMERIC, value);
						} else {
							throw new Exception(" 只支持int和String类型的变量");
						}
					}
				}
			}
			if("客户录入模板".equals(excelName)){
				if("ZsCustomerImportVo".equals(className)){
					setXSSFValidation(sheet, zygwList.toArray(new String[zygwList.size()]), 1, 5000, 21, 21);//zygw
					setXSSFValidation(sheet, projectList.toArray(new String[projectList.size()]), 1, 5000, 22, 22);//project
				}else{
					setXSSFValidation(sheet, zygwList.toArray(new String[zygwList.size()]), 1, 5000, 23, 23);
					setXSSFValidation(sheet, projectList.toArray(new String[projectList.size()]), 1, 5000, 24, 24);
				
				}
	        }else if("客户资料".equals(excelName)){
	        	log.info(className);
	        	if("ZsCustomerExportVo".equals(className)){
	        		sheet.setColumnHidden(10, true);
	        		setXSSFValidation(sheet, zygwList.toArray(new String[zygwList.size()]), 1, ja.size(), 0, 0);
	        	}else if("ZsCustomerImportVo".equals(className)){
	        		setXSSFValidation(sheet, zygwList.toArray(new String[zygwList.size()]), 1, 5000, 21, 21);//zygw
					setXSSFValidation(sheet, projectList.toArray(new String[projectList.size()]), 1, 5000, 22, 22);//project
	        	}else if("XsCustomerImportVo".equals(className)){
	        		setXSSFValidation(sheet, zygwList.toArray(new String[zygwList.size()]), 1, 5000, 23, 23);
					setXSSFValidation(sheet, projectList.toArray(new String[projectList.size()]), 1, 5000, 24, 24);
	        	}else{
	        		sheet.setColumnHidden(23, true);
					sheet.setColumnHidden(24, true);
					sheet.setColumnHidden(25, true);
					sheet.setColumnHidden(26, true);
					setXSSFValidation(sheet, zygwList.toArray(new String[zygwList.size()]), 1, ja.size(), 0, 0);
	        	}
	        }
			workbook.write(out);
			out.flush();
			out.close();
		} catch (InstantiationException e) {
			log.error("", e);
		} catch (IllegalAccessException e) {
			log.error("", e);
		} catch (Exception e) {
			log.error("", e);
		}
		return workbook;
	}

	// 创建Excel单元格

	private void createCell(XSSFRow row, int column, int cellType, Object value) {

		XSSFCell cell = row.createCell(column);
		//cell.getCellStyle().setLocked(true);
		switch (cellType) {
			case XSSFCell.CELL_TYPE_BLANK: {
	
			}
				break;
			case XSSFCell.CELL_TYPE_STRING: {
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(value.toString());
				
			}
				break;
			case XSSFCell.CELL_TYPE_NUMERIC: {
				cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(Double.parseDouble(value.toString()));
			}
				break;
			default:
				break;

		}

	}
	/**
	 * 设置某些列的值只能输入预制的数据,显示下拉框.---2003版本
	 * 
	 * @param sheet
	 *            要设置的sheet.
	 * @param textlist
	 *            下拉框显示的内容
	 * @param firstRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @param firstCol
	 *            开始列
	 * @param endCol
	 *            结束列
	 * @return 设置好的sheet.
	 */
	public  HSSFSheet setHSSFValidation(HSSFSheet sheet,
			String[] textlist, int firstRow, int endRow, int firstCol,
			int endCol) {
		// 加载下拉列表内容
		DVConstraint constraint = DVConstraint
				.createExplicitListConstraint(textlist);
		// 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
		CellRangeAddressList regions = new CellRangeAddressList(firstRow,
				endRow, firstCol, endCol);
		// 数据有效性对象
		HSSFDataValidation data_validation_list = new HSSFDataValidation(
				regions, constraint);
		sheet.addValidationData(data_validation_list);
		log.info("set tehst");
		return sheet;
	}
	/**
	 * 设置某些列的值只能输入预制的数据,显示下拉框.---2007版本
	 * 
	 * @param sheet
	 *            要设置的sheet.
	 * @param textlist
	 *            下拉框显示的内容
	 * @param firstRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @param firstCol
	 *            开始列
	 * @param endCol
	 *            结束列
	 * @return 设置好的sheet.
	 */
	public  XSSFSheet setXSSFValidation(XSSFSheet sheet,
			String[] textlist, int firstRow, int endRow, int firstCol,
			int endCol) {
		// 加载下拉列表内容
		/*DVConstraint constraint = DVConstraint
				.createExplicitListConstraint(textlist);*/
		// 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
		log.info(textlist.length);
		CellRangeAddressList regions = new CellRangeAddressList(firstRow,
				endRow, firstCol, endCol);
		// 数据有效性对象
		XSSFDataValidationHelper helper =new XSSFDataValidationHelper(sheet);
		//helper.c
		//String excelName=sheet.getSheetName();
		DataValidationConstraint constraint=helper.createExplicitListConstraint(textlist);
		
		XSSFDataValidation data_validation_list = (XSSFDataValidation) helper.createValidation(constraint, regions);//new XSSFDataValidation(constraint, regions, ctDataValidation)
		sheet.addValidationData(data_validation_list);
		log.info("set tehst");
		return sheet;
	}

}
