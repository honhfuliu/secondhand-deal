package com.ziheng.deal.common.util;

import com.ziheng.deal.common.ex.customizeErorr.EmailMessagingException;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.EmailDuplicateException;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Random;


/**
 * 邮箱发送短信工具
 */
@Component
public class SendEmailUtil {
    @Resource
    JavaMailSender javaMailSender;
    @Resource
    SpringTemplateEngine springTemplateEngine;//Spring 模板引擎

    @Value("${spring.mail.username}") //动态获取配置文件中 发送邮件的账户
    private String sendemail;

    public String SendEmail(String EamliAddress){
        //随机生成一个四位数的验证码
        int yzm = new Random().nextInt(9999) % (9999 - 1000 + 1) + 1000;
        /*
        // 创建一个简单的邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        // 发送邮件账户
        message.setFrom(sendemail);
        // 给谁发送
        message.setTo(EamliAddress);
        //邮件标题
        message.setSubject("二手交易平台注册验证码");
        //邮件内容
        message.setText("您的注册验证码是：" + yzm + "该验证码5分钟有效");
        javaMailSender.send(message);*/

        /*********************************************************/

        try {
            // 创建模板消息类
            MimeMessage message = javaMailSender.createMimeMessage();

            // 创建模板消息附带信息
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            // 发送邮件账户
            helper.setFrom(sendemail);
            // 给谁发送
            helper.setTo(EamliAddress);
            //邮件标题
            helper.setSubject("二手交易平台注册验证码");
            //用于保存将用于填充 Thymeleaf 模板的变量和数据。
            Context context = new Context();
            context.setVariable("code", yzm);
            String process = springTemplateEngine.process("registerEmaliTemplates.html", context);

            // 设置邮件主体内容， true 表示带有附件或html
            helper.setText(process, true);

            javaMailSender.send(message);

            return String.valueOf(yzm);
        } catch (MessagingException e) {
            throw new EmailMessagingException();
        }
    }


}
