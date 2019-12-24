package com.neoniou.timaner.controller;

import com.neoniou.timaner.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author neo.zzj
 * @date 2019-11-24
 */
@RestController
@RequestMapping("/api/timaner/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    /**
     * 发送验证码，然后存入redis，15分钟过期
     * @param email 输入的邮箱
     * @return 1
     */
    @GetMapping("{email}")
    public ResponseEntity<Void> sendMail(@PathVariable("email") String email) {
        try {
            //首先判断邮箱是否重复发送邮件
            Boolean emailInTime = emailService.isEmailInTime(email + "time");
            //可以发送邮件
            if (emailInTime) {
                emailService.sendCheckCode(email);
            } else {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }
}