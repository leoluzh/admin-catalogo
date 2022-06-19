package com.lambdasys.admim.catalogo.application.category.update;

import com.lambdasys.admim.catalogo.application.UseCase;
import com.lambdasys.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification,UpdateCategoryOutput>> {
}
