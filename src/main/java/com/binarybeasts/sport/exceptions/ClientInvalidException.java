package com.binarybeasts.sport.exceptions;

public class ClientInvalidException extends RuntimeException {
    public ClientInvalidException(String message) {
        super(message);
    }

    public ClientInvalidException() {
        super("Invalid Client");
    }
}
