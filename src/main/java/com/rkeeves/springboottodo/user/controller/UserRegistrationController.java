package com.rkeeves.springboottodo.user.controller;

import com.rkeeves.springboottodo.user.dto.UserRegisterDTO;
import com.rkeeves.springboottodo.user.exception.UserAlreadyExistException;
import com.rkeeves.springboottodo.user.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        var userRegisterDTO = new UserRegisterDTO();
        model.addAttribute("userRegisterDTO", userRegisterDTO);
        return "user/register";
    }

    @PostMapping("/register")
    public String processRegisterForm(@Valid UserRegisterDTO dto, BindingResult result, Model model){
        if(result.hasErrors()){
            return "user/register";
        }
        try {
            userRegistrationService.registerNewUser(dto);
        } catch (UserAlreadyExistException e) {
            model.addAttribute("registerError", e.getMessage());
            return "user/register";
        }
        return "redirect:/login";
    }
}
