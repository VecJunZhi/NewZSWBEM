package com.zst.test.rbac.thread;

import java.util.Timer;
import java.util.TimerTask;


/**
 *��ͳ��ʱ�� 
 * @author yzj
 *
 */
public class TraditionalTimerTest {
	
	public static void main(String[] args) {
	//boming һ��
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("boming");
			}
		}, 10000);
	//boming ÿ��3��һ��
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("boming2");
			}
		}, 10000,3000);
	//
		
	}
}
