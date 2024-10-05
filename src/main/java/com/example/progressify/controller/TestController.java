package com.example.progressify.controller;

import com.example.progressify.dto.MailTestRequestDTO;
import com.example.progressify.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final EmailService emailService;

    @Autowired
    public TestController(EmailService emailService){
        this.emailService= emailService;
    }

    @PostMapping("/testing")
    public String testing() {
        return "The application is running successfully and ready to test it out ";
    }

    @PostMapping("/email-testing")
    public String testMail(@RequestBody MailTestRequestDTO mailTestRequestDTO){
        try{
            emailService.sendEmail(mailTestRequestDTO.getEmail(), mailTestRequestDTO.getSubject(), mailTestRequestDTO.getBody());

        }catch (Exception e){

        }
        return "Mail testing";
    }
}
