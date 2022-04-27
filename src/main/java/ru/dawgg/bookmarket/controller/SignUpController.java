package ru.dawgg.bookmarket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dawgg.bookmarket.dto.UserDto;
import ru.dawgg.bookmarket.service.UserService;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final UserService service;

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUserUp(@RequestBody UserDto userDto) {
        service.signUp(userDto);
        return ResponseEntity.ok().build();
    }
}