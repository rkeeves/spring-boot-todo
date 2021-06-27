package com.rkeeves.springboottodo.todo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "todos")
@Getter
@Setter
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Todo's title cannot be null")
    @NotEmpty(message = "Todo's title cannot be empty")
    @Size(max = 50, message = "Todo's title cannot be longer than 50")
    private String title;

    @NotNull(message = "Todo's description cannot be null")
    @Size(max = 200, message = "Todo's description cannot be longer than 200")
    private String description;

    @NotNull(message = "Todo's progress cannot be null")
    @Min(value = 0, message = "Todo's progress must be an integer in [0, 100]")
    @Max(value = 100, message = "Todo's progress must be an integer in [0, 100]")
    @Column(name = "progress_percent")
    private Integer progressPercent;
}
