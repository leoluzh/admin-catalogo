package com.lambdasys.admin.catalogo.domain.category;

import com.lambdasys.admin.catalogo.domain.AggregateRoot;
import com.lambdasys.admin.catalogo.domain.validation.ValidationHandler;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class Category extends AggregateRoot<CategoryID> implements Cloneable {

    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(
            CategoryID id,
            String name,
            String description,
            boolean active,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt) {
        super(id);
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Category newCategory(
            String name,
            String description,
            boolean active
    ){
        final var id = CategoryID.from(UUID.randomUUID());
        final var now = Instant.now();
        return new Category(
                id ,
                name ,
                description ,
                active ,
                now ,
                now ,
                active ? null : now );
    }

    public void validate(final ValidationHandler handler){
        new CategoryValidator(this, handler).validate();
    }

    public Category deactivate(){

        this.active = false;
        this.updatedAt = Instant.now();

        if( getDeletedAt() == null ){
            this.deletedAt = this.updatedAt;
        }

        return this;

    }

    public Category activate(){
        this.active = true ;
        this.updatedAt = Instant.now();
        this.deletedAt = null;
        return this;
    }

    public Category update(final String name , final String description , final boolean active ){

        if(active){
            activate();
        }else{
            deactivate();
        }

        this.name = name;
        this.description = description;
        this.updatedAt = Instant.now();
        return this;

    }

    public static Category clone(final Category category){
        return category.clone();
    }

    @Override
    public Category clone(){
        try {
            return (Category)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


}