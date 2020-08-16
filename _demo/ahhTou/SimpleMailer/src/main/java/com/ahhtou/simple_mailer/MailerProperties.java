package com.ahhtou.simple_mailer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ahhtou.simple.mailer")
@Data
public class MailerProperties {

    private String SenderEmail = "default";

    private String SenderPwd = "default";

    private Boolean isDebug = true;
}
