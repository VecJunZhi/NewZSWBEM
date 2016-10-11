package com.zst.test.rbac.javaEmail;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JavaMail {
	public static void main(String [] args) throws Exception {   
	    // send();
		complexMail();
	}
	
	public static void send() throws Exception{
		
		Properties props=new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.126.com");
		Session session= Session.getInstance(props, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("yanzhijun869", "126.com");
			}
		});
		
		Message msg=new MimeMessage(session);
		msg.setFrom(new InternetAddress("yanzhijun869@126.com"));
		msg.setSubject("zhuti");
		msg.setText("abc");
		//msg.setContent("hehehe", "String");//text/plain
		//msg.setRecipients(RecipientType.TO, InternetAddress.parse("yanzhijun869@126.com,yanzhijun867@163.com"));
		msg.setRecipient(RecipientType.TO, new InternetAddress("yanzhijun869@126.com"));
		Transport.send(msg);
		System.out.println("success");
	}
	public static void msg(){
		 // �ռ��˵�������
	      String to = "yanzhijun869@126.com";
	 
	      // �����˵�������
	      String from = "web@gmail.com";
	 
	      // ָ�������ʼ�������Ϊ localhost
	      String host = "127.0.0.1";
	 
	      // ��ȡϵͳ����
	      Properties properties = System.getProperties();
	 
	      // �����ʼ�������
	      properties.setProperty("mail.smtp.host", host);
	 
	      // ��ȡĬ��session����
	      Session session = Session.getDefaultInstance(properties);
	 
	      try{
	         // ����Ĭ�ϵ� MimeMessage ����
	         MimeMessage message = new MimeMessage(session);
	 
	         // Set From: ͷ��ͷ�ֶ�
	         message.setFrom(new InternetAddress(from));
	 
	         // Set To: ͷ��ͷ�ֶ�
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	 
	         // Set Subject: ͷ��ͷ�ֶ�
	         message.setSubject("This is the Subject Line!");
	 
	         // ������Ϣ��
	         message.setText("This is actual message");
	 
	         // ������Ϣ
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
	public static void complexMail() throws Exception{
		Session session=null;
		MimeMessage msg=new MimeMessage(session);
		
		Multipart mp=new MimeMultipart("mixed");//�����ʼ��ļܹ�
		msg.setContent(mp);
		MimeBodyPart content=new MimeBodyPart();
		MimeBodyPart attach1=new MimeBodyPart();
		MimeBodyPart attach2=new MimeBodyPart();
		mp.addBodyPart(content);
		mp.addBodyPart(attach1);
		mp.addBodyPart(attach2);
		
		DataSource ds=new FileDataSource("E:\\�����ĵ�API\\jdk_api_1.6.chm");//(ctrl+t ��ʵ����)
		DataHandler dh1=new DataHandler(ds);
		attach1.setDataHandler(dh1);
		attach1.setFileName("java.chm");
		
		DataSource ds2=new FileDataSource("E:\\����\\music\\�Դ�-��ȫ.mp3");//(ctrl+t ��ʵ����)
		DataHandler dh2=new DataHandler(ds2);
		System.out.println(dh2.getContentType());
		System.out.println(dh2.getDataSource());
		//System.out.println(dh2.);
		attach2.setDataHandler(dh2);
		System.out.println(attach2.getFileName());
		attach2.setFileName("a.mp3");
		
		//����body
		Multipart bodyMultipart=new MimeMultipart("related");//���ĵļܹ�
		content.setContent(bodyMultipart);
		
		MimeBodyPart mimeBodyPart1=new MimeBodyPart();
		MimeBodyPart mimeBodypart2=new MimeBodyPart();
		bodyMultipart.addBodyPart(mimeBodyPart1);
		bodyMultipart.addBodyPart(mimeBodypart2);
		
		mimeBodyPart1.setContent("��ʢ�ۺ���Ϣ����ϵͳ <a href='http://www.zgzsdc.com/' target='_blank' title='��ʢ�ز�'>��ʢ�ز�</a>", "text/html;charset=gbk");
		
		DataSource ds3=new FileDataSource("C:\\Users\\zsjr\\Pictures\\picture\\001.jpg");
		DataHandler dh3=new DataHandler(ds3);
		mimeBodypart2.setDataHandler(dh3);
		
		msg.saveChanges();
		
		OutputStream os=new FileOutputStream("C:\\Users\\zsjr\\Desktop\\my_yzj\\mail.eml");
		msg.writeTo(os);
		os.close();
		
	}
	
	//tomcat ����jndi�ķ�ʽʵ��javamail
	// ��mail.jar�����뵽tomcat/lib���¡�
	//��Ӧ�ó����е�lib���е�javax.mail.jar��ɾ��
	public static void tomcatJndi() throws Exception{
		System.out.println("tomcatjndi");
		Context initCtx=new InitialContext();
		Context envCtx=(Context)initCtx.lookup("java:comp/env");
		System.out.println(envCtx.lookup("mail/Session").getClass().getClassLoader().getClass().getName());
		System.out.println(Session.class.getClassLoader().getClass().getName());
		Session session=(Session)envCtx.lookup("mail/Session");
		
		MimeMessage message =new MimeMessage(session);
		message.setFrom(new InternetAddress("yanzhijun869@126.com"));
		InternetAddress address[]=new InternetAddress[1];
		address[0]=new InternetAddress("yanzhijun869@126.com");
		message.setRecipient(RecipientType.TO, address[0]);
		message.setSubject("hello world");
		message.setText("i am a email");
		
		Transport transport=session.getTransport();
		transport.connect("smtp.126.com", "yanzhijun869", "126.com");
		transport.sendMessage(message, address);
		transport.close();
		
	}
	
}
