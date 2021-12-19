package com.elieabiad.toDoList.model.exception;

public class UnAuthenticatedUserException extends RuntimeException{
    public UnAuthenticatedUserException(String message){
        super(message);
    }
}
