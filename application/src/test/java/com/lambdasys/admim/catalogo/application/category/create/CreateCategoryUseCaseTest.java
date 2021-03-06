package com.lambdasys.admim.catalogo.application.category.create;

import com.lambdasys.admin.catalogo.domain.category.Category;
import com.lambdasys.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @Mock
    private CategoryGateway categoryGateway;

    @InjectMocks
    private DefaultCreateCategoryUseCase useCase;


    @BeforeEach
    void cleanUp(){
        Mockito.reset(categoryGateway);
    }

    //1. teste caminho feliz ...
    //2. teste passando propriedade invalida ...
    //3. teste criando categoria inativa ...
    //4. teste simulando erro generico vindo do gateway...

    @Test
    @Order(1)
    public void given_a_valid_command_when_call_create_category_should_return_category_id(){

        final var expectedName = "Filmes" ;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;
        final var expectedCategory = Category.newCategory(expectedName,expectedDescription,expectedActive);

        final var aCommand = CreateCategoryCommand.from(expectedName,expectedDescription,expectedActive);

        //mockito
        when(categoryGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        verify(categoryGateway,times(1)).create(argThat(aCategory -> {
            return Objects.equals(expectedName, aCategory.getName())
                    && Objects.equals(expectedDescription,aCategory.getDescription())
                    && Objects.equals(expectedActive,aCategory.isActive())
                    && Objects.nonNull(aCategory.getId())
                    && Objects.nonNull(aCategory.getCreatedAt())
                    && Objects.nonNull(aCategory.getUpdatedAt())
                    && Objects.isNull(aCategory.getDeletedAt());
        }));

    }

    @Test
    @Order(2)
    public void given_an_invalid_name_when_calls_create_category_then_should_return_domain_exception(){

        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;
        final var expectedErrorMessage = "name should not be null";
        final var expectedErrorCount = 1;
        final var expectedCategory = Category.newCategory(expectedName,expectedDescription,expectedActive);

        final var aCommand = CreateCategoryCommand.from(expectedName,expectedDescription,expectedActive);

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount,notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage,notification.firstError().message());

        verify(categoryGateway,times(0)).create(any());

    }

    @Test
    @Order(3)
    public void given_a_valid_command_with_inactive_category_when_call_create_category_should_return_inactive_category_id(){

        final var expectedName = "Filmes" ;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = false;
        final var expectedCategory = Category.newCategory(expectedName,expectedDescription,expectedActive);

        final var aCommand = CreateCategoryCommand.from(expectedName,expectedDescription,expectedActive);

        //mockito
        when(categoryGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        verify(categoryGateway,times(1)).create(argThat(aCategory -> {
            return Objects.equals(expectedName, aCategory.getName())
                    && Objects.equals(expectedDescription,aCategory.getDescription())
                    && Objects.equals(expectedActive,aCategory.isActive())
                    && Objects.nonNull(aCategory.getId())
                    && Objects.nonNull(aCategory.getCreatedAt())
                    && Objects.nonNull(aCategory.getUpdatedAt())
                    && Objects.nonNull(aCategory.getDeletedAt());
        }));

    }

    @Test
    @Order(4)
    public void given_a_valid_command_when_gateway_throws_random_exception_should_return_an_exception(){

        final var expectedName = "Filmes" ;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Gateway exception";
        final var expectedCategory = Category.newCategory(expectedName,expectedDescription,expectedActive);

        final var aCommand = CreateCategoryCommand.from(expectedName,expectedDescription,expectedActive);

        //mockito
        when(categoryGateway.create(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount,notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage,notification.firstError().message());

        verify(categoryGateway,times(1)).create(argThat(aCategory -> {
            return Objects.equals(expectedName, aCategory.getName())
                    && Objects.equals(expectedDescription,aCategory.getDescription())
                    && Objects.equals(expectedActive,aCategory.isActive())
                    && Objects.nonNull(aCategory.getId())
                    && Objects.nonNull(aCategory.getCreatedAt())
                    && Objects.nonNull(aCategory.getUpdatedAt())
                    && Objects.isNull(aCategory.getDeletedAt());
        }));

    }

}
