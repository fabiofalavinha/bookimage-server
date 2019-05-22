package com.bookimage.validator;

public interface Validator<T> {

    ValidationResult validate(T obj);

}
