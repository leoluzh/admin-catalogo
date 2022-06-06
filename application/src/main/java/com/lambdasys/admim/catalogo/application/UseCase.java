package com.lambdasys.admim.catalogo.application;

import com.lambdasys.admin.catalogo.domain.category.Category;

/**
 * Basic pattern command.
 * @param <IN>
 * @param <OUT>
 */
public abstract class UseCase<IN,OUT> {

    public abstract OUT execute(IN input);

}