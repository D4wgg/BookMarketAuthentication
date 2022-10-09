package ru.dawgg.bookmarket.exception;

public class TokenNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Token has not been found";

    public TokenNotFoundException() {
        super(MESSAGE);
    }

    public TokenNotFoundException(Long id) {
        super(MESSAGE +  " by id - " + id);
    }
}
