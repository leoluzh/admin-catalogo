package com.lambdasys.admin.catalogo.domain.validation.handler;

import com.lambdasys.admin.catalogo.domain.exceptions.DomainException;
import com.lambdasys.admin.catalogo.domain.validation.Error;
import com.lambdasys.admin.catalogo.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(Error error) {
        throw DomainException.of(error);
    }

    @Override
    public ValidationHandler append(ValidationHandler handler) {
        throw DomainException.of(handler.getErrors());
    }

    @Override
    public ValidationHandler validate(Validation validation) {
        try{
            validation.validate();
        }catch(Exception ex){
            throw DomainException.from(ex.getMessage());
        }
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
