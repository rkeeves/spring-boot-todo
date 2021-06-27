package com.rkeeves.springboottodo.todo.service;

import com.rkeeves.springboottodo.todo.dto.TodoCreateDTO;
import com.rkeeves.springboottodo.todo.entity.Todo;
import com.rkeeves.springboottodo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo createAndSaveNewTodo(TodoCreateDTO todoCreateDTO){
        var todo = new Todo();
        todo.setTitle(todoCreateDTO.getTitle());
        todo.setDescription(todoCreateDTO.getDescription());
        todo.setProgressPercent(0);
        return todoRepository.save(todo);
    }

    public List<Todo> findAllTodos(){
        return todoRepository.findAll();
    }

    public void delete(Long todoId) {
        todoRepository.findById(todoId).ifPresent(todo -> {
            todoRepository.deleteById(todoId);
        });
    }
}
