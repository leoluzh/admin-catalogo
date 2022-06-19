package com.lambdasys.admim.catalogo.application.category.delete;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeleteCategoryMapper {

    DeleteCategoryMapper INSTANCE = Mappers.getMapper(DeleteCategoryMapper.class);

}
