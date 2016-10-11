package com.zst.test.rbac.thread;

import java.util.Timer;
import java.util.TimerTask;


/**
 *传统定时器 
 * @author yzj
 *
 */
public class TraditionalTimerTest {
	
	public static void main(String[] args) {
	//boming 一次
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("boming");
			}
		}, 10000);
	//boming 每隔3秒一次
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("boming2");
			}
		}, 10000,3000);
	//
		
	}
}
