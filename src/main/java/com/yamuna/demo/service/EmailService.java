package com.yamuna.demo.service;

import com.yamuna.demo.entity.Customer;
import com.yamuna.demo.repository.CustomerRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;


@Service
public class EmailService {


    @Autowired
    private CustomerRepo customerRepo;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender = null;


    public String sendEmail(String emailAddress) throws AuthenticationException, MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject("Password Reset");
        String randomPwd = sendTempPassword();
        helper.setText("Your temporary new password is " + randomPwd + ",Please change your password at profile");
        helper.setFrom(sender);
        helper.setTo(emailAddress);


        Customer cusByEmail = customerRepo.findByEmailAddress(emailAddress);
        if (cusByEmail != null) {
            customerRepo.save(cusByEmail);
        } else {
            throw new AuthenticationException("Invalid EmailAddress");
        }

        javaMailSender.send(message);
        return "Mail Sent Successfully......!";
    }
    public String verifyEmail(String emailAddress,String code) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject("Email Verification");
        String randomPwd = sendTempPassword();
        helper.setText("Your Verification Code is " + randomPwd + ",Please use the code  for mail verification");
        helper.setFrom(sender);
        helper.setTo(emailAddress);
        javaMailSender.send(message);

        if(randomPwd == code) {

            return "Mail Sent Successfully......!";
        }
        return "";

    }
    public String verifyEmail(String emailAddress) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject("Email Verification");
        String randomPwd = sendTempPassword();
        helper.setText("Your Verification Code is " + randomPwd + ",Please use the code  for mail verification");
        helper.setFrom(sender);
        helper.setTo(emailAddress);
        javaMailSender.send(message);
        return "Mail Sent Successfully......!";

    }




    public String sendTempPassword() {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Math RandomStringUtils = null;
        String pwd = String.valueOf(RandomStringUtils.random());
        return pwd;
    }

}
