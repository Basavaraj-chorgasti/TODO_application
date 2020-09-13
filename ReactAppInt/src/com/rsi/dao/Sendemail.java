package com.rsi.dao;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sendemail 
{
	public static void sendemail(String mail,String messages,String SenderEmail,String password)
	{  
        //Get properties object    
        Properties properties = new Properties();    
        properties.put("mail.smtp.host","smtp.gmail.com");    
        properties.put("mail.smtp.socketFactory.port", "465");    
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");    
        properties.put("mail.smtp.auth","true");    
        properties.put("mail.smtp.port","465");    
        //get Session   
        Session session = Session.getDefaultInstance(properties,    
        new javax.mail.Authenticator() 
        {    
        		protected PasswordAuthentication getPasswordAuthentication()
        		{    
        			return new PasswordAuthentication(SenderEmail,password);  
        		}    
        });    
        //compose message    
        try
        {
        	MimeMessage message = new MimeMessage(session);    
        	message.setRecipient(Message.RecipientType.TO,new InternetAddress(mail));    
        	message.setSubject("email varification");    
        	message.setText(messages);    
        	//send message  
        	Transport.send(message);    
        	System.out.println("EMAIL SEND SUCCESSFULLY...");    
        } 	
        catch(MessagingException e) 
        {
      	  	e.printStackTrace();
        }    
	}
}
