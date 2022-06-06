package com.lambdasys.admin.catalogo.domain.category;

import com.lambdasys.admin.catalogo.domain.Identifier;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CategoryID extends Identifier {

    @EqualsAndHashCode.Include
    private final String value;

    private CategoryID(final String value){
        Objects.requireNonNull( value ,"value should not be null");
        this.value = value;
    }

    public static CategoryID unique(){
        return CategoryID.from(UUID.randomUUID()) ;
    }

    public static CategoryID from(final String id){
        return new CategoryID(id);
    }

    public static CategoryID from(final UUID id){
        return new CategoryID(id.toString());
    }

}
