package ru.dawgg.bookmarket.service;

public interface EmailSender {
    void send(String to, String email);
}
