package com.roomy.exception;

public class FileException extends RuntimeException{
    public FileException(String message) {
        super(message);
    }

    public FileException() {
        super();
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileException(Throwable cause) {
        super(cause);
    }

}
