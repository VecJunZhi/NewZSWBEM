package com.zs.common.task;

/**
 * Spring���ȣ�ָ��ʱ��ִ��
 * ������spring��ʹ��ע��ķ�ʽ������������ȡ� 
 */
public class SpringTaskController {

	public static void main(String[] args) {
		SpringTaskController action = new SpringTaskController();
		try {
			action.task();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ���û����õ�ʹ���ʱȽ� spirng ����
	 * 
	 * @throws Exception
	 */
	public void task() throws Exception {
		System.out.println("scheduled task ÿ����");
	}
	
	public void task1() throws Exception {
		System.out.println("scheduled task  ÿ1��ִ��");
	}
}