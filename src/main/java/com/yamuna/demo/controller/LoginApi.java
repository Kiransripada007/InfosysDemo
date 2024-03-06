package com.yamuna.demo.controller;


import com.yamuna.demo.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class LoginApi {

    @Autowired
    private EmailService emailService;


    @PostMapping("/send/{emailAddress}")
    public ResponseEntity<?> createStudent(@PathVariable String  emailAddress) throws  MessagingException {
        String msg = emailService.verifyEmail(emailAddress);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
