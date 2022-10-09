package ru.dawgg.bookmarket.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.dawgg.bookmarket.exception.TokenNotFoundException;
import ru.dawgg.bookmarket.exception.UserAlreadyExistException;
import ru.dawgg.bookmarket.exception.UserNotFoundException;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ErrorMessage> userNotFoundExceptionHandler(UserNotFoundException ex) {
        return commonMessage(ex, NOT_ACCEPTABLE);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    protected ResponseEntity<ErrorMessage> tokenNotFoundExceptionHandler(TokenNotFoundException ex) {
        return commonMessage(ex, NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    protected ResponseEntity<ErrorMessage> userAlreadyExistHandler(UserAlreadyExistException ex) {
        return commonMessage(ex, NOT_ACCEPTABLE);
    }

    private ResponseEntity<ErrorMessage> commonMessage(Exception ex, HttpStatus status) {
        var message = ex.getMessage();
        log.error(message, ex);

        return new ResponseEntity<>(
                ErrorMessage.builder()
                        .message(message)
                        .stackTrace(ExceptionUtils.getStackTrace(ex))
                        .build(),
                status
        );
    }
}
