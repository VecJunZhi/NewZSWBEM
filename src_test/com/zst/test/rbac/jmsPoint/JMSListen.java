package com.zst.test.rbac.jmsPoint;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class JMSListen implements MessageListener {

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			System.out.println("�յ�����Ϣ��"+(TextMessage)message);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
