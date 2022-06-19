package com.lambdasys.admim.catalogo.application.category.retrieve.list;


import com.lambdasys.admin.catalogo.domain.category.Category;
import com.lambdasys.admin.catalogo.domain.category.CategoryGateway;
import com.lambdasys.admin.catalogo.domain.category.CategorySearchQuery;
import com.lambdasys.admin.catalogo.domain.pagination.Pagination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ListCategoriesUseCaseTest {

    @Mock
    private CategoryGateway categoryGateway;

    @InjectMocks
    private DefaultListCategoriesUseCase useCase;

    @BeforeEach
    public void setup(){
        Mockito.reset(categoryGateway);
    }

    //1. retornar infos
    //2. retorna vazio
    //3. gateway gera erro.

    @Test
    @Order(1)
    public void given_a_valid_query_when_call_list_categories_then_should_return_categories(){

        final var categories = List.of(
                Category.newCategory("Filmes", "Uma descricao para filmes", true) ,
                Category.newCategory("Series", "Uma descricao para series", true)
        );

        final var expectedPage = 0 ;
        final var expectedPerPage = 10;
        final var expectedTerms = "" ;
        final var expectedSort = "createdAt" ;
        final var expectedDirection = "asc" ;

        final var query = CategorySearchQuery.from(
                expectedPage,
                expectedPerPage,
                expectedTerms,
                expectedSort,
                expectedDirection);



        final var expectedPagination = Pagination.from(
                expectedPage,
                expectedPerPage,
                categories.size(),
                categories);


        final var expectedItemsCount = 2;
        final var expectedResult = expectedPagination.map(CategoryListOutput::from);


        Mockito.when(categoryGateway.findAll(Mockito.eq(query))).thenReturn(expectedPagination);

        final var actualResult = useCase.execute(query);

        Assertions.assertEquals(expectedItemsCount,actualResult.items().size());
        Assertions.assertEquals(expectedPage,actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage,actualResult.perPage());
        Assertions.assertEquals(categories.size(),actualResult.total());

    }

    @Test
    @Order(2)
    public void given_a_valid_query_when_call_list_categories_then_should_return_empty_categories(){

        final var categories = List.<Category>of();

        final var expectedPage = 0 ;
        final var expectedPerPage = 10;
        final var expectedTerms = "" ;
        final var expectedSort = "createdAt" ;
        final var expectedDirection = "asc" ;

        final var query = CategorySearchQuery.from(
                expectedPage,
                expectedPerPage,
                expectedTerms,
                expectedSort,
                expectedDirection);



        final var expectedPagination = Pagination.from(
                expectedPage,
                expectedPerPage,
                categories.size(),
                categories);


        final var expectedItemsCount = 0;
        final var expectedResult = expectedPagination.map(CategoryListOutput::from);


        Mockito.when(categoryGateway.findAll(Mockito.eq(query))).thenReturn(expectedPagination);

        final var actualResult = useCase.execute(query);

        Assertions.assertEquals(expectedItemsCount,actualResult.items().size());
        Assertions.assertEquals(expectedPage,actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage,actualResult.perPage());
        Assertions.assertEquals(categories.size(),actualResult.total());

    }

    @Test
    @Order(3)
    public void given_a_valid_query_when_call_list_categories_gateway_throws_exception_then_should_return_exception(){

        final var categories = List.of(
                Category.newCategory("Filmes", "Uma descricao para filmes", true) ,
                Category.newCategory("Series", "Uma descricao para series", true)
        );

        final var expectedPage = 0 ;
        final var expectedPerPage = 10;
        final var expectedTerms = "" ;
        final var expectedSort = "createdAt" ;
        final var expectedDirection = "asc" ;
        final var expectedErrorMessage = "Gateway error" ;

        final var query = CategorySearchQuery.from(
                expectedPage,
                expectedPerPage,
                expectedTerms,
                expectedSort,
                expectedDirection);


        Mockito.when(categoryGateway.findAll(Mockito.eq(query))).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(query));

        Assertions.assertEquals(expectedErrorMessage,actualException.getMessage());

    }

}
