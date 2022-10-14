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
import ru.dawgg.bookmarket.repository.ConfirmationEmailTokenRepository;
import ru.dawgg.bookmarket.service.SignUpService;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SignUpController {

    private final SignUpService signUpService;
    private final ConfirmationEmailTokenRepository repository;

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUserUp(@RequestBody @Valid UserDto userDto) {
        signUpService.signUp(userDto);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/confirm")
    public String confirmEmailToken(@RequestParam String token) {
        signUpService.activateUserAccount(token);
        return "redirect:/api/v1/login";
    }

    @GetMapping("/findToken")
    public String findToken(@RequestParam String token) {
        if (repository.findByToken(token).isPresent()) {
            return "Found";
        }
        return "Not found";
    }
}