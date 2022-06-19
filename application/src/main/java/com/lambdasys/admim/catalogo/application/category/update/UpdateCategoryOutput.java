package com.lambdasys.admim.catalogo.application.category.update;

import com.lambdasys.admin.catalogo.domain.category.Category;
import com.lambdasys.admin.catalogo.domain.category.CategoryID;

public record UpdateCategoryOutput(
        CategoryID id
) {

    public static UpdateCategoryOutput from(
            final Category category){
        return new UpdateCategoryOutput(category.getId());
    }

}
