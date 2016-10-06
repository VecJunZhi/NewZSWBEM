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
        	filenameBuffer="�ͻ�¼��ģ��";
        }else if(data.size()>0){
        	filenameBuffer="�ͻ�����";
        }
        //֧����������
        filename=URLEncoder.encode(filenameBuffer, "utf-8")+filenameSuffer;
        //filename=data.size()==0?"�ͻ�¼��ģ��.xls":"�ͻ�����.xls";
		//String excelName = "�����пͻ��б�";
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
		 //titiles = {"��ҵ��Ա","�ͻ�����","�ֻ�����","��ҵ����","������ʽ","�ͻ�״̬","��������","�´θ�������","�ͻ�����","��������","��ҵ����GUID","��ĿGUID","�ͻ�GUID"};//{ "�������", "��������"};
		titles.addAll(Arrays.asList(titiles));
		return titles;
	}

	public <T> XSSFWorkbook generateExcel(String excelName,
			List<String> titles, JSONArray ja,XSSFWorkbook workbook, List<String> zygwList,Class<T> t,ServletOutputStream out,List<String> projectList) {
		//HSSFWorkbook workbook = new HSSFWorkbook();
		// ����������ʵ��
		
		XSSFSheet sheet = workbook.createSheet(excelName);
		sheet.setDefaultColumnWidth(256 * 15);
		//XSSFCellStyle cellStyle=workbook.createCellStyle();
		XSSFRow rowTitle = sheet.createRow(0);
		for (int i = 0; i < titles.size(); i++) {
			createCell(rowTitle, i, XSSFCell.CELL_TYPE_STRING, titles.get(i));//����������
		}
		//JSONArray ja = JSONArray.fromObject(data);
		log.info(t.getSimpleName());
		String className=t.getSimpleName();
		try {
			int j = 0;
			Field fields[] = t.getDeclaredFields();
			for (; j < ja.size(); j++) {
				XSSFRow row = sheet.createRow(j+1);
				if("�ͻ�����".equals(excelName) && ("ZsCustomerImportVo".equals(className) ||"XsCustomerImportVo".equals(className) )){
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
							throw new Exception(" ֻ֧��int��String���͵ı���");
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
							throw new Exception(" ֻ֧��int��String���͵ı���");
						}
					}
				}
			}
			if("�ͻ�¼��ģ��".equals(excelName)){
				if("ZsCustomerImportVo".equals(className)){
					setXSSFValidation(sheet, zygwList.toArray(new String[zygwList.size()]), 1, 5000, 21, 21);//zygw
					setXSSFValidation(sheet, projectList.toArray(new String[projectList.size()]), 1, 5000, 22, 22);//project
				}else{
					setXSSFValidation(sheet, zygwList.toArray(new String[zygwList.size()]), 1, 5000, 23, 23);
					setXSSFValidation(sheet, projectList.toArray(new String[projectList.size()]), 1, 5000, 24, 24);
				
				}
	        }else if("�ͻ�����".equals(excelName)){
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

	// ����Excel��Ԫ��

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
	 * ����ĳЩ�е�ֵֻ������Ԥ�Ƶ�����,��ʾ������.---2003�汾
	 * 
	 * @param sheet
	 *            Ҫ���õ�sheet.
	 * @param textlist
	 *            ��������ʾ������
	 * @param firstRow
	 *            ��ʼ��
	 * @param endRow
	 *            ������
	 * @param firstCol
	 *            ��ʼ��
	 * @param endCol
	 *            ������
	 * @return ���úõ�sheet.
	 */
	public  HSSFSheet setHSSFValidation(HSSFSheet sheet,
			String[] textlist, int firstRow, int endRow, int firstCol,
			int endCol) {
		// ���������б�����
		DVConstraint constraint = DVConstraint
				.createExplicitListConstraint(textlist);
		// ����������Ч�Լ������ĸ���Ԫ����,�ĸ������ֱ��ǣ���ʼ�С���ֹ�С���ʼ�С���ֹ��
		CellRangeAddressList regions = new CellRangeAddressList(firstRow,
				endRow, firstCol, endCol);
		// ������Ч�Զ���
		HSSFDataValidation data_validation_list = new HSSFDataValidation(
				regions, constraint);
		sheet.addValidationData(data_validation_list);
		log.info("set tehst");
		return sheet;
	}
	/**
	 * ����ĳЩ�е�ֵֻ������Ԥ�Ƶ�����,��ʾ������.---2007�汾
	 * 
	 * @param sheet
	 *            Ҫ���õ�sheet.
	 * @param textlist
	 *            ��������ʾ������
	 * @param firstRow
	 *            ��ʼ��
	 * @param endRow
	 *            ������
	 * @param firstCol
	 *            ��ʼ��
	 * @param endCol
	 *            ������
	 * @return ���úõ�sheet.
	 */
	public  XSSFSheet setXSSFValidation(XSSFSheet sheet,
			String[] textlist, int firstRow, int endRow, int firstCol,
			int endCol) {
		// ���������б�����
		/*DVConstraint constraint = DVConstraint
				.createExplicitListConstraint(textlist);*/
		// ����������Ч�Լ������ĸ���Ԫ����,�ĸ������ֱ��ǣ���ʼ�С���ֹ�С���ʼ�С���ֹ��
		log.info(textlist.length);
		CellRangeAddressList regions = new CellRangeAddressList(firstRow,
				endRow, firstCol, endCol);
		// ������Ч�Զ���
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
