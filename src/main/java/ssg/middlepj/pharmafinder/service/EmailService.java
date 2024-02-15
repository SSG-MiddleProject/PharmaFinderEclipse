package ssg.middlepj.pharmafinder.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}