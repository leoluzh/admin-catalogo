package com.lambdasys.admin.catalogo.domain.pagination;

public record Pagination<T>(
        int currentPage ,
        int perPage  ,
        long total ,
        T itens ) {
}
