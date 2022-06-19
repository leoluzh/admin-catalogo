package com.lambdasys.admim.catalogo.application.category.update;

import com.lambdasys.admin.catalogo.domain.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdateCategoryMapper {

    UpdateCategoryMapper INSTANCE = Mappers.getMapper(UpdateCategoryMapper.class);

    UpdateCategoryOutput output(Category category);

}
