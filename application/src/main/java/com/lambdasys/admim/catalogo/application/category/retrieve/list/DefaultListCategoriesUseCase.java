package com.lambdasys.admim.catalogo.application.category.retrieve.list;

import com.lambdasys.admin.catalogo.domain.category.CategoryGateway;
import com.lambdasys.admin.catalogo.domain.category.CategorySearchQuery;
import com.lambdasys.admin.catalogo.domain.pagination.Pagination;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultListCategoriesUseCase extends  ListCategoriesUseCase {

    @NonNull
    private final CategoryGateway categoryGateway;

    @Override
    public Pagination<CategoryListOutput> execute(final CategorySearchQuery input) {
        return this.categoryGateway.findAll(input).map(CategoryListOutput::from);
    }

}
