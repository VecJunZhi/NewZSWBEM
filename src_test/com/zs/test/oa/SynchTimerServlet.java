package com.zs.test.oa;

import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class SynchTimerServlet extends HttpServlet {
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private Timer cemsTimer = null;
	@Override
	public void init() throws ServletException {
        // TODO Auto-generated method stub
        String startTask = getInitParameter("startTask");             
     
        if(startTask.equals("true")){          
           
            cemsTimer = new Timer(true);                     
            TimerTask cemsTask=new TimerTaskExcute();
          //  TimerTask cemsTask=new TestTimeConn();
            cemsTimer.schedule(cemsTask, 0, 1*60*1000);
        }   
    }
    @Override
	public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();    
        if(cemsTimer != null){   
            cemsTimer.cancel();   
           
        }   
    }
}
