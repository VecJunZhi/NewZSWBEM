package com.zst.test.rbac.jmsPoint;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumerListen {
	
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int SENDNUM=10;
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory;
		Connection connection;
		Session session;
		Destination destination;//��Ϣ����
		MessageConsumer consumer;
		
		factory=new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
		connection=factory.createConnection();
		connection.start();
		session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);// 1 ���� 2 
		destination=session.createQueue("FirstQueue1");//�������ӵ���Ϣ����
		consumer=session.createConsumer(destination);//������Ϣ������
		consumer.setMessageListener(new JMSListen());//ע�����
		
	}

}
