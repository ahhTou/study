package com.ahhtou.simple_mailer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties(MailerProperties.class)
@ConditionalOnClass(SimpleMailer.class)
@ConditionalOnProperty(prefix = "ahhtou.simple.mailer", value = "enable", matchIfMissing = true)
public class MailerAutoConfig {

    @Resource
    MailerProperties properties;

    @Bean
    public SimpleMailer simpleMailer() {
        SimpleMailer simpleMailer = new SimpleMailer();
        simpleMailer.setMySenderEmail(properties.getSenderEmail());
        simpleMailer.setMySenderPassword(properties.getSenderPwd());
        simpleMailer.setIsDebug(properties.getIsDebug());
        return simpleMailer;
    }
}
