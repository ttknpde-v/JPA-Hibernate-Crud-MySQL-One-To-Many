package com.ttknp.springbootcrudonetomany.exception.handler;

public class NotAllowedMethod extends RuntimeException {
    public NotAllowedMethod() {
        super();
    }
    public NotAllowedMethod(String message) {
        super(message);
    }
}
