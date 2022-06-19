package com.lambdasys.admim.catalogo.application.category.retrieve.get;

import com.lambdasys.admin.catalogo.domain.category.CategoryGateway;
import com.lambdasys.admin.catalogo.domain.category.CategoryID;
import com.lambdasys.admin.catalogo.domain.exceptions.DomainException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {

    @NonNull
    private final CategoryGateway categoryGateway;

    @Override
    public CategoryOutput execute(final GetCategoryByIdCommand input) {

        final var id = CategoryID.from(input.id());

        return categoryGateway.findById(id)
                .map(CategoryOutput::from)
                .orElseThrow(notFound(id));

    }

    private Supplier<DomainException> notFound(CategoryID id) {
        return () -> DomainException.from(String.format("Category with id %s was not found",id.getValue()));
    }
}
