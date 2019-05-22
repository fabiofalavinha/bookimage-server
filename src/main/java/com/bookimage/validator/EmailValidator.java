package com.bookimage.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator implements Validator<String> {

    private static final Pattern EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public ValidationResult validate(String obj) {
        final ValidationResult validationResult = new ValidationResult();
        if (!EMAIL_ADDRESS_REGEX.matcher(obj).matches()) {
            validationResult.setErrorMessage("Invalid email");
        }
        return validationResult;
    }
}
