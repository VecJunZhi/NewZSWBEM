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
 * <p>Title: ����������</p>
 * <p>Description: ����������</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author JiaRui
 * @version 1.0
 */
public class MailSender
{
    /** �ʼ������ȱʡ���뷽ʽ��GBK */
    public static String MIME_CHARSET = "GBK";

    private Session smtpSession = null;
    private Transport smtpTransport = null;

    /**
     * �����ʼ�������
     */
    public MailSender()
    {
    }
	
    
    /**
     * �����ʼ��ʼ�������
     * @param server �ʼ�������
     * @param isAuth �Ƿ���Ҫsmtp��֤�����isAuthΪtrue��user��pass����Ϊ��
     * @param user ��¼smtp�������û�
     * @param pass ��¼smtp����������
     * @return ������ӳɹ�, ����true
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
     * �ر��ʼ�����session
     * @return ����رճɹ�, ����true
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
     * �����ʼ�����, �ʼ���ַ����",;"�ָ�
     * @param to  �ʼ�������
     * @param cc  CC���ʼ���ַ ����Ϊ null
     * @param bcc BCC���ʼ���ַ, ����Ϊ null
     * @param from �ʼ�������
     * @param content �ʼ�����
     * @param subject �ʼ�����
     * @param attachmentPath �ʼ�����·�������� �޸���Ϊ null
     * @return 0(���ͳɹ�), 1(�ʼ���ַ��ʽ��), 2(�ż������쳣)
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
     * parse�ʼ���ַ���ʼ���ַ��",;"�ָ�
     * @param addressString ��ַ��
     * @return �����ʼ���ַ��Vector
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
            catch (AddressException e)    //����ʼ���ַ�����������ﲢ���׳�Exception.
            {
                throw new Exception(e.getMessage());
            }
        }

        return addressVector;
    }

    /**
     * �����ʼ�����
     * @param args �����в���
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
            System.out.println("�޷����ʼ���������������");
            System.exit(0);
        }

        if (sender.send(to, cc, null, from, "��������123", "����123", null) != 0){
            System.out.println("�ʼ�����ʧ��");
            System.exit(0);
        } else{
			System.out.println("���ͳɹ���");
        }
		
        
        if (!sender.close()){
            System.out.println("�ر�sessionʧ��");
            System.exit(0);
        }
    }

}