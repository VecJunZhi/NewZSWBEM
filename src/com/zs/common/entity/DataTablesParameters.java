package com.zs.common.entity;

/**
 * DataTables������ò���ʵ��
 * @author JiaRui
 */
public class DataTablesParameters {
	private int draw;
	
	private int start;
	
	private int length;
	
	private String orderDir;
	
	private String orderColumn;
	
	private String[] columnArray;
	
	
	/**
	 * ��ȡ��Ҫ������г���
	 * @return
	 */
	public String getOrderColumn() {
		if(this.columnArray == null &&  "".equals(this.orderColumn))	//û�������������鼰ҳ���û��ȡ��ֵʱ
			return "";
		else
			return columnArray[Integer.parseInt(this.orderColumn)];
	}
	
	/**
	 * ��ȡ����ʽ
	 * @return 
	 */
	public String getOrderDir() {
		if(this.columnArray == null && "".equals(this.orderColumn))
			return "";
		else
			return orderDir;
	}

	/**
	 * ����Ĭ������ʽ asc desc
	 * @param orderDir
	 */
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	

	/**
	 * ��ȡ�б��ֶ�����
	 * @return
	 */
	public String[] getColumnArray() {
		return columnArray;
	}

	/**
	 * ����Ҫ���������ñ��б��ֶ�ֵ��<br>
	 * ��ҳ����б����ζ�Ӧ����columnArray��Ϊnull�������<br>
	 * ��ʹ��getOrderColumn �� getOrderDir �����õ�ĳ�а�ʲô��ʽ����<br>
	 * @param columnArray
	 */
	public void setColumnArray(String[] columnArray) {
		this.columnArray = columnArray;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	/**
	 * �Ӷ�������ʼ 
	 */
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * ÿҳ��ʾ���� 
	 */
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	
}
