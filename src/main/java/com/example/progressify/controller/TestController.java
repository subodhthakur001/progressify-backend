package com.example.progressify.controller;

import com.example.progressify.dto.MaiilTestRequestDTO;
import com.example.progressify.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/testing")
    public String testing() {
        return "The application is running successfully and ready to test it out ";
    }

    @PostMapping("/email-testing")
    public String testMail(@RequestBody MaiilTestRequestDTO maiilTestRequestDTO){
        try{
            emailService.sendEmail(maiilTestRequestDTO.getEmail(), maiilTestRequestDTO.getSubject(), maiilTestRequestDTO.getBody());

        }catch (Exception e){

        }
        return "Mail testing";
    }
}
