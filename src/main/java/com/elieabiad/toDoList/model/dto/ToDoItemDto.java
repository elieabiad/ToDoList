package com.elieabiad.toDoList.model.dto;

import com.elieabiad.toDoList.model.ToDoItem;
import lombok.Data;

@Data
public class ToDoItemDto {
    private Long id;
    private String title;
    private boolean isDone;

    public static ToDoItemDto from(ToDoItem toDoItem){
        ToDoItemDto toDoItemDto = new ToDoItemDto();
        toDoItemDto.setId(toDoItem.getToDoItemId());
        toDoItemDto.setTitle(toDoItem.getTitle());
        toDoItemDto.setDone(toDoItem.isDone());
        return toDoItemDto;
    }
}
