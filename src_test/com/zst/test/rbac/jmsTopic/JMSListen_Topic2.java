package com.zst.test.rbac.jmsTopic;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class JMSListen_Topic2 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			System.out.println("订阅者二收到的消息："+(TextMessage)message);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
