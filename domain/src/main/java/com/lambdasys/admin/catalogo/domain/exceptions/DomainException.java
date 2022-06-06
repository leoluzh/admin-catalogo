package com.lambdasys.admin.catalogo.domain.exceptions;

import com.lambdasys.admin.catalogo.domain.validation.Error;
import lombok.Getter;

import java.util.List;

@Getter
public class DomainException extends  NoStackTraceException {

    private final List<Error> errors;

    private DomainException(String message , List<Error> errors){
        super(message);
        this.errors = errors;
    }

    public static DomainException from(String message){
        return new DomainException(message,List.of(new Error(message)));
    }

    public static DomainException from(List<String> messages){
        return new DomainException("Some errors has ocurred",messages.stream().map(Error::new).toList() );
    }

    public static DomainException of(Error error){
        return new DomainException(error.message(),List.of(error));
    }

    public static DomainException of(List<Error> errors){
        return new DomainException("Some errors has ocurred",errors);
    }

}
