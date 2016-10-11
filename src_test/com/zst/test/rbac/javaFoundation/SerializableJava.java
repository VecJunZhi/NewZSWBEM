package com.zst.test.rbac.javaFoundation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ���л���
 * Ŀ���ǣ�0.�������ϵ����ж������������������������õ����ж��󶼻ᱻ���л���
 * 		1.ͨ�����л����洢�����״̬��
 * 		2.ʹ��ObjectOutStream�����л�����
 * 		3.�������ʵ�����л����������Զ�ʵ�����л����������Ƿ�����ȷ��������
 * 		4.�������л�ʱ��������ͼ���ᱻ���л�����������ʵ�����������õĶ��󶼻ᱻʵ������
 * @author zsjr
 *
 */
public class SerializableJava implements Serializable {

	/**
	 * �������л�����ʱ���ö��󶼻����һ�����л���ʾId,�������л�����ʱ��java���������бȶ����л�ID����һ��ʱ
	 * �����л����󣬲�һ�µ�ʱ���򱨴�ID�Ų�һ����
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public static void main(String[] args) {
		deserialization();
	}
	/**
	 * �������л������ļ�
	 */
	public void createSerializable(){
		try {
			FileOutputStream fileos = new FileOutputStream("d:\\foo.ser");
			ObjectOutputStream objos = new ObjectOutputStream(fileos);
			SerializableJava obj = new SerializableJava();
			obj.setHeight(100);
			obj.setWidth(200);
			objos.writeObject(obj);
			objos.close();
			System.out.println("ok");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * �������л������ļ�
	 */
	public static void deserialization(){
		FileInputStream fileIs;
		ObjectInputStream objIs = null;
		try {
			fileIs = new FileInputStream("foo.ser");
			objIs=new ObjectInputStream(fileIs);
			Object obj=objIs.readObject();
			SerializableJava _obj=(SerializableJava)obj;
			int height=_obj.getHeight();
			int weight=_obj.getWidth();
			System.out.println("h "+height+" w "+weight);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				objIs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	/**
	 * д�ַ������ı��ļ���
	 */
	public static void writeToFile(){
		try {
			FileWriter write = new FileWriter("file.txt");
			write.write("hello,ikec");
			write.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
