package com.lambdasys.admin.catalogo.domain.validation.handler;

import com.lambdasys.admin.catalogo.domain.exceptions.DomainException;
import com.lambdasys.admin.catalogo.domain.validation.Error;
import com.lambdasys.admin.catalogo.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa o notification pattern ... utilizada pra ir acumulando erros e remover estrategia de um erro
 * por exception.
 *
 * Abaixo link para documentação do designer pattern.
 * https://martinfowler.com/eaaDev/Notification.html
 *
 */

public class Notification implements ValidationHandler {

    private final List<Error> errors;

    private Notification(final List<Error> errors){
        this.errors = errors;
    }

    public static Notification create(){
        return new Notification(new ArrayList<>());
    }

    public static Notification create(final Error error){
        return new Notification(new ArrayList<>()).append(error);
    }

    public static Notification create(final String message){
        return new Notification(new ArrayList<>()).append(new Error(message));
    }

    public static Notification create(final Throwable throwable){
        return new Notification(new ArrayList<>()).append(new Error(throwable.getMessage()));
    }

    @Override
    public Notification append(final Error error) {
        this.errors.add(error);
        return this;
    }

    @Override
    public Notification append(final ValidationHandler handler) {
        this.errors.addAll(handler.getErrors());
        return this;
    }

    @Override
    public Notification validate(final Validation validation) {

        try{
            validation.validate();
        }catch(final DomainException ex){
            errors.addAll(ex.getErrors());
        }catch(final Throwable ex){
            errors.add(new Error(ex.getMessage()));
        }

        return this;
    }

    @Override
    public List<Error> getErrors() {
        return this.errors;
    }
}
