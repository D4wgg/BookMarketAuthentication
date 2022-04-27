package ru.dawgg.bookmarket.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dawgg.bookmarket.dto.LoginDto;
import ru.dawgg.bookmarket.dto.TokenDto;
import ru.dawgg.bookmarket.exception.InnerApiException;
import ru.dawgg.bookmarket.model.Token;
import ru.dawgg.bookmarket.model.User;
import ru.dawgg.bookmarket.repository.TokenRepository;
import ru.dawgg.bookmarket.repository.UserRepository;
import ru.dawgg.bookmarket.service.LoginService;

import java.util.Optional;

import static ru.dawgg.bookmarket.exception.ApiEntityNotFoundException.USER_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final ModelMapper mapper;

    public TokenDto login(LoginDto loginDto) {
        Optional<User> userCandidate = userRepository.findOneByLogin(loginDto.getLogin());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            if (passwordEncoder.matches(loginDto.getPassword(), user.getHashPassword())) {
                Token token = Token.builder()
                        .user(user)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();

                tokenRepository.save(token);

                return mapper.map(token, TokenDto.class);
            }
        }
        throw new InnerApiException(USER_NOT_FOUND_EXCEPTION);
    }
}