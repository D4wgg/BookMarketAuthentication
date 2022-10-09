package ru.dawgg.bookmarket.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@EnableJpaRepositories(basePackages = "ru.dawgg.bookmarket.repository")
@EntityScan(basePackages = "ru.dawgg.bookmarket.model")
public class DataSourceConfig {

    @Value("{spring.mail.username}")
    private String username;
    @Value("{spring.mail.password}")
    private String password;
    @Value("{spring.mail.host}")
    private String host;
//    @Value("{spring.mail.port}")
//    private int port;
    @Value("{spring.mail.protocol}")
    private String protocol;
    @Value("{mail.debug}")
    private String debug;
    @Bean
    public JavaMailSender javaMailSender() {
        var mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(587);
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        var props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("spring.mail.protocol", protocol);
        props.put("mail.debug", debug);

        return mailSender;
    }
}
