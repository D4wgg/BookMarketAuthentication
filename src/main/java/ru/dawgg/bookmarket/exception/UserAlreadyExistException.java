package ru.dawgg.bookmarket.exception;

public class UserAlreadyExistException extends RuntimeException {
    private static final String MESSAGE = "User with the email is already exist";

    public UserAlreadyExistException(String email) {
        super(MESSAGE + " (" + email + ")");
    }
}
