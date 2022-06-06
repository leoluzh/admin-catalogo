package com.lambdasys.admim.catalogo.application.category.create;

import com.lambdasys.admin.catalogo.domain.category.Category;
import com.lambdasys.admin.catalogo.domain.category.CategoryGateway;
import com.lambdasys.admin.catalogo.domain.validation.handler.Notification;
import static io.vavr.API.Left;
import static io.vavr.API.Try;
import io.vavr.control.Either;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    @NonNull
    private final CategoryGateway categoryGateway;

    @Override
    public Either<Notification,CreateCategoryOutput> execute(final CreateCategoryCommand input) {

        final var name = input.name();
        final var description = input.description();
        final var active = input.active();
        final var category = Category.newCategory(name,description,active);
        final var notification = Notification.create();

        category.validate(notification);

        return notification.hasErrors() ? Left(notification) : create(category);

    }

    private Either<Notification,CreateCategoryOutput> create(final Category category){

        return Try( () ->  this.categoryGateway.create(category) )
                .toEither()
                .bimap(Notification::create,CreateCategoryOutput::from);

    }

}
