package com.bookimage.validator;

public class ValidationResult {

    private String errorMessage;

    public boolean hasError() {
        return errorMessage != null && !errorMessage.isEmpty();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
