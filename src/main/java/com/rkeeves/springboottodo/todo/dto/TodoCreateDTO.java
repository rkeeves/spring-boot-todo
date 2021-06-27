package com.rkeeves.springboottodo.todo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TodoCreateDTO {

    @NotNull(message = "Todo's title cannot be null")
    @NotEmpty(message = "Todo's title cannot be empty")
    @Size(max = 50, message = "Todo's title cannot be longer than 50")
    private String title = "";

    @NotNull(message = "Todo's description cannot be null")
    @Size(max = 200, message = "Todo's description cannot be longer than 200")
    private String description = "";
}
