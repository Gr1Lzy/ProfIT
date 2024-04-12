package com.github.project.reader;

public class InvalidDirectoryException extends RuntimeException {
    public InvalidDirectoryException(String message) {
        super(message);
    }
}