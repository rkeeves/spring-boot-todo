package com.rkeeves.springboottodo.todo.repository;

import com.rkeeves.springboottodo.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
