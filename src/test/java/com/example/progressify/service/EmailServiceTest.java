package com.example.progressify.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {


    @Autowired
    private EmailService emailService;


    @Test
    void SendEmail(){
        emailService.sendEmail("poonamthak768@gmail.com","Testing MailSender Api","Hi This is a test");
    }
}
