package com.rkeeves.springboottodo.todo.controller;

import com.rkeeves.springboottodo.todo.dto.TodoCreateDTO;
import com.rkeeves.springboottodo.todo.entity.Todo;
import com.rkeeves.springboottodo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/")
    public String showTodoList(Model model){
        model.addAttribute("todos" ,  todoService.findAllTodos());
        return "todo/list";
    }

    @GetMapping("/todo/add")
    public String showFormCreateTodo(Model model){
        model.addAttribute("todoCreateDTO" , new TodoCreateDTO());
        return "todo/add";
    }

    @PostMapping("/todo/add")
    public String processFormCreateTodo(@Valid TodoCreateDTO todoCreateDTO, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "todo/add";
        }
        todoService.createAndSaveNewTodo(todoCreateDTO);
        return "redirect:/";
    }

    @GetMapping("/todo/delete/{todoId}")
    public String deleteTodo(@PathVariable Long todoId){
        todoService.delete(todoId);
        return "redirect:/";
    }

    @GetMapping("/todo/edit/{todoId}")
    public String showFormEditTodo(@PathVariable Long todoId, Model model){
        var editableTodo = todoService.findById(todoId);
        if(editableTodo.isEmpty()){
            return "redirect:/";
        }
        model.addAttribute("todo", editableTodo.get());
        return "todo/edit";
    }

    @PostMapping("/todo/edit")
    public String showFormEditTodo(@Valid Todo todo, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "todo/edit";
        }
        todoService.update(todo);
        return "redirect:/";
    }
}
