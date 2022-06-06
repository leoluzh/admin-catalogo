package com.lambdasys.admin.catalogo.domain.category;

import com.lambdasys.admin.catalogo.domain.validation.Error;
import com.lambdasys.admin.catalogo.domain.validation.ValidationHandler;
import com.lambdasys.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {

    public static final int NAME_MIN_LENGTH = 3 ;
    public static final int NAME_MAX_LENGTH = 255;

    private final Category category;

    public CategoryValidator( Category category , final ValidationHandler handler){
        super(handler);
        this.category = category;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.category.getName();
        if( name == null ){
            this.getValidationHandler().append(new Error("name should not be null"));
            return;
        }
        if( name.isBlank()){
            this.getValidationHandler().append(new Error("name should not be empty"));
            return;
        }
        final var length = name.trim().length();
        if( length < NAME_MIN_LENGTH ||  length > NAME_MAX_LENGTH ){
            this.getValidationHandler().append(new Error("name must be between 3 and 255 characters"));
            return;
        }
    }
}
