package ru.dawgg.bookmarket.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
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

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    @Value("${time.expiresAt}")
    private String expirationTime;

    @Value("${algorithm.secret}")
    private String secret;

    @Override
    public boolean login(LoginDto loginDto, HttpServletResponse response) {
        var user = userRepository.findOneByEmail(loginDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(loginDto.getEmail()));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getHashPassword())) {
            throw new IncorrectPasswordException();
        }

        var token = Token.builder()
                .user(user)
                .value(createJwt(user))
                .build();
        tokenRepository.save(token);

        var cookie = createCookie(token.getValue());
        response.addCookie(cookie);
        return true;
    }

    private String createJwt(User user) {
        Map<String, String> map = Map.of(
                "email", user.getEmail(),
                "role", user.getRole().name(),
                "name", user.getName()
        );

        return JWT.create()
                .withSubject(user.getHashPassword())
                .withExpiresAt(new Date(System.currentTimeMillis() + Integer.parseInt(expirationTime)))
                .withClaim("user", map)
                .sign(Algorithm.HMAC256(secret.getBytes()));
    }

    private Cookie createCookie(String jwt) {
        Cookie cookie = new Cookie("token", jwt);
        cookie.setMaxAge(Integer.parseInt(expirationTime));
        cookie.setSecure(true);
        return cookie;
    }
}