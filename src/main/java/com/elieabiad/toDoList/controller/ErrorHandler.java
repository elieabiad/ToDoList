package com.elieabiad.toDoList.controller;

import com.elieabiad.toDoList.model.exception.ApplicationError;
import com.elieabiad.toDoList.model.exception.ToDoItemNotFoundException;
import com.elieabiad.toDoList.model.exception.UnderAgeOverFiveToDoItemsException;
import com.elieabiad.toDoList.model.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ToDoItemNotFoundException.class)
    public ResponseEntity<ApplicationError> handleToDoItemNotFoundException(ToDoItemNotFoundException exception, WebRequest webRequest){
        ApplicationError error = new ApplicationError();
        error.setCode(333);
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApplicationError> handleUserNotFoundException(UserNotFoundException exception, WebRequest webRequest){
        ApplicationError error = new ApplicationError();
        error.setCode(444);
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UnderAgeOverFiveToDoItemsException.class)
    public ResponseEntity<ApplicationError> handleUnderageOverFiveToDoItemsException(UnderAgeOverFiveToDoItemsException exception, WebRequest webRequest){
        ApplicationError error = new ApplicationError();
        error.setCode(555);
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
