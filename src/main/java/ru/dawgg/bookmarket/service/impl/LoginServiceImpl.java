package ru.dawgg.bookmarket.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dawgg.bookmarket.dto.LoginDto;
import ru.dawgg.bookmarket.exception.IncorrectPasswordException;
import ru.dawgg.bookmarket.exception.UserNotFoundException;
import ru.dawgg.bookmarket.model.Token;
import ru.dawgg.bookmarket.model.User;
import ru.dawgg.bookmarket.repository.TokenRepository;
import ru.dawgg.bookmarket.repository.UserRepository;
import ru.dawgg.bookmarket.service.LoginService;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    @Override
    public boolean login(LoginDto loginDto, HttpServletResponse response) {
        var user = userRepository.findOneByEmail(loginDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(loginDto.getEmail()));

        if (!userPasswordMatches(user, loginDto)) {
            throw new IncorrectPasswordException();
        }

        final int randomStringLength = 10;
        var token = Token.builder()
                .user(user)
                .value(RandomStringUtils.random(randomStringLength, true, true))
                .build();
        tokenRepository.save(token);
        addCookie(response, user.getEmail(), token.getValue(), user.getName());
        return true;
    }

    private boolean userPasswordMatches(User user, LoginDto loginDto) {
        return passwordEncoder.matches(loginDto.getPassword(), user.getHashPassword());
    }

    private void addCookie(HttpServletResponse response, String email, String token, String userFirstName) {
        int weekPerSeconds = 7 * 24 * 60 * 60;

        response.addCookie(createCookie("email", email, weekPerSeconds));
        response.addCookie(createCookie("token", token, weekPerSeconds));
        response.addCookie(createCookie("name", userFirstName, weekPerSeconds));
    }

    private Cookie createCookie(String name, String value, int expirationTime) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(expirationTime);
        cookie.setSecure(true);
        return cookie;
    }
}