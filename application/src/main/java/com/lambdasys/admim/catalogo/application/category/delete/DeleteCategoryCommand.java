package com.lambdasys.admim.catalogo.application.category.delete;

import com.lambdasys.admin.catalogo.domain.category.CategoryID;

public record DeleteCategoryCommand(String id) {

    public static DeleteCategoryCommand from(final String id){
        return new DeleteCategoryCommand(id);
    }

}
