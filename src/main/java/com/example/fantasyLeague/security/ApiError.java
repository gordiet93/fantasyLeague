package com.example.fantasyLeague.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.ConstraintViolation;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private List<ApiValidationError> apiValidationErrors;

    public ApiError(){
        setTimestamp(LocalDateTime.now());
    }

    public ApiError(HttpStatus status) {
        super();
        this.status = status;
    }

    private void addValidationError(ApiValidationError validationError) {
        if (apiValidationErrors == null) {
            apiValidationErrors = new ArrayList<>();
        }
        apiValidationErrors.add(validationError);
    }

    private void addValidationError (String object, String field, Object rejectedValue, String message) {
        addValidationError(new ApiValidationError(object, field, rejectedValue, message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(fieldError.getObjectName(), fieldError.getField(),
                fieldError.getRejectedValue(), fieldError.getDefaultMessage());
    }

    private void addValidationErrors(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationErrors);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ApiValidationError> getApiValidationErrors() {
        return apiValidationErrors;
    }

    public void setApiValidationErrors(List<ApiValidationError> apiValidationErrors) {
        this.apiValidationErrors = apiValidationErrors;
    }
}
