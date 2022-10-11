package ru.dawgg.bookmarket.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dawgg.bookmarket.dto.UserDto;
import ru.dawgg.bookmarket.service.ConfirmationEmailTokenService;
import ru.dawgg.bookmarket.service.SignUpService;
import ru.dawgg.bookmarket.service.UserService;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final UserService userService;
    private final ConfirmationEmailTokenService emailTokenService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void signUp(UserDto userDto) {
        var user = userService.buildUser(userDto);
        if (!userService.userAlreadyExists(user)) {
            userService.save(user);
            emailTokenService.confirmUserEmail(user);
        }
    }
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void activateUserAccount(String token) {
        var confirmationToken = emailTokenService.confirmToken(token);
        var email = confirmationToken.getUser().getEmail();
        userService.enableUser(email);
    }
}
