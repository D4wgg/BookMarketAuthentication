package ru.dawgg.bookmarket.service;

public interface EmailBuilder {
    String buildEmail(String to, String link);
}
