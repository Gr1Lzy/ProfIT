package com.github.project.exeption;

public class MovieWriteException extends RuntimeException {
    public MovieWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
