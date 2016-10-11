package com.zst.test.rbac.javaFoundation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * File:文件这个类代表磁盘中的文件，但不是文件中的内容。
 * File这个类不能读取文件中的数据，亦不能代表文件中的数据。
 * =======================================
 * Buffer:缓冲区就像超市里的推车一样，暂时存放很多的东西.
 * 			写缓冲：就像购物--往菜篮子里放东西。
 * 			读缓冲：就像从菜篮子里那东西一次性结账。
 * @author zsjr
 *
 */
public class FileJava {
	public static void main(String[] args) {
		BufferedWriter bufWrite = null;
		BufferedReader reader=null;
		try {
			//创建文件夹
			File dirFile=new File("dirFile");
			dirFile.mkdir();
			//创建文件
			File file=new File("dirFile\\foo3.txt");
			file.createNewFile();
			//遍历文件夹
			if(dirFile.isDirectory()){
				String[] array=dirFile.list();
				for (int i = 0; i < array.length; i++) {
					System.out.println(array[i]);
				}
			}
			//构建一个写缓冲区,向文件写入数据
			bufWrite=new BufferedWriter(new FileWriter(file));
			bufWrite.write("abc,def/r dfd  ");
			bufWrite.write("adddbc,def/r dddfd  ");
			bufWrite.flush();
			System.out.println(dirFile.getAbsolutePath());
			//构建一个读缓冲区，读取文件的数据。
			reader=new BufferedReader(new FileReader(file));
			String line;
			while((line=reader.readLine()) !=null){
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bufWrite.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//dirFile.delete();
	}
}
