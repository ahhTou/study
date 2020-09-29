package com.ahhTou.conf.stay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Configuration
@Data
@ConfigurationProperties(prefix = "ahhtou.utils.email-sender")
public class EmailConf {


    private String mySenderPassword;

    private String mySenderEmail;

    private boolean isDebug = false;

    @Bean
    public Session defaultSession() {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        String myEmailSMTPHost = "smtp.163.com";
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        // 需要请求认证
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);

        // 设置为debug模式
        session.setDebug(isDebug);

        return session;
    }

    @Bean
    public Transport defaultTransport() {

        try {

            Transport transport = defaultSession().getTransport();

            transport.connect(mySenderEmail, mySenderPassword);

            return transport;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;

    }


    @Bean
    public MimeMessage defaultMessage() throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(defaultSession());

        // 2. From: 发件人
        message.setFrom(new InternetAddress(mySenderEmail, "ahhTou", "UTF-8"));

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }


}
