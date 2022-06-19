package com.lambdasys.admim.catalogo.application.category.update;

public record UpdateCategoryCommand(
        String id,
        String name,
        String description,
        boolean active

) {

    public static UpdateCategoryCommand from(
            final String id,
            final String name,
            final String description,
            final boolean active){
        return new UpdateCategoryCommand(id,name,description,active);
    }

}
