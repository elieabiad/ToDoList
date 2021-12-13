package com.elieabiad.toDoList.model;

import com.elieabiad.toDoList.model.dto.ToDoItemDto;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "toDoItem")
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long toDoItemId;
    @NotEmpty
    @Size(min = 2)
    private String title;
    private boolean isDone;

    public static ToDoItem from(ToDoItemDto toDoItemDto){
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setTitle(toDoItemDto.getTitle());
        toDoItem.setDone(toDoItemDto.isDone());//https://www.youtube.com/watch?v=eQsXQBiXXsg 38:10 why no set id
        return toDoItem;
    }
}
