package com.lambdasys.admin.catalogo.domain.category;

import com.lambdasys.admin.catalogo.domain.exceptions.DomainException;
import com.lambdasys.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


public class CategoryTest {

    @Test
    @Order(1)
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory(){

        final var expectedName = "Filmes" ;
        final var expectedDescription = "A categoria mais assistidade" ;
        final var expectedIsActive = true;

        final var actualCategory =
        Category.newCategory( expectedName ,
                expectedDescription ,
                expectedIsActive );

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName,actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.isActive());

        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());

    }

    @Test
    @Order(2)
    public void givenAnInvalidNameNull_whenCallNewCategoryAndValidate_thenShouldRaiseAnError(){

        final String expectedName = null ;
        final var expectedErrorMessage = "name should not be null";
        final var expectedErrorCount = 1 ;
        final var expectedDescription = "A categoria mais assistida" ;
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory( expectedName ,
                        expectedDescription ,
                        expectedIsActive );

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());
    }

    @Test
    @Order(3)
    public void givenAnInvalidNameEmpty_whenCallNewCategoryAndValidate_thenShouldRaiseAnError(){

        final var expectedName = "" ;
        final var expectedErrorMessage = "name should not be empty";
        final var expectedErrorCount = 1 ;
        final var expectedDescription = "A categoria mais assistida" ;
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory( expectedName ,
                        expectedDescription ,
                        expectedIsActive );

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());
    }

    @Test
    @Order(4)
    public void givenAnInvalidNameLengthLessThen3_whenCallNewCategoryAndValidate_thenShouldRaiseAnError(){

        final var expectedName = "Fi " ;
        final var expectedErrorMessage = "name must be between 3 and 255 characters";
        final var expectedErrorCount = 1 ;
        final var expectedDescription = "A categoria mais assistida" ;
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory( expectedName ,
                        expectedDescription ,
                        expectedIsActive );

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());
    }

    @Test
    @Order(5)
    public void givenAnInvalidNameLengthGreaterThen255_whenCallNewCategoryAndValidate_thenShouldRaiseAnError(){

        final var expectedName = """
                Considerando que temos bons administradores de rede, o uso de servidores em datacenter otimiza o uso dos 
                processadores dos requisitos mínimos de hardware exigidos. No nível organizacional, a interoperabilidade 
                de hardware pode nos levar a considerar a reestruturação dos paradigmas de desenvolvimento de software. 
                Assim mesmo, a lógica proposicional exige o upgrade e a atualização do bloqueio de portas imposto pelas 
                redes corporativas. No entanto, não podemos esquecer que o novo modelo computacional aqui preconizado 
                auxilia no aumento da segurança e/ou na mitigação dos problemas do tempo de down-time que deve ser mínimo.
            """ ;
        final var expectedErrorMessage = "name must be between 3 and 255 characters";
        final var expectedErrorCount = 1 ;
        final var expectedDescription = "A categoria mais assistida" ;
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory( expectedName ,
                        expectedDescription ,
                        expectedIsActive );

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());
    }

    @Test
    @Order(6)
    public void givenAValidEmptyDescription_whenCallNewCategory_thenInstantiateACategory(){

        final var expectedName = "Filmes" ;
        final var expectedDescription = " " ;
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory( expectedName ,
                        expectedDescription ,
                        expectedIsActive );

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName,actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.isActive());

        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());

    }

    @Test
    @Order(7)
    public void givenAValidFalseIsActive_whenCallNewCategory_thenInstantiateACategory(){

        final var expectedName = "Filmes" ;
        final var expectedDescription = "A categoria mais assistida" ;
        final var expectedIsActive = false;

        final var actualCategory =
                Category.newCategory( expectedName ,
                        expectedDescription ,
                        expectedIsActive );

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName,actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.isActive());

        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    @Order(8)
    public void givenAValidActiveCategory_whenCallDeactivate_ThenReturnACategoryInactivated(){

        final var expectedName = "Filmes" ;
        final var expectedDescription = " " ;
        final var expectedIsActive = false;

        final var aCategory =
                Category.newCategory( expectedName ,
                        expectedDescription ,
                        true );

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getUpdatedAt());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var actualCategory = aCategory.deactivate();

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));


        Assertions.assertEquals(aCategory.getId(),actualCategory.getId());
        Assertions.assertEquals(expectedName,actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.isActive());

        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());

    }

    @Test
    @Order(9)
    public void givenAValidDeactivatedCategory_whenCallDeactivate_ThenReturnACategoryActivated(){

        final var expectedName = "Filmes" ;
        final var expectedDescription = " " ;
        final var expectedIsActive = true;

        final var aCategory =
                Category.newCategory( expectedName ,
                        expectedDescription ,
                        false );

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();


        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getUpdatedAt());
        Assertions.assertNotNull(aCategory.getDeletedAt());

        final var actualCategory = aCategory.activate();

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));


        Assertions.assertEquals(aCategory.getId(),actualCategory.getId());
        Assertions.assertEquals(expectedName,actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.isActive());

        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());

    }

    @Test
    @Order(10)
    public void givenAValidCatetory_whenCallUpdate_thenReturnCategoryUpdated(){

        final var expectedName = "Filmes" ;
        final var expectedDescription = " " ;
        final var expectedIsActive = true;

        final var aCategory =
                Category.newCategory( "Film" ,
                        "A categoria" ,
                        expectedIsActive );

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        var actualCategory = aCategory.update(expectedName,expectedDescription,expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));


        Assertions.assertEquals(aCategory.getId(),actualCategory.getId());
        Assertions.assertEquals(expectedName,actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.isActive());

        Assertions.assertEquals(createdAt,actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());


    }

    @Test
    @Order(10)
    public void givenAValidCatetory_whenCallUpdateToInactivated_thenReturnCategoryUpdated(){

        final var expectedName = "Filmes" ;
        final var expectedDescription = " " ;
        final var expectedIsActive = false;

        final var aCategory =
                Category.newCategory( "Film" ,
                        "A categoria" ,
                        true );

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        var actualCategory = aCategory.update(expectedName,expectedDescription,expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));


        Assertions.assertEquals(aCategory.getId(),actualCategory.getId());
        Assertions.assertEquals(expectedName,actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.isActive());

        Assertions.assertEquals(createdAt,actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());


    }

    @Test
    @Order(10)
    public void givenAValidCatetory_whenCallUpdateToInactivatedWithInvalidParams_thenReturnCategoryUpdated(){

        final String expectedName = null ;
        final var expectedDescription = " " ;
        final var expectedIsActive = true;

        final var aCategory =
                Category.newCategory( "Film" ,
                        "A categoria" ,
                        true );

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));


        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        var actualCategory = aCategory.update(expectedName,expectedDescription,expectedIsActive);


        Assertions.assertEquals(aCategory.getId(),actualCategory.getId());
        Assertions.assertEquals(expectedName,actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.isActive());

        Assertions.assertEquals(createdAt,actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());


    }

}
