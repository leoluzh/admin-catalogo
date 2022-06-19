package com.lambdasys.admim.catalogo.application.category.retrieve.list;

import com.lambdasys.admim.catalogo.application.UseCase;
import com.lambdasys.admin.catalogo.domain.category.CategorySearchQuery;
import com.lambdasys.admin.catalogo.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {

}
