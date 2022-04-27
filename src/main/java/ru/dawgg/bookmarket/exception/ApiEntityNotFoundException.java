package ru.dawgg.bookmarket.exception;

public class ApiEntityNotFoundException extends InnerApiException {

    public static final String USER_NOT_FOUND_EXCEPTION = "User is not found";
    public static final String TOKEN_NOT_FOUND_EXCEPTION = "Token is not found";

    public ApiEntityNotFoundException(String message) {
        super(message + "! The properties are wrong, recheck it carefully");
    }

    public ApiEntityNotFoundException(String message , Long id) {
        super(message + " id: " + id);
    }
}
