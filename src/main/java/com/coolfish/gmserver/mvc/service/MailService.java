package com.coolfish.gmserver.mvc.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.coolfish.gmserver.mvc.model.ExcelObj;

/**
 * Created by Forrest on 2017/3/16.
 */
@SuppressWarnings("deprecation")
@Service
public class MailService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	@Value("${mail.vm}")
	private String mailVmPath;
	
	@Value("${attachment.path}")
	private String attachment;
	
	
	public void sendSimpleMail(ExcelObj obj) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(obj.getMail());
		message.setSubject("北京良业环境技术有限公司 面试邀请");
		message.setText("simple mail");
		try {
			sender.send(message);
			obj.setSendSuccess("发送成功");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		public void sendHtmlMail(ExcelObj obj){  
	        MimeMessage message = sender.createMimeMessage();  
	        
	        VelocityEngine engine = new VelocityEngine();
	        Map<String, Object> model = new HashMap<String, Object>();  
	        model.put("name", obj.getName());  
	        model.put("time", obj.getTime());  
	        String content = VelocityEngineUtils.mergeTemplateIntoString(engine, "mail.vm", "UTF-8", model);  
	        try {  
	            //true表示需要创建一个multipart message  
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);  
	            helper.setFrom(from);  
	            helper.setTo(obj.getMail());  
	            helper.setSubject("北京良业环境技术有限公司 面试邀请");  
	            helper.setText(content, true);  
	  
	            sender.send(message);  
	        } catch (MessagingException e) {  
	        	e.printStackTrace();
	        }  
	    }  
	      
	    /** 
	     * 发送带附件的邮件 
	     * @param to 
	     * @param subject 
	     * @param content 
	     * @param filePath 
	     */  
		public void sendAttachmentsMail(ExcelObj obj){  
	        MimeMessage message = sender.createMimeMessage();  
	        VelocityEngine engine = new VelocityEngine();
	        Map<String, Object> model = new HashMap<String, Object>();  
	        model.put("name", obj.getName());  
	        model.put("time", obj.getTime());  
	        String content = VelocityEngineUtils.mergeTemplateIntoString(engine, mailVmPath, "UTF-8", model); 
	        try {  
	            //true表示需要创建一个multipart message  
	        	 MimeMessageHelper helper = new MimeMessageHelper(message, true);  
		            helper.setFrom(from);  
		            helper.setTo(obj.getMail());  
		            helper.setSubject("北京良业环境技术有限公司 面试邀请");  
		            helper.setText(content, true);    
		        String fileName = "良业环境PPT1020.pdf";
	            FileSystemResource file = new FileSystemResource(new File(fileName));  
//	            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));  
	            helper.addAttachment(fileName, file);  
	              
	            sender.send(message);  
	            obj.setSendSuccess("发送成功");
	        } catch (MessagingException e) {  
	        	e.printStackTrace();
	        }  
	    }  
	      
	    /** 
	     * 发送嵌入静态资源（一般是图片）的邮件 
	     * @param to 
	     * @param subject 
	     * @param content 邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" > 
	     * @param rscPath 静态资源路径和文件名 
	     * @param rscId 静态资源id 
	     */  
	    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId){  
	        MimeMessage message = sender.createMimeMessage();  
	  
	        try {  
	            //true表示需要创建一个multipart message  
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);  
	            helper.setFrom(from);  
	            helper.setTo(to);  
	            helper.setSubject(subject);  
	            helper.setText(content, true);  
	  
	            FileSystemResource res = new FileSystemResource(new File(rscPath));  
	            helper.addInline(rscId, res);  
	              
	            sender.send(message);  
	        } catch (MessagingException e) {  
	        	e.printStackTrace();
	        }  
	    }  
}
