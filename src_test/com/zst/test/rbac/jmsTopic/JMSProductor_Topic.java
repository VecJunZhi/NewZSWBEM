package com.zst.test.rbac.jmsTopic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class JMSProductor_Topic {
	
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int SENDNUM=10;
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory;
		Connection connection;
		Session session;
		Destination destination;
		MessageProducer messageProducer;
		
		factory=new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
		connection=factory.createConnection();
		connection.start();
		session=connection.createSession(true, Session.AUTO_ACKNOWLEDGE);// 1 事务 2 
		//destination= session.createQueue("FirstQueue1");
		destination=session.createTopic("firstTopic1");
		messageProducer=session.createProducer(destination);
		sendMessage(session, messageProducer);
		session.commit();
		connection.close();	
	}
	public static void sendMessage(Session session,MessageProducer messageProducer) throws Exception{
		for (int i = 0; i < SENDNUM; i++) {
			TextMessage message=session.createTextMessage("ACTIVE 发送的消息"+i);
			System.out.println("send message "+i);
			messageProducer.send(message);
		}
		
	}
}
