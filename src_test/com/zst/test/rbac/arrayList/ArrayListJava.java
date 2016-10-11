package com.zst.test.rbac.arrayList;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ArrayListJava {
	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>();
		list.ensureCapacity(10);
		list.add("a");
		list.add("aa");
		list.add("aaa");
		list.add("a");
		//System.out.println(list.size());
		//System.out.println(list.get(4));
		
		//=============================
		
		//String[] string = new String[6];
		//System.out.println(string[0]);
		Iterator<String> it=list.iterator();
		while (it.hasNext()) {
			String value = (String) it.next();
			//System.out.println(value);
			
		}
		Map<String, String> map =new HashMap<String, String>();
		map.put("1", "11");
		map.put("11", "21");
		map.put("122", "31");
		map.put("133", "41");
		map.put("144", "51");
		
		Set<Entry<String, String>> sets=map.entrySet();
		
		Iterator<Entry<String, String>> its=sets.iterator();
		Iterator<String> itval=map.values().iterator();
		while(itval.hasNext()){
			System.out.println(itval.next());
		}
		while(its.hasNext()){
			//System.out.println(its.next());
		}
		//
		
	}
	abstract class A{
		private final void getA() {
			// TODO Auto-generated method stub
			System.out.println("get a ");
			getAs();
		}
		abstract void getAs();
	}
	class B extends A implements Iterator<Object>{
		private final void getB() {
			// TODO Auto-generated method stub
			System.out.println("get B ");
			super.getA();
		}

		@Override
		void getAs() {
			// TODO Auto-generated method stub
			System.out.println("GET AS ");
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
