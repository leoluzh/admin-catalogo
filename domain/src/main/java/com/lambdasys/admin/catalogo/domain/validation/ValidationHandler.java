package com.lambdasys.admin.catalogo.domain.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(Error error);
    ValidationHandler append(ValidationHandler handler);
    ValidationHandler validate(Validation validation);

    default boolean hasErrors(){
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Error firstError(){
        return hasErrors() ? getErrors().get(0) : null;
    }

    public interface Validation {
        void validate();
    }

    List<Error> getErrors();

}
