package com.xq17.cloudtools.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


/**
 * 
    * @ClassName: SendEmailUtil  
    * @Description: 邮件发送类
    * @author xq17  
    * @date 2020年9月7日  
    *
 */
public class SendEmailUtil {
	private String sendEmail; // 发件箱
	private String pwd; //发件箱的授权码
	
	public String getSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public boolean sendHtmlMail(String receiveEmail, String name, String code) {
		if(StringUtil.checkStringNull(receiveEmail, name, code)) {
			return false;
		}
		
		try {
			JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
			senderImpl.setHost("smtp.qq.com"); //邮箱主机
			senderImpl.setDefaultEncoding("utf-8");
			
			//建立邮件信息
			MimeMessage message = senderImpl.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message);
			
			messageHelper.setTo(receiveEmail); //收件人邮箱
			messageHelper.setFrom(sendEmail); // 发件人邮箱
			messageHelper.setSubject("Snacks注册中心"); //邮件注册
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss EEE");
			
			//设置邮件正文
			String text = "<!doctype html><html><head><meta charset=\"utf-8\"></head><body>";
			text += "<p style=\"font-size:20px font-weight:bold\">尊敬的," + name +"</p>";
			text += "<p style=\"text-indent:2em;font-size:20px;\">欢迎注册零食网,您本次的注册码为:<span  style=\"font-size:30px; font-weight: bold; color:red\">";
			text += code + "</span> 3分钟之内有效,请尽快完成注册流程!</p>";
			text += "<span style=\"font-size:18px; float:right; margin-right：20px\" >";
			text += sdf.format(new Date()) + "</span>";
			text += "</body></html>";
			messageHelper.setText(text, true);
			
			//设置收件箱的账号和密码
			senderImpl.setUsername(sendEmail);
			senderImpl.setPassword(pwd);
			
			Properties prop = new Properties();
			prop.put("mail.smtp.auth", "true"); // 让服务器验证账号和密码 
			prop.put("mail.smtp.timeout", "2500"); // 连接超时时间
			
			senderImpl.setJavaMailProperties(prop);
			senderImpl.send(message);
			
			return true;
			
		}catch(MessagingException e){
			e.printStackTrace();
		}
		
		return false;
	}
	

}
