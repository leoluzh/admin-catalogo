package com.lambdasys.admin.catalogo.domain.category;

public record CategorySearchQuery(
        int page ,
        int perPage ,
        String terms ,
        String sort ,
        String direction ) {

    public static CategorySearchQuery from(
            final int page ,
            final int perPage,
            String terms,
            String sort,
            String direction ){
        return new CategorySearchQuery(page,perPage,terms,sort,direction);
    }

}
