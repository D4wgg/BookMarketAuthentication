package ru.dawgg.bookmarket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dawgg.bookmarket.dto.LoginDto;
import ru.dawgg.bookmarket.service.LoginService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping()
    public String login(@RequestBody @Valid LoginDto loginDto, HttpServletResponse response) {
        if (loginService.login(loginDto, response)) {
            return "redirect:/funcapi/v1/authors";
        }
        return "redirect:/api/v1/login";
    }
}