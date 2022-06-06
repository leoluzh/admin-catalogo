package com.lambdasys.admin.catalogo.domain.category;

import com.lambdasys.admin.catalogo.domain.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {

    Category create(Category category);
    void delete(CategoryID categoryID);
    Optional<Category> findById(CategoryID categoryID);
    Category update(Category category);
    Pagination<Category> findAll(CategorySearchQuery query);

}
