package com.zst.test.rbac.Generic;

import java.util.ArrayList;
import java.util.List;


/**
 * ���Ϳ��Զ��������ϣ��ӿ��ϣ�������
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
		//generic.SetAnimal(list2);//����ͨ���޶������޶�ʹ�õķ���ֻ����Box����
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
	
	class Generic<T>{//����������
		private T data;

		public T getData() {
			return data;
		}

		public void setData(T data) {//�ڲ�����ʹ��
			this.data = data;
		}
	}
	@SuppressWarnings("unused")
	private <T,S extends T>void setName(String string,T t){//�����ڷ�����
		
	}
	
	interface GenericFace<T>{//�����ڽӿ���
		public  T getT();
		public void setT(T t);
	}
	
	/**
	 * ͨ�����ʹ��
	 */
	private void SetAnimal(List<? extends Box> list){
		for (Object object : list) {
			System.out.println(object);
		}
	}
}
