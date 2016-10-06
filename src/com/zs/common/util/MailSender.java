package com.zs.common.util;

import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * <p>Title: 基本工具类</p>
 * <p>Description: 基本工具类</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author JiaRui
 * @version 1.0
 */
public class MailSender
{
    /** 邮件标题的缺省编码方式是GBK */
    public static String MIME_CHARSET = "GBK";

    private Session smtpSession = null;
    private Transport smtpTransport = null;

    /**
     * 创建邮件发送者
     */
    public MailSender()
    {
    }
	
    
    /**
     * 连接邮件邮件服务器
     * @param server 邮件服务器
     * @param isAuth 是否需要smtp认证，如果isAuth为true，user和pass不能为空
     * @param user 登录smtp服务器用户
     * @param pass 登录smtp服务器口令
     * @return 如果连接成功, 返回true
     */
    public boolean connect(String server, boolean isAuth, String user, String pass)
    {
        try
        {
            Properties props = new Properties();
            props.put("mail.smtp.host", server);
            if (isAuth)
            {
                props.put("mail.smtp.auth", "true");
            }
            smtpSession = Session.getDefaultInstance(props, null);
            //smtpSession.setDebug(true);

            smtpTransport = smtpSession.getTransport("smtp");
            if (isAuth)
            {
                smtpTransport.connect(server, user, pass);
            }
            else
            {
                smtpTransport.connect();
            }
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 关闭邮件发送session
     * @return 如果关闭成功, 返回true
     */
    public boolean close()
    {
        try
        {
            if (smtpTransport == null)
            {
                return false;
            }
            smtpTransport.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 发送邮件方法, 邮件地址均以",;"分隔
     * @param to  邮件接收者
     * @param cc  CC的邮件地址 可以为 null
     * @param bcc BCC的邮件地址, 可以为 null
     * @param from 邮件发送者
     * @param content 邮件内容
     * @param subject 邮件标题
     * @param attachmentPath 邮件附件路径的数组 无附件为 null
     * @return 0(发送成功), 1(邮件地址格式错), 2(信件发送异常)
     */
    public int send(String to,
                    String cc,
                    String bcc,
                    String from,
                    String content,
                    String subject,
                    String[] attachmentPath
                     )
    {
        Vector addressVectorTo = null;
        Vector addressVectorCc = null;
        Vector addressVectorBcc = null;
        String sendTo = "";

        MimeMessage msg = new MimeMessage(smtpSession);

        try
        {
            addressVectorTo = getAddressVector(to);
            addressVectorCc = getAddressVector(cc);
            addressVectorBcc = getAddressVector(bcc);
        }
        catch(Exception e)
        {
            return 1;
        }

        try
        {
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = null;

            // set to
            if (addressVectorTo != null)
            {
                address = new InternetAddress[addressVectorTo.size()];
                address = (InternetAddress[]) addressVectorTo.toArray(address);
                msg.setRecipients(Message.RecipientType.TO, address);
            }

            //set cc
            if (addressVectorCc != null)
            {
                address = new InternetAddress[addressVectorCc.size()];
                address = (InternetAddress[]) addressVectorCc.toArray(address);
                msg.setRecipients(Message.RecipientType.CC, address);
            }

            //set bcc
            if (addressVectorBcc != null)
            {
                address = new InternetAddress[addressVectorBcc.size()];
                address = (InternetAddress[]) addressVectorBcc.toArray(address);
                msg.setRecipients(Message.RecipientType.BCC, address);
            }
			
			subject = new String(subject.getBytes(), MIME_CHARSET);
            msg.setSubject(subject, MIME_CHARSET);

            MimeMultipart mmp = new MimeMultipart();
            MimeBodyPart  body1 = new MimeBodyPart();

            if (attachmentPath == null || attachmentPath.length == 0)
            {
            	body1.setContent(content,"text/html");
                mmp.addBodyPart(body1);
            }
            else
            {
                body1.setContent(content,"text/html");
                mmp.addBodyPart(body1);

                for (int i = 0; i < attachmentPath.length; i++)
                {
                    MimeBodyPart body2 = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(attachmentPath[i]);
                    
                    body2.setDataHandler(new DataHandler(fds));
                    String attachmentName = fds.getName();
                    //attachmentName = MimeUtility.encodeText(attachmentName, MIME_CHARSET, null);
                    body2.setFileName(attachmentName);
                    
                    mmp.addBodyPart(body2);
                }
            }

            msg.setContent(mmp);	// Next 2 lines enforce base64 encode.
            msg.saveChanges();

            body1.setHeader("Content-Transfer-Encoding", "base64");

            smtpTransport.sendMessage(msg, msg.getAllRecipients());
            return 0;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 2;
        }

    }

    /**
     * parse邮件地址，邮件地址以",;"分隔
     * @param addressString 地址串
     * @return 包含邮件地址的Vector
     * @throws Exception
     */
    private Vector getAddressVector(String addressString) throws Exception
    {
        if (addressString == null)
        {
            return null;
        }

        String delimiter = ",;";
        StringTokenizer st = new StringTokenizer(addressString, delimiter);
        Vector addressVector = null;
        InternetAddress tmpAddress = null;
        String tmpString = null;

        while (st.hasMoreTokens())
        {
            tmpString = st.nextToken();

            try
            {
                tmpAddress = new InternetAddress(tmpString);
                if (addressVector == null)
                {
                    addressVector = new Vector();
                }
                addressVector.add(tmpAddress);
            }
            catch (AddressException e)    //如果邮件地址解析错，但这里并不抛出Exception.
            {
                throw new Exception(e.getMessage());
            }
        }

        return addressVector;
    }

    /**
     * 测试邮件发送
     * @param args 命令行参数
     */
    public static void main(String[] args){
        String server = "mail.verye.com";
        String to = "jiangzp@verye.com";
        String cc = "ronaldoc@sohu.com,;luoxx@sina.com";
        String user = "u52bd15";
        String pass = "123456";
        String from = "jiangzp@verye.com";
        String[] attachment = new String[2];
        attachment[0] = "d:/temp/cxt.xml";
        attachment[1] = "d:/temp/cxt.xml";


        MailSender sender = new MailSender();
        if (! sender.connect(server, true, user, pass)){
            System.out.println("无法与邮件服务器建立连接");
            System.exit(0);
        }

        if (sender.send(to, cc, null, from, "测试内容123", "测试123", null) != 0){
            System.out.println("邮件发送失败");
            System.exit(0);
        } else{
			System.out.println("发送成功！");
        }
		
        
        if (!sender.close()){
            System.out.println("关闭session失败");
            System.exit(0);
        }
    }

}