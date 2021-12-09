package com.elieabiad.toDoList.model.exception;

/*import java.text.MessageFormat;

public class ToDoItemNotFoundException extends RuntimeException{
    public ToDoItemNotFoundException(final Long id){
        super(MessageFormat.format("Could not find ToDoItem with id: {0}", id));
    }
}*/

public class ToDoItemNotFoundException extends RuntimeException{
    public ToDoItemNotFoundException(String message){
        super(message);
    }
}
