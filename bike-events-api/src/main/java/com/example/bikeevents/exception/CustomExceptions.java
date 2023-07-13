package com.example.bikeevents.exception;

public class CustomExceptions {
    public static class NotFoundException extends RuntimeException{
        public NotFoundException(String message) {
            super(message);
        }
    }
    public static class BadRequestException extends RuntimeException{
        public BadRequestException(String message) {
            super(message);
        }
    }
    public static class OwnershipException extends RuntimeException{
        public OwnershipException(String message) {
            super(message);
        }
    }
    public static class AuthenticationException extends RuntimeException{
        public AuthenticationException(String message) {
            super(message);
        }
    }
}



