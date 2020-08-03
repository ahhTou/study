package com.ahhTou.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Component
public class MyEmailSender {

    @Resource
    MyRedis myRedis;

    @Value("${emailSender.password}")
    private String mySenderPassword;
    @Value("${emailSender.sendEmail}")
    private String mySenderEmail;


    public Session createSession() {
        Session session = null;
        String myEmailSMTPHost = "smtp.163.com";
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
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
        session = Session.getInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log
//        session.setDebug(true);
        return session;
    }

    /* 发送验证码 */
    public String sendVerificationCodeEmail(String receiver) {
        String verificationCode = createVerificationCode();
        String content = "您的注册验证码为:  <strong>" + verificationCode + "</strong><br>验证码在5分钟内有效，请在五分钟内完成登录";
        String title = "欢迎注册，无名小站";
        Transport transport = null;
        try {
            MimeMessage message = createMimeMessage(mySenderEmail, receiver, content, title);
            transport = createTransport(mySenderEmail, receiver, mySenderPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return myRedis.setEmailVerificationCode(receiver, verificationCode, 5);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* 创建一次对话 */
    public Transport createTransport(String sender, String receiver, String sendPassword) {
        try {

            Session session = createSession();

            Transport transport = session.getTransport();

            transport.connect(sender, sendPassword);

            return transport;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    /* 创建一封邮件 */
    private MimeMessage createMimeMessage(String sendMail, String receiveMail, String content, String title) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(createSession());

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "ahhTou", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "亲爱的用户", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject(title, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(content, "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

    /* 创建一个验证码 */
    public static String createVerificationCode() {
        Random random = new Random(new Date().getTime());
        String code = "";
        for (int i = 0; i < 6; i++) {
            int i1 = random.nextInt(10);
            code = code.concat(String.valueOf(i1));
        }
        return code;
    }


}
