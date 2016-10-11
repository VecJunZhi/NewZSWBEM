package com.zst.test.rbac.Generic;

import java.util.ArrayList;
import java.util.List;


/**
 * 泛型可以定义在类上，接口上，方法上
 * @author yzj
 *
 */
public class GenericTest {
	
	public static void main(String[] args) {
		GenericTest generic=new GenericTest();
		List<Box> list=new ArrayList<Box>();
		Box box=generic.new Box();
		list.add(box);
		generic.SetAnimal(list);
		List<Integer> list2=new ArrayList<Integer>();
		list2.add(2);
		//generic.SetAnimal(list2);//报错，通过限定符来限定使用的泛型只能是Box子类
	}
	
	class Box{
		private int hight;
		private int weight;
		public int getHight() {
			return hight;
		}
		public void setHight(int hight) {
			this.hight = hight;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
	}
	class CircleBox extends Box{
		@Override
		public void setHight(int hight) {
			// TODO Auto-generated method stub
			super.setHight(hight);
		}
		@Override
		public int getHight() {
			// TODO Auto-generated method stub
			return super.getHight();
		}
	}
	
	class Generic<T>{//定义在类上
		private T data;

		public T getData() {
			return data;
		}

		public void setData(T data) {//内部方法使用
			this.data = data;
		}
	}
	@SuppressWarnings("unused")
	private <T,S extends T>void setName(String string,T t){//定义在方法上
		
	}
	
	interface GenericFace<T>{//定义在接口上
		public  T getT();
		public void setT(T t);
	}
	
	/**
	 * 通配符的使用
	 */
	private void SetAnimal(List<? extends Box> list){
		for (Object object : list) {
			System.out.println(object);
		}
	}
}
