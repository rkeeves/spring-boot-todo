package com.rkeeves.springboottodo.todo.controller;

import com.rkeeves.springboottodo.todo.dto.TodoCreateDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TodoController {

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
        return "redirect:/";
    }
}
