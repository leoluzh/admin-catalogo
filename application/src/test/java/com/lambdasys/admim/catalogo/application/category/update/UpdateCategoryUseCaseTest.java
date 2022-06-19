package com.lambdasys.admim.catalogo.application.category.update;

import com.lambdasys.admim.catalogo.application.category.create.CreateCategoryCommand;
import com.lambdasys.admim.catalogo.application.category.create.DefaultCreateCategoryUseCase;
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
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {

    @Mock
    private CategoryGateway categoryGateway;

    @InjectMocks
    private DefaultUpdateCategoryUseCase useCase;

    @BeforeEach
    void cleanUp(){
        Mockito.reset(categoryGateway);
    }

    //1. teste caminho feliz ...
    //2. teste passando propriedade invalida ...
    //3. teste criando categoria inativa ...
    //4. teste simulando erro generico vindo do gateway...
    //5. teste atualizar categoria passando ID inválido...

    @Test
    @Order(1)
    public void givenAValidCommand_whenCallUpdateCategory_shouldReturnCategoryId(){

        final var expectedName = "Filmes" ;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;

        final var aCategory = Category.newCategory(expectedName,expectedDescription,expectedActive);
        final var expectedId = aCategory.getId();

        final var aCommand = UpdateCategoryCommand.from(
                expectedId.getValue(),
                expectedName,
                expectedDescription,
                expectedActive);

        //mockito - findbyid
        when(categoryGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(Category.clone(aCategory)));
        //mockito - update
        when(categoryGateway.update(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        verify(categoryGateway,times(1)).findById(eq(expectedId));

        verify(categoryGateway,times(1)).update(argThat(aUpdateCategory -> {
            return Objects.equals(expectedName, aCategory.getName())
                    && Objects.equals(expectedDescription,aCategory.getDescription())
                    && Objects.equals(expectedActive,aCategory.isActive())
                    && Objects.equals(expectedId,aUpdateCategory.getId())
                    && Objects.equals(aCategory.getCreatedAt(),aUpdateCategory.getCreatedAt())
                    && aCategory.getUpdatedAt().isBefore(aUpdateCategory.getUpdatedAt())
                    && Objects.isNull(aUpdateCategory.getDeletedAt());
        }));

    }

    @Test
    @Order(2)
    public void givenAnInvalidName_whenCallsUpdateCategory_thenShouldReturnDomainException(){

        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;
        final var expectedErrorMessage = "name should not be null";
        final var expectedErrorCount = 1;
        final var expectedCategory = Category.newCategory(expectedName,expectedDescription,expectedActive);

        final var aCategory = Category.newCategory(expectedName,expectedDescription,expectedActive);
        final var expectedId = aCategory.getId();

        final var aCommand = UpdateCategoryCommand.from(
                expectedId.getValue(),
                expectedName,
                expectedDescription,
                expectedActive);

        //mockito - findbyid
        when(categoryGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(Category.clone(aCategory)));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount,notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage,notification.firstError().message());

        verify(categoryGateway,times(0)).update(any());

    }

    /**
    @Test
    @Order(3)
    public void givenAValidCommandWithInactiveCategory_whenCallCreateCategory_shouldReturnInactiveCategoryId(){

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
    public void givenAValidCommand_whenGatewayThrowsRandomExeception_shouldReturnAnException(){

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
    **/
}
