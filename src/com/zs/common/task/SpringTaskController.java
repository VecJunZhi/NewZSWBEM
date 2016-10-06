package com.zs.common.task;

/**
 * Spring调度，指定时间执行
 * 利用了spring中使用注解的方式来进行任务调度。 
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
	 * 与用户设置的使用率比较 spirng 调度
	 * 
	 * @throws Exception
	 */
	public void task() throws Exception {
		System.out.println("scheduled task 每分钟");
	}
	
	public void task1() throws Exception {
		System.out.println("scheduled task  每1秒执行");
	}
}