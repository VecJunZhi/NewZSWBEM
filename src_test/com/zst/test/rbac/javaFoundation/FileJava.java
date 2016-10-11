package com.zst.test.rbac.javaFoundation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * File:�ļ�������������е��ļ����������ļ��е����ݡ�
 * File����಻�ܶ�ȡ�ļ��е����ݣ��಻�ܴ����ļ��е����ݡ�
 * =======================================
 * Buffer:����������������Ƴ�һ������ʱ��źܶ�Ķ���.
 * 			д���壺������--����������Ŷ�����
 * 			�����壺����Ӳ��������Ƕ���һ���Խ��ˡ�
 * @author zsjr
 *
 */
public class FileJava {
	public static void main(String[] args) {
		BufferedWriter bufWrite = null;
		BufferedReader reader=null;
		try {
			//�����ļ���
			File dirFile=new File("dirFile");
			dirFile.mkdir();
			//�����ļ�
			File file=new File("dirFile\\foo3.txt");
			file.createNewFile();
			//�����ļ���
			if(dirFile.isDirectory()){
				String[] array=dirFile.list();
				for (int i = 0; i < array.length; i++) {
					System.out.println(array[i]);
				}
			}
			//����һ��д������,���ļ�д������
			bufWrite=new BufferedWriter(new FileWriter(file));
			bufWrite.write("abc,def/r dfd  ");
			bufWrite.write("adddbc,def/r dddfd  ");
			bufWrite.flush();
			System.out.println(dirFile.getAbsolutePath());
			//����һ��������������ȡ�ļ������ݡ�
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
