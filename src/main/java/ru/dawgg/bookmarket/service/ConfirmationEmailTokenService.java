package ru.dawgg.bookmarket.service;

import ru.dawgg.bookmarket.model.ConfirmationEmailToken;
import ru.dawgg.bookmarket.model.User;

public interface ConfirmationEmailTokenService {
    void save(ConfirmationEmailToken token);
    ConfirmationEmailToken find(String token);
    int setConfirmedAt(String token);
    ConfirmationEmailToken confirmToken(String token);
    ConfirmationEmailToken buildEmailToken(User user, String token);
    void confirmUserEmail(User user);
}
