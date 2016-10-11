package com.zst.test.rbac.String;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class StringJava {
	
	public static void main(String[] args) {
		StringBuffer sb=new StringBuffer();
		sb.append("a");
		sb.append("ab");
		sb.append("abc");
		sb.append("abcd");
		List<StringBuffer> list=new ArrayList<StringBuffer>();
		list.add(sb);
		System.out.println(list.size());
		for (StringBuffer stringBuffer : list) {
			System.out.println(stringBuffer);
		}
		//==============HashSet===================
		System.out.println("//====================================");
		StringBuffer sb2=new StringBuffer();
		sb2.append("ddd");
		StringBuffer sb3=new StringBuffer();
		sb3.append("ddd");
		Set<StringBuffer> set =new HashSet<StringBuffer>();
		set.add(sb);
		set.add(sb2);
		set.add(sb2);
		System.out.println("set "+set.size());
		for (StringBuffer stringBuffer: set) {
			System.out.println(stringBuffer);
		}
		//=================TreeSet中不能加入StringBuffer元素===========================
		System.out.println("//====================================");
		StringBuffer tsb2=new StringBuffer();
		tsb2.append("ddd");
		StringBuffer tsb3=new StringBuffer();
		tsb3.append("ddd");
		Set<StringBuffer> tset =new TreeSet<StringBuffer>();
		tset.add(sb);
		tset.add(tsb2);
		tset.add(tsb2);
		System.out.println("set "+tset.size());
		for (StringBuffer stringBuffer: tset) {
			System.out.println(stringBuffer);
		}
	}
	

}
