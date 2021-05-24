package cn.edu.cuit.travel_agency_management_system.mapper;

import cn.edu.cuit.travel_agency_management_system.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSendMail {

    @Autowired
    MailService mailService;

    @Test
    public void testSendMail(){
        mailService.sendSimpleMail();
    }
}
