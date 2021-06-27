package com.rkeeves.springboottodo.todo.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void givenEntityTitleNull_whenValidate_thenReturnNonEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setTitle(null);
        // when
        var violations = validator.validate(todo);
        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void givenEntityTitleEmpty_whenValidate_thenReturnNonEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setTitle("");
        // when
        var violations = validator.validate(todo);
        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void givenEntityTitleIs50_whenValidate_thenReturnEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setTitle("x".repeat(50));
        // when
        var violations = validator.validate(todo);
        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void givenEntityTitleIsLongerThan50_whenValidate_thenReturnNonEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setTitle("x".repeat(51));
        // when
        var violations = validator.validate(todo);
        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void givenEntityDescriptionIsNull_whenValidate_thenReturnNonEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setDescription(null);
        // when
        var violations = validator.validate(todo);
        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void givenEntityDescriptionIsEmpty_whenValidate_thenReturnEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setDescription("");
        // when
        var violations = validator.validate(todo);
        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void givenEntityDescriptionIs200Long_whenValidate_thenReturnEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setDescription("x".repeat(200));
        // when
        var violations = validator.validate(todo);
        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void givenEntityDescriptionIsLongerThan200_whenValidate_thenReturnNonEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setDescription("x".repeat(201));
        // when
        var violations = validator.validate(todo);
        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void givenEntityProgressPercentIsNull_whenValidate_thenReturnNonEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setProgressPercent(null);
        // when
        var violations = validator.validate(todo);
        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void givenEntityProgressPercentIsLessThanZero_whenValidate_thenReturnNonEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setProgressPercent(-1);
        // when
        var violations = validator.validate(todo);
        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void givenEntityProgressPercentIsMoreThan100_whenValidate_thenReturnNonEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setProgressPercent(101);
        // when
        var violations = validator.validate(todo);
        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void givenEntityProgressPercentIs0_whenValidate_thenReturnEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setProgressPercent(100);
        // when
        var violations = validator.validate(todo);
        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void givenEntityProgressPercentIsBetween0And100_whenValidate_thenReturnEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setProgressPercent(50);
        // when
        var violations = validator.validate(todo);
        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void givenEntityProgressPercentIs100_whenValidate_thenReturnEmptyListOfViolations() {
        // given
        var todo = validTodo();
        todo.setProgressPercent(100);
        // when
        var violations = validator.validate(todo);
        // then
        assertTrue(violations.isEmpty());
    }

    private Todo validTodo(){
        var todo = new Todo();
        todo.setTitle("title");
        todo.setDescription("description");
        todo.setProgressPercent(50);
        return todo;
    }
}