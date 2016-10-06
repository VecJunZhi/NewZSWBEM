package com.zs.test.oa;

import java.util.TimerTask;

import com.zs.oa.web.action.AttendSynchAction;



public class TimerTaskExcute extends TimerTask {
   
	@Override
	public void run() {
		AttendSynchAction action = new AttendSynchAction();
		try {
			//action.insertDataToTable();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
	}

}
