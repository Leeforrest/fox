package com.coolfish.gmserver.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.coolfish.gmserver.mvc.model.ExcelObj;

/**
 * Created by Forrest on 2017/3/16.
 */
@Service
public class MailService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	public void sendSimpleMail(ExcelObj obj) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(obj.getMail());
		message.setSubject("北京良业环境技术有限公司 面试邀请");
//		message.setSubject("李晓东");
		message.setText("simple mail");
		try {
			sender.send(message);
			obj.setSendSuccess("发送成功");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
