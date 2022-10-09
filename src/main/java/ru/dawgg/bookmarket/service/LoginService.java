package ru.dawgg.bookmarket.service;

import ru.dawgg.bookmarket.dto.LoginDto;
import ru.dawgg.bookmarket.dto.TokenDto;

import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    boolean login(LoginDto loginDto, HttpServletResponse response);
}