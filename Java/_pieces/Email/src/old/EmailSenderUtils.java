package com.ahhtou.old;

import com.ahhtou.utils.RedisUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
@Data
public class EmailSenderUtils {

    @Resource
    RedisUtils redisUtils;

    @Resource
    private Session defaultSession;

    @Resource
    private MimeMessage defaultMessage;

    @Resource
    private Transport defaultTransport;


    /* 创建一封邮件 */
    public MimeMessage createMimeMessage(String receiveMail, String content, String title) throws Exception {

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        defaultMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "亲爱的用户", "UTF-8"));

        // 4. Subject: 邮件主题
        defaultMessage.setSubject(title, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        defaultMessage.setContent(content, "text/html;charset=UTF-8");


        // 7. 保存设置
        defaultMessage.saveChanges();

        return defaultMessage;
    }


    public void send(MimeMessage msg) throws MessagingException {
        defaultTransport.sendMessage(msg, msg.getAllRecipients());
    }

    public void closeTransport() throws MessagingException {
        defaultTransport.close();
    }


}
