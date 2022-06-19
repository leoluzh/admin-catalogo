package com.lambdasys.admim.catalogo.application.category.delete;

import com.lambdasys.admin.catalogo.domain.category.Category;
import com.lambdasys.admin.catalogo.domain.category.CategoryGateway;
import com.lambdasys.admin.catalogo.domain.category.CategoryID;
import com.lambdasys.admin.catalogo.domain.exceptions.DomainException;
import com.lambdasys.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class DefaultDeleteCategoryUseCase extends DeleteCategoryUseCase {

    @NonNull
    private final CategoryGateway categoryGateway;

    @Override
    public void execute(final DeleteCategoryCommand input) {
        final var id = CategoryID.from(input.id());
        this.categoryGateway.delete(id);
    }

}
