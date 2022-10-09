package ru.dawgg.bookmarket.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.dawgg.bookmarket.service.EmailSender;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderImpl implements EmailSender {
    private final JavaMailSender mailSender;

    @Override
    @Async
    @SneakyThrows
    public void send(String to, String email) {
        var message = new SimpleMailMessage();
        message.setFrom("dwg3kelo@gmail.com");
        message.setTo(to);
        message.setSubject("Confirm your email");
        message.setText(email);

        mailSender.send(message);
        System.out.println("Message has been sent");
    }
}