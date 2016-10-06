package com.zs.test.oa;

import java.util.TimerTask;

import com.zs.oa.dao.impl.AttendSynchDaoImpl;

public class TestTimeConn extends TimerTask {
     
	AttendSynchDaoImpl dao = new AttendSynchDaoImpl();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			dao.getStart_Time();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
