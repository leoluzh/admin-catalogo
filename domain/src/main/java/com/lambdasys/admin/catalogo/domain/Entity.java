package com.lambdasys.admin.catalogo.domain;

import com.lambdasys.admin.catalogo.domain.validation.ValidationHandler;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@EqualsAndHashCode(callSuper = false)
public abstract class Entity<I extends Identifier> {

    @EqualsAndHashCode.Include
    protected final I id;

    protected Entity(final I id){
        Objects.requireNonNull(id,"id should not be null");
        this.id = id;
    }

    public abstract void validate(ValidationHandler handler);

}
