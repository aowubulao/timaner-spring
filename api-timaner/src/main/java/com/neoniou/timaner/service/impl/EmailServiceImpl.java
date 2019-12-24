package com.neoniou.timaner.service.impl;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import com.neoniou.timaner.service.EmailService;
import com.neoniou.timaner.util.CheckCodeUtil;
import com.neoniou.timaner.util.RedisUtil;
import io.github.biezhi.ome.OhMyEmail;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author neo.zzj
 * @date 2019-11-24
 */
@Service
public class EmailServiceImpl implements EmailService {

    private RedisUtil redisUtil = new RedisUtil();
    private static String password;

    static {
        Properties props = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("ome.properties");
        try {
            props.load(is);
            password = props.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendCheckCode(String accepter) {
        try {
            //生成验证码
            String checkCode = CheckCodeUtil.createCheckCode();
            //存入redis
            redisUtil.setCheckCode(accepter, checkCode);

            OhMyEmail.config(OhMyEmail.SMTP_QQ(true), "checkcode@neoniou.com", password);
            //pebble
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = engine.getTemplate("checkCode.html");

            Map<String, Object> context = new HashMap<String, Object>();
            context.put("checkCode", checkCode);

            Writer writer = new StringWriter();
            compiledTemplate.evaluate(writer, context);

            String output = writer.toString();

            //send
            OhMyEmail.subject("验证码")
                    .from("Timaner")
                    .to(accepter)
                    .html(output)
                    .send();
        } catch (Exception e) {
            //throw new MyException(ExceptionEnum.MAIL_SEND_FAIL);
            e.printStackTrace();
        }
    }

    @Override
    public Boolean isEmailInTime(String email) {
        return redisUtil.isEmailInTime(email);
    }
}
