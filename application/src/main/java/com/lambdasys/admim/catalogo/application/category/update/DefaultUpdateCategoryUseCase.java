package com.lambdasys.admim.catalogo.application.category.update;

import com.lambdasys.admin.catalogo.domain.category.Category;
import com.lambdasys.admin.catalogo.domain.category.CategoryGateway;
import com.lambdasys.admin.catalogo.domain.category.CategoryID;
import com.lambdasys.admin.catalogo.domain.exceptions.DomainException;
import com.lambdasys.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    @NonNull
    private final CategoryGateway categoryGateway;

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(final UpdateCategoryCommand command) {

        final var id = CategoryID.from(command.id());
        final var name = command.name();
        final var description = command.description();
        final var active = command.active();

        final var aCategory = this.categoryGateway.findById(id).orElseThrow(notFound(id));

        final var notification = Notification.create();

        aCategory
                .update(name,description,active)
                .validate(notification);

        return notification.hasErrors() ? Left(notification) : update(aCategory) ;

    }

    private Either<Notification,UpdateCategoryOutput> update(final Category category){
        return Try( () -> this.categoryGateway.update(category) )
                .toEither()
                .bimap(Notification::create,UpdateCategoryOutput::from);
    }

    private Supplier<DomainException> notFound(final CategoryID id) {
        return () ->
                DomainException.from(String.format("Category with id %s was not found", id.getValue()));
    }

}
