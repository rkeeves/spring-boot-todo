package com.rkeeves.springboottodo.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "role name cannot be null")
    @NotEmpty(message = "role name cannot be empty")
    @Size(max = 30, message = "role name must be shorter than 51")
    private String name;
}
