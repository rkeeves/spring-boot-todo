package com.rkeeves.springboottodo.todo.repository;

import com.rkeeves.springboottodo.todo.entity.Todo;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TodoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void givenNoEntitiesExists_whenFindAll_thenReturnEmptyList() {
        // given
        // no entities exist
        // when
        var actual = todoRepository.findAll();
        // then
        assertEquals(Lists.emptyList(), actual);
    }

    @Test
    void givenOneEntityExists_whenFindAll_thenReturnListWithOneElement() {
        // given
        var todo = validTodo();
        var expectedEntity = entityManager.persist(todo);
        entityManager.flush();
        entityManager.clear();
        // when
        var actual = todoRepository.findAll();
        // then
        assertEquals(List.of(expectedEntity), actual);
    }

    @Test
    void givenNEntitiesExists_whenFindAll_thenReturnListWithNElements() {
        // given
        var todo0 = validTodo();
        var expectedEntity0 = entityManager.persist(todo0);
        var todo1 = new Todo();
        todo1.setTitle("SomeTitle1");
        todo1.setDescription("SomeDescription1");
        todo1.setProgressPercent(100);
        var expectedEntity1 = entityManager.persist(todo1);
        entityManager.flush();
        entityManager.clear();
        // when
        var actual = todoRepository.findAll();
        // then
        assertEquals(List.of(expectedEntity0, expectedEntity1), actual);
    }

    @Test
    void givenEntityByIdNotExists_whenFindById_thenReturnTodo() {
        // given
        // when
        var actualEntityOptional = todoRepository.findById(1L);
        // then
        assertTrue(actualEntityOptional.isEmpty());
    }

    @Test
    void givenEntityByIdExists_whenFindById_thenReturnTodo() {
        // given
        var todo = validTodo();
        var expectedEntity = entityManager.persist(todo);
        entityManager.flush();
        entityManager.clear();
        // when
        var actualEntityOptional = todoRepository.findById(expectedEntity.getId());
        // then
        assertTrue(actualEntityOptional.isPresent());
        assertEquals(expectedEntity,actualEntityOptional.get());
    }

    @Test
    void givenEntityIsValidAndIsNotYetPersisted_whenSave_thenReturnPersistedEntity() {
        // given
        var todo = validTodo();
        // when
        var actualEntity = todoRepository.save(todo);
        // then
        assertNotNull(actualEntity.getId());
        assertEquals(todo.getTitle(),actualEntity.getTitle());
        assertEquals(todo.getDescription(),actualEntity.getDescription());
        assertEquals(todo.getProgressPercent(),actualEntity.getProgressPercent());
    }

    @Test
    void givenEntityIsValidAndIsPersisted_whenSave_thenReturnUpdatedEntity() {
        // given
        var oldTodo = validTodo();
        entityManager.persist(oldTodo);
        entityManager.flush();
        entityManager.clear();

        var changedTodo = new Todo();
        changedTodo.setId(oldTodo.getId());
        changedTodo.setTitle("SomeTitleA");
        changedTodo.setDescription("SomeDescriptionB");
        changedTodo.setProgressPercent(100);
        // when
        var actualEntity = todoRepository.save(changedTodo);
        // then
        assertEquals(changedTodo.getId(), actualEntity.getId());
        assertEquals(changedTodo.getTitle(),actualEntity.getTitle());
        assertEquals(changedTodo.getDescription(),actualEntity.getDescription());
        assertEquals(changedTodo.getProgressPercent(),actualEntity.getProgressPercent());
    }

    @Test
    void givenEntityIsValid_whenSave_thenReturnEntity() {
        // given
        var todo = validTodo();
        // when
        var actual = todoRepository.save(todo);
        // then
        assertNotNull(actual.getId());
        assertEquals(actual.getTitle(), todo.getTitle());
        assertEquals(actual.getDescription(), todo.getDescription());
        assertEquals(actual.getProgressPercent(), todo.getProgressPercent());
    }

    private Todo validTodo(){
        var todo = new Todo();
        todo.setTitle("title");
        todo.setDescription("description");
        todo.setProgressPercent(50);
        return todo;
    }
}