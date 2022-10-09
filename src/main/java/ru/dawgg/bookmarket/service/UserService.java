package ru.dawgg.bookmarket.service;

import ru.dawgg.bookmarket.dto.UserDto;
import ru.dawgg.bookmarket.model.User;

import java.util.List;

public interface UserService {
    void signUp(UserDto userDto);
    void save(User user);
    List<UserDto> findAll();
    User findByEmail(String email);
    void enableUser(String token);
}