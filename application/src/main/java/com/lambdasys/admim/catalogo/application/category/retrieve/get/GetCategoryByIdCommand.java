package com.lambdasys.admim.catalogo.application.category.retrieve.get;

public record GetCategoryByIdCommand(String id) {

    public static GetCategoryByIdCommand from(String id){
        return new GetCategoryByIdCommand(id);
    }

}
