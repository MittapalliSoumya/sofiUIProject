package com.swaglabs.sofiUi.exceptions;

public class SwaglabsException extends RuntimeException {

    public SwaglabsException() {
        super();
    }

    public SwaglabsException(String message) {
        super(message);
    }

    public SwaglabsException(String message, Throwable cause) {
        super(message, cause);
    }
}
