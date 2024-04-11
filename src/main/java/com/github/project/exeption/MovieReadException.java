package com.github.project.exeption;

public class MovieReadException extends RuntimeException {
    public MovieReadException(String message, Throwable cause) {
        super(message, cause);
    }
}