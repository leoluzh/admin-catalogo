package com.lambdasys.admim.catalogo.application.category.create;

import com.lambdasys.admin.catalogo.domain.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateCategoryMapper {

    CreateCategoryMapper INSTANCE = Mappers.getMapper(CreateCategoryMapper.class);

    CreateCategoryOutput output(Category category);

}
