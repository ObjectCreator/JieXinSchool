package cn.lx.jk.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtil {

	public static void  sendMail(String addr,String subject,String text) throws Exception{
		//1.邮件发送相关参数设置 
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.163.com");    //远程邮件服务器的地址   smtp.163.com    stmp.qq.com    smtp.sina.com
			props.put("mail.smtp.auth", "true");//是否进行安全验证
			
			//2.得到Session对象
			Session session = Session.getInstance(props);
	/*		session.setDebug(true);*/
			
			//3.产生一封邮件
			MimeMessage mimeMessage = new MimeMessage(session);
			
			//3.1设置发送者
			Address address = new InternetAddress("itheima14@163.com");
			mimeMessage.setFrom(address);
			
			//3.2设置接收者
			Address toAddress = new InternetAddress(addr);
			mimeMessage.setRecipient(RecipientType.TO, toAddress);  //TO   CC   BCC暗送
			
			//3.3设置主题
			mimeMessage.setSubject(subject);
			mimeMessage.setText(text);//设置正文
			mimeMessage.saveChanges();
			//4.得到Transport对象
			Transport transport = session.getTransport("smtp"); //火箭
			transport.connect("smtp.163.com", "itheima14@163.com", "iamsorry");//服务器地址,账号，密码
			
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			//5.关闭资源
			transport.close();
	}
}
