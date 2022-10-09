package ru.dawgg.bookmarket.exception;

public class IncorrectPasswordException extends RuntimeException {
    private static final String MESSAGE = "The password is wrong";

    public IncorrectPasswordException() {
        super(MESSAGE);
    }
}
