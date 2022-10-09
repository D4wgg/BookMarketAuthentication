package ru.dawgg.bookmarket.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String MESSAGE = "User has not been found";

    public UserNotFoundException() {
        super(MESSAGE);
    }

    public UserNotFoundException(Long id) {
        super(MESSAGE + " by id - " + id);
    }

    public UserNotFoundException(String email) {
        super(MESSAGE + " by an email - " + email);
    }
}
