package com.rkeeves.springboottodo.todo.service;

import com.rkeeves.springboottodo.todo.dto.TodoCreateDTO;
import com.rkeeves.springboottodo.todo.entity.Todo;
import com.rkeeves.springboottodo.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    TodoService todoService;

    @BeforeEach
    void init(){
        when(todoRepository.save(any(Todo.class))).then(new Answer<Todo>() {
            long sequence = 1;
            @Override
            public Todo answer(InvocationOnMock invocation) throws Throwable {
                Todo user = (Todo) invocation.getArgument(0);
                user.setId(sequence++);
                return user;
            }
        });
    }

    @Test
    void givenValidTodoCreateDTO_whenCreateAndSaveNewTodo_ReturnsPersistedEntity(){
        // given
        var dto = new TodoCreateDTO();
        dto.setTitle("Title");
        dto.setDescription("Description");
        // when
        var todo = todoService.createAndSaveNewTodo(dto);
        // then
        assertNotNull(todo.getId());
        assertEquals(dto.getTitle(), todo.getTitle());
        assertEquals(dto.getDescription(), todo.getDescription());
        assertEquals(0, todo.getProgressPercent());
    }
}