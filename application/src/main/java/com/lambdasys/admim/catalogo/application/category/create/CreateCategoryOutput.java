package com.lambdasys.admim.catalogo.application.category.create;

import com.lambdasys.admin.catalogo.domain.category.Category;
import com.lambdasys.admin.catalogo.domain.category.CategoryID;

public record CreateCategoryOutput(
        CategoryID id
){

    public static CreateCategoryOutput from(Category category){
        return new CreateCategoryOutput(category.getId());
    }

}
