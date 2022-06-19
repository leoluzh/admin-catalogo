package com.lambdasys.admim.catalogo.application.category.retrieve.get;


import com.lambdasys.admin.catalogo.domain.category.Category;
import com.lambdasys.admin.catalogo.domain.category.CategoryGateway;
import com.lambdasys.admin.catalogo.domain.exceptions.DomainException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GetCategoryByIdUseCaseTest {

    @Mock
    private CategoryGateway categoryGateway;

    @InjectMocks
    private DefaultGetCategoryByIdUseCase useCase;


    @BeforeEach
    public void cleanup(){
        Mockito.reset(categoryGateway);
    }


    @Order(1)
    @DisplayName("Given a valid id when calls get category by id should be ok")
    @Test
    public void given_a_valid_id_when_calls_get_category_by_id_should_be_ok(){

        final var expectedName = "Filmes" ;
        final var expectedDescription = "A categoria mais assistida" ;
        final var expectedActive = true;

        final var category = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedActive);

        final var expectedId = category.getId();

        Mockito.when(categoryGateway.findById(expectedId)).thenReturn(Optional.of(category.clone()));

        final var actualOutput = useCase.execute(GetCategoryByIdCommand.from(expectedId.getValue()));

        Assertions.assertNotNull(actualOutput);

    }


    @Order(2)
    @DisplayName("Given a invalid id when calls get category should be not found")
    @Test
    public void given_a_invalid_id_when_calls_delete_category_should_be_ok(){

        final var expectedName = "Filmes" ;
        final var expectedDescription = "A categoria mais assistida" ;
        final var expectedActive = true;

        final var category = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedActive);

        final var expectedId = category.getId();
        final var expectedErrorMessage = String.format("Category with id %s was not found",expectedId.getValue()) ;

        Mockito.when(categoryGateway.findById(expectedId)).thenReturn(Optional.empty());

       final var actualException = Assertions.assertThrows(
               DomainException.class,
               () -> useCase.execute(GetCategoryByIdCommand.from(expectedId.getValue())) );

       Assertions.assertEquals(expectedErrorMessage,actualException.getMessage());

    }

    @Order(3)
    @DisplayName("Given a valid id when calls gateway throw error should return exception")
    @Test
    public void given_a_valid_id_when_gateway_throw_error_should_return_exception(){

        final var expectedErrorMessage = "Gateway error" ;
        final var expectedName = "Filmes" ;
        final var expectedDescription = "A categoria mais assistida" ;
        final var expectedActive = true;

        final var category = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedActive);

        final var expectedId = category.getId();

        Mockito.when(categoryGateway.findById(expectedId)).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(GetCategoryByIdCommand.from(expectedId.getValue())) );

        Assertions.assertEquals(expectedErrorMessage,actualException.getMessage());

    }

}
