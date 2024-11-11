package org.example.service;

public interface EmailService {

    void sendSimpleMessage(String mail,String to, String subject, String text);
    void sendMessageWithAttachment(String mail,String to, String subject, String text, String pathToAttachment);

}

