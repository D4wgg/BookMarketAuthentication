package ru.dawgg.bookmarket.exception;

public class EmailSendException extends RuntimeException {
    private static final String MESSAGE = "Failed to send an email";

    public EmailSendException() {
        super(MESSAGE);
    }
}
