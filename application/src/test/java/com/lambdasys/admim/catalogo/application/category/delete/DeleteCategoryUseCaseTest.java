package com.lambdasys.admim.catalogo.application.category.delete;


import com.lambdasys.admin.catalogo.domain.category.Category;
import com.lambdasys.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryUseCaseTest {

    @Mock
    private CategoryGateway categoryGateway;

    @InjectMocks
    private DefaultDeleteCategoryUseCase useCase;

    @BeforeEach
    public void cleanup(){
        Mockito.reset(categoryGateway);
    }

    @Order(1)
    @DisplayName("Given a valid id when calls delete category should be ok")
    @Test
    public void given_a_valid_id_when_calls_delete_category_should_be_ok(){

        var category = Category.newCategory("Filmes","A categoria mais assistida", true);
        var expectedId = category.getId();

        Mockito.doNothing()
                .when(categoryGateway).delete(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow( () -> useCase.execute(DeleteCategoryCommand.from(expectedId.getValue())) );

        Mockito.verify(categoryGateway, Mockito.times(1)).delete(Mockito.eq(expectedId));


    }


    @Order(2)
    @DisplayName("Given a invalid id when calls delete category should be ok")
    @Test
    public void given_a_invalid_id_when_calls_delete_category_should_be_ok(){

        var category = Category.newCategory("Filmes","A categoria mais assistida", true);
        var expectedId = category.getId();

        Mockito.doNothing()
                .when(categoryGateway).delete(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(
                () -> useCase.execute(DeleteCategoryCommand.from(expectedId.getValue())) );

        Mockito.verify(categoryGateway, Mockito.times(1)).delete(Mockito.eq(expectedId));
    }

    @Order(3)
    @DisplayName("Given a valid id when calls gateway throw error should return exception")
    @Test
    public void given_a_valid_id_when_gateway_throw_error_should_return_exception(){

        var category = Category.newCategory("Filmes","A categoria mais assistida", true);
        var expectedId = category.getId();

        Mockito.doThrow(new IllegalStateException("Gateway error"))
                .when(categoryGateway).delete(Mockito.eq(expectedId));

        Assertions.assertThrows(
                IllegalStateException.class ,
                () -> useCase.execute(DeleteCategoryCommand.from(expectedId.getValue())) );

        Mockito.verify(categoryGateway, Mockito.times(1)).delete(Mockito.eq(expectedId));

    }

}
