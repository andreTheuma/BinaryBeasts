package com.binarybeasts.sport.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("User with ID '" + id + "' not found.");
    }
}
