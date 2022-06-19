package com.lambdasys.admin.catalogo.domain.pagination;

import java.util.List;
import java.util.function.Function;

public record Pagination<T>(
        int currentPage ,
        int perPage  ,
        long total ,
        List<T> items ) {


    public static <T> Pagination<T> from( int currentPage , int perPage , long total , List<T> items){
        return new Pagination<T>(
                currentPage,
                perPage,
                total,
                items);
    }


    public <R> Pagination<R> map(final Function<T,R> mapper){
        final var mappedItems = this.items.stream().map(mapper).toList();
        return new Pagination<>(
                this.currentPage() ,
                this.perPage() ,
                this.total() ,
                mappedItems
        );
    }

}
