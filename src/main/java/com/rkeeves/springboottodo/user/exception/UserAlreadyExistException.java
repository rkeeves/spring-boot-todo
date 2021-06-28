package com.rkeeves.springboottodo.user.exception;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String userName) {
        super(String.format("Username %s already exists", userName));
    }
}
