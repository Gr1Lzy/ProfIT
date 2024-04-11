package com.github.project.exeption;

public class InvalidDirectoryException extends RuntimeException {
    public InvalidDirectoryException(String message) {
        super(message);
    }
}