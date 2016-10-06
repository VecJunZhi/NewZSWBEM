package com.zs.common.entity;

/**
 * DataTables组件公用参数实体
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
	 * 获取需要排序的列称名
	 * @return
	 */
	public String getOrderColumn() {
		if(this.columnArray == null &&  "".equals(this.orderColumn))	//没有设置排序数组及页面端没有取到值时
			return "";
		else
			return columnArray[Integer.parseInt(this.orderColumn)];
	}
	
	/**
	 * 获取排序方式
	 * @return 
	 */
	public String getOrderDir() {
		if(this.columnArray == null && "".equals(this.orderColumn))
			return "";
		else
			return orderDir;
	}

	/**
	 * 设置默认排序方式 asc desc
	 * @param orderDir
	 */
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	

	/**
	 * 获取列表字段数组
	 * @return
	 */
	public String[] getColumnArray() {
		return columnArray;
	}

	/**
	 * 如需要表单排序，设置表单列表字段值，<br>
	 * 与页面端列表依次对应，在columnArray不为null的情况下<br>
	 * 可使用getOrderColumn 和 getOrderDir 方法得到某列按什么方式排序。<br>
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
	 * 从多少条开始 
	 */
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * 每页显示数量 
	 */
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	
}
