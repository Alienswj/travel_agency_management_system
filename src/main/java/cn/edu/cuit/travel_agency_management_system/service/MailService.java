package cn.edu.cuit.travel_agency_management_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSenderImpl mailSender;//注入邮件工具类
    SimpleMailMessage simpleMailMessage= new SimpleMailMessage();

    public void sendSimpleMail(){
        simpleMailMessage.setFrom("173316449@qq.com");
        simpleMailMessage.setTo("1356772315@qq.com");
        simpleMailMessage.setSubject("你已被封禁！");
        simpleMailMessage.setText("test_send_mail");
        mailSender.send(simpleMailMessage);
    }

    public boolean sendForbiddenMail(String To){
        try {
            simpleMailMessage.setFrom("173316449@qq.com");
            simpleMailMessage.setTo("1356772315@qq.com");
            simpleMailMessage.setSubject("您的账号已被封禁！");
            simpleMailMessage.setText("您的账号涉嫌违规，已被系统封禁，如有异议请联系客服！");
            mailSender.send(simpleMailMessage);
            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        }

    }


}
