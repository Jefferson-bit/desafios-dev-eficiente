package org.example.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.CompletableFuture;

@Component
class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        CompletableFuture.runAsync(() -> {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom("crashnight089@gmail.com");
                    message.setTo(to);
                    message.setSubject(subject);
                    message.setText(text);
                    emailSender.send(message);
                    System.out.println("Email enviado com sucesso");
                })
                .exceptionally(error -> {
                    System.err.println("Excpetion " + error.getMessage());
                    return null;
                });
    }

    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("crashnight089@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);

            emailSender.send(message);
        } catch (MessagingException ex) {
            throw new RuntimeException(ex);
        }
    }
}