package com.zst.test.rbac.jmsPoint;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumer {
	
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int SENDNUM=10;
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory;
		Connection connection;
		Session session;
		Destination destination;//消息队列
		MessageConsumer consumer;
		
		factory=new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
		connection=factory.createConnection();
		connection.start();
		session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);// 1 事务 2 
		destination=session.createQueue("FirstQueue1");//创建连接的消息队列
		consumer=session.createConsumer(destination);//创建消息消费者
		while(true){
			TextMessage message=(TextMessage)consumer.receive(100000);//同步，阻塞式的方式，接受消息
			if(message !=null){
				System.out.println("get meaage :"+message.getText());
			}else{
				break;
			}
		}
		
	}

}
