package ru.dawgg.bookmarket.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dawgg.bookmarket.exception.ConfirmationTokenException;
import ru.dawgg.bookmarket.model.ConfirmationEmailToken;
import ru.dawgg.bookmarket.model.User;
import ru.dawgg.bookmarket.repository.ConfirmationEmailTokenRepository;
import ru.dawgg.bookmarket.service.ConfirmationEmailTokenService;
import ru.dawgg.bookmarket.service.EmailSender;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationEmailTokenServiceImpl implements ConfirmationEmailTokenService {
    private final ConfirmationEmailTokenRepository repository;
    private final EmailSender emailSender;

    @Override
    @SneakyThrows
    public void confirmUserEmail(User user) {
        var token = UUID.randomUUID().toString();
        var emailToken = buildEmailToken(user, token);
        save(emailToken);

        String link = "http://localhost:8080/api/v1/confirm?emailToken=" + token;
        emailSender.send(user.getEmail(), link);
    }

    @Override
    @Transactional
    public ConfirmationEmailToken confirmToken(String token) {
        var confirmationToken = find(token);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new ConfirmationTokenException("token already confirmed");
        }

        var expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new ConfirmationTokenException("token expired");
        }
        setConfirmedAt(token);

        return confirmationToken;
    }

    @Override
    public int setConfirmedAt(String token) {
        return repository.setConfirmedAt(token, LocalDateTime.now());
    }

    @Override
    public ConfirmationEmailToken buildEmailToken(User user, String token) {
        return ConfirmationEmailToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
    }

    @Override
    public void save(ConfirmationEmailToken token) {
        repository.save(token);
    }

    @Override
    public ConfirmationEmailToken find(String token) {
        return repository.findByToken(token)
                .orElseThrow(() -> new ConfirmationTokenException("confirmation token not found"));
    }
}
