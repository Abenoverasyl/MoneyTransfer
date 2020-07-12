package com.transfer.app.integration;

public class IntegrationException extends RuntimeException {
    private String source;

    public IntegrationException(String source, String message) {
        super(message);
        this.source = source;
    }
}
