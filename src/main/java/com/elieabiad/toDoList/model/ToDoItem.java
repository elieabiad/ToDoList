package com.elieabiad.toDoList.model;

import com.elieabiad.toDoList.model.dto.ToDoItemDto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "toDoItem")
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long toDoItemId;
    private String title;
    private boolean isDone;

    public static ToDoItem from(ToDoItemDto toDoItemDto){
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setTitle(toDoItemDto.getTitle());
        toDoItem.setDone(toDoItemDto.isDone());//https://www.youtube.com/watch?v=eQsXQBiXXsg 38:10 why no set id
        return toDoItem;
    }
}
