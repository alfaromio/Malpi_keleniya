/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.notification;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Mihiran
 */
public class Email_ForgotPass {

  
    
    Email_ForgotPass(String email,String Username,String Password,String userid){
        final String user ="mpwpathirage@gmail.com";
        final String pass ="Mihiran@Epic";
   
   
       Properties prop=new Properties();
       
       prop.put("mail.smtp.ssl.trust","smtp.gmail.com");
       prop.put("mail.smtp.auth",true);
       prop.put("mail.smtp.starttls.enable",true);
       prop.put("mail.smtp.host","smtp.gmail.com");
       prop.put("mail.smtp.port","587");
       
       Session session =Session.getInstance(prop, new javax.mail.Authenticator() 
       {
          
          protected javax.mail.PasswordAuthentication getPasswordAuthentication()
           {
               return new javax.mail.PasswordAuthentication(user, pass);
           }
       });
       
       try{
           Message message =new MimeMessage(session);
           message.setFrom(new InternetAddress("mpwpathirage@gmail.com"));
           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
           message.setSubject("IMS Notification");
           String htmlText = "<H3>BlueWolfInc(Pvt)Ltd.</H3><h4>Mr.Mihiran</h4>Here is the login details of your account of IMS(Pvt)Ltd.<br> "+"<h4>User_ID: Test</h4>"+"<h4>Password: Test</h4>Admin.<br>Institute Management System System.<br>IMS(Pvt)Ltd.";
           message.setContent(htmlText, "text/html");
           Transport.send(message);
           
           System.out.println("message sent");
           
       }catch(Exception e){
           System.out.println(e); 
       }
    
    
    }

   
}

