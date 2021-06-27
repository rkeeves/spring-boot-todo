package com.rkeeves.springboottodo.todo.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoCreateDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void givenEntityTitleNull_whenValidate_thenReturnNonEmptyListOfViolations() {
        // given
        var dto = validDTO();
        dto.setTitle(null);
        // when
        var violations = validator.validate(dto);
        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    void givenEntityTitleEmpty_whenValidate_thenReturnNonEmptyListOfViolations() {
        // given
        var dto = validDTO();
        dto.setTitle("");
        // when
        var violations = validator.validate(dto);
        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    void givenEntityTitleIs50_whenValidate_thenReturnEmptyListOfViolations() {
        // given
        var dto = validDTO();
        dto.setTitle("x".repeat(50));
        // when
        var violations = validator.validate(dto);
        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    void givenEntityTitleIsLongerThan50_whenValidate_thenReturnNonEmptyListOfViolations() {
        // given
        var dto = validDTO();
        dto.setTitle("x".repeat(51));
        // when
        var violations = validator.validate(dto);
        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    void givenEntityDescriptionIsNull_whenValidate_thenReturnNonEmptyListOfViolations() {
        // given
        var dto = validDTO();
        dto.setDescription(null);
        // when
        var violations = validator.validate(dto);
        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    void givenEntityDescriptionIsEmpty_whenValidate_thenReturnEmptyListOfViolations() {
        // given
        var dto = validDTO();
        dto.setDescription("");
        // when
        var violations = validator.validate(dto);
        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    void givenEntityDescriptionIs200Long_whenValidate_thenReturnEmptyListOfViolations() {
        // given
        var dto = validDTO();
        dto.setDescription("x".repeat(200));
        // when
        var violations = validator.validate(dto);
        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    void givenEntityDescriptionIsLongerThan200_whenValidate_thenReturnNonEmptyListOfViolations() {
        // given
        var dto = validDTO();
        dto.setDescription("x".repeat(201));
        // when
        var violations = validator.validate(dto);
        // then
        assertFalse(violations.isEmpty());
    }

    private TodoCreateDTO validDTO(){
        var dto = new TodoCreateDTO();
        dto.setTitle("title");
        dto.setDescription("description");
        return dto;
    }
}