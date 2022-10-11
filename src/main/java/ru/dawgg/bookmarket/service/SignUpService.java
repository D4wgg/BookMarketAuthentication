package ru.dawgg.bookmarket.service;

import ru.dawgg.bookmarket.dto.UserDto;

public interface SignUpService {
    void signUp(UserDto userDto);
    void activateUserAccount(String token);
}
