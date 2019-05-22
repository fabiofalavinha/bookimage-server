package com.bookimage.validator;

import com.bookimage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements Validator<User> {

    private final EmailValidator emailValidator;

    @Autowired
    public UserValidator(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    @Override
    public ValidationResult validate(User user) {
        final ValidationResult validationResult = new ValidationResult();
        if (user == null) {
            validationResult.setErrorMessage("Missing user information");
        } else {
            final ValidationResult emailValidationResult = emailValidator.validate(user.getEmail());
            if (emailValidationResult.hasError()) {
                return emailValidationResult;
            }
            if (user.getName() == null || user.getName().isEmpty()) {
                validationResult.setErrorMessage("Name is required");
            } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
                validationResult.setErrorMessage("Password is required");
            }
        }
        return validationResult;
    }
}
