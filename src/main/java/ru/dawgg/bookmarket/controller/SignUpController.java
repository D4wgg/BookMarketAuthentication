package ru.dawgg.bookmarket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dawgg.bookmarket.dto.UserDto;
import ru.dawgg.bookmarket.service.UserService;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SignUpController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUserUp(@RequestBody @Valid UserDto userDto) {
        userService.signUp(userDto);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/confirm")
    public ResponseEntity<Void> confirmEmailToken(@RequestParam String token) {
        userService.enableUser(token);
        return new ResponseEntity<>(OK);
    }
}