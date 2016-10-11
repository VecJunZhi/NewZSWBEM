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
		 // 收件人电子邮箱
	      String to = "yanzhijun869@126.com";
	 
	      // 发件人电子邮箱
	      String from = "web@gmail.com";
	 
	      // 指定发送邮件的主机为 localhost
	      String host = "127.0.0.1";
	 
	      // 获取系统属性
	      Properties properties = System.getProperties();
	 
	      // 设置邮件服务器
	      properties.setProperty("mail.smtp.host", host);
	 
	      // 获取默认session对象
	      Session session = Session.getDefaultInstance(properties);
	 
	      try{
	         // 创建默认的 MimeMessage 对象
	         MimeMessage message = new MimeMessage(session);
	 
	         // Set From: 头部头字段
	         message.setFrom(new InternetAddress(from));
	 
	         // Set To: 头部头字段
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	 
	         // Set Subject: 头部头字段
	         message.setSubject("This is the Subject Line!");
	 
	         // 设置消息体
	         message.setText("This is actual message");
	 
	         // 发送消息
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
	public static void complexMail() throws Exception{
		Session session=null;
		MimeMessage msg=new MimeMessage(session);
		
		Multipart mp=new MimeMultipart("mixed");//整个邮件的架构
		msg.setContent(mp);
		MimeBodyPart content=new MimeBodyPart();
		MimeBodyPart attach1=new MimeBodyPart();
		MimeBodyPart attach2=new MimeBodyPart();
		mp.addBodyPart(content);
		mp.addBodyPart(attach1);
		mp.addBodyPart(attach2);
		
		DataSource ds=new FileDataSource("E:\\开发文档API\\jdk_api_1.6.chm");//(ctrl+t 打开实现类)
		DataHandler dh1=new DataHandler(ds);
		attach1.setDataHandler(dh1);
		attach1.setFileName("java.chm");
		
		DataSource ds2=new FileDataSource("E:\\娱乐\\music\\赵传-成全.mp3");//(ctrl+t 打开实现类)
		DataHandler dh2=new DataHandler(ds2);
		System.out.println(dh2.getContentType());
		System.out.println(dh2.getDataSource());
		//System.out.println(dh2.);
		attach2.setDataHandler(dh2);
		System.out.println(attach2.getFileName());
		attach2.setFileName("a.mp3");
		
		//构造body
		Multipart bodyMultipart=new MimeMultipart("related");//正文的架构
		content.setContent(bodyMultipart);
		
		MimeBodyPart mimeBodyPart1=new MimeBodyPart();
		MimeBodyPart mimeBodypart2=new MimeBodyPart();
		bodyMultipart.addBodyPart(mimeBodyPart1);
		bodyMultipart.addBodyPart(mimeBodypart2);
		
		mimeBodyPart1.setContent("兆盛综合信息管理系统 <a href='http://www.zgzsdc.com/' target='_blank' title='兆盛地产'>兆盛地产</a>", "text/html;charset=gbk");
		
		DataSource ds3=new FileDataSource("C:\\Users\\zsjr\\Pictures\\picture\\001.jpg");
		DataHandler dh3=new DataHandler(ds3);
		mimeBodypart2.setDataHandler(dh3);
		
		msg.saveChanges();
		
		OutputStream os=new FileOutputStream("C:\\Users\\zsjr\\Desktop\\my_yzj\\mail.eml");
		msg.writeTo(os);
		os.close();
		
	}
	
	//tomcat 采用jndi的方式实现javamail
	// 将mail.jar包导入到tomcat/lib包下。
	//将应用程序中的lib包中的javax.mail.jar包删除
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
