package com.lambdasys.admim.catalogo.application.category.create;

import com.lambdasys.admim.catalogo.application.UseCase;
import com.lambdasys.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification,CreateCategoryOutput>> {
}
