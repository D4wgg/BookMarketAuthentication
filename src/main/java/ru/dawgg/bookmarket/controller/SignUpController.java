package ru.dawgg.bookmarket.controller;

import static org.springframework.http.HttpStatus.OK;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dawgg.bookmarket.dto.UserDto;
import ru.dawgg.bookmarket.service.SignUpService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/signUp")
public final class SignUpController {
    private final SignUpService signUpService;

    @PostMapping()
    public ResponseEntity<Void> signUserUp(@RequestBody @Valid UserDto user) {
        signUpService.signUp(user);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/confirm")
    public String confirmEmailToken(final @RequestParam String emailToken) {
        signUpService.activateUserAccount(emailToken);
        return "redirect:/api/v1/login";
    }
}