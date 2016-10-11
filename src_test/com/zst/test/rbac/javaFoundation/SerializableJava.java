package com.zst.test.rbac.javaFoundation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 序列化：
 * 目的是：0.将对象上的所有东西储存起来，被对象所引用的所有对象都会被序列化。
 * 		1.通过序列化来存储对象的状态。
 * 		2.使用ObjectOutStream来序列化对象。
 * 		3.如果父类实现序列化，则子类自动实现序列化，而不管是否有明确的声明。
 * 		4.对象被序列化时，整个版图都会被序列化，代表他的实例变量所引用的对象都会被实例化。
 * @author zsjr
 *
 */
public class SerializableJava implements Serializable {

	/**
	 * 创建序列化对象时，该对象都会带有一个序列化标示Id,当解序列化对象时，java虚拟机会进行比对序列化ID，当一致时
	 * 解序列化对象，不一致的时候则报错，ID号不一样。
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
	 * 创建序列化对象文件
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
	 * 解析序列化对象文件
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
	 * 写字符串到文本文件中
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
