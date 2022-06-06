package com.lambdasys.admin.catalogo.domain.validation;

public abstract class Validator {

    private final ValidationHandler handler;

    protected Validator(ValidationHandler handler){
        this.handler = handler;
    }

    protected ValidationHandler getValidationHandler(){
        return this.handler;
    }

    public abstract void validate();

}
