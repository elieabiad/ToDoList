package com.elieabiad.toDoList.model.exception;

/*import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(final Long id){
        super(MessageFormat.format("Could not find User with id: {0}", id));
    }
}*/

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
