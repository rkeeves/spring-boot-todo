package com.rkeeves.springboottodo.user.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegisterDTO {

    @NotNull(message = "userName cannot be null")
    @NotEmpty(message = "userName cannot be empty")
    @Size(max = 30, message = "username must be shorter than 31")
    private String userName;

    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    private String password;
}
